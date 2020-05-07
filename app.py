from flask import Flask
from flask import render_template
from flask import request, redirect
from werkzeug.utils import secure_filename
import time
import os
import cv2
import face_recognition
import time
import numpy as np

app = Flask(__name__)
app.config["IMAGE_UPLOADS"]  = './upload-video/'
@app.route('/index')
@app.route('/')
def index():
    return render_template("index.html")


@app.route('/upload', methods=["GET", "POST"])
def uploadVideo():
    if request.method == 'POST':
        if request.files:
            video = request.files["video"]
            timestr = time.strftime("%Y%m%d-%H%M%S")
            filename = secure_filename(timestr) + ".mp4"
            video.save(os.path.join(app.config["IMAGE_UPLOADS"], filename))
            return redirect('/upload/ml?file=' + timestr)
        else:
            messages = "Missing the file"
            return render_template("index.html", messages = messages)
    else:
        return render_template("index.html")

@app.route('/upload/ml', methods=["GET", "POST"])
def process():
    fileName = request.args.get('file')
    cap = cv2.VideoCapture('./upload-video/' + fileName +'.mp4')
    length = 20
    timestr = time.strftime("%Y%m%d-%H%M%S")

    face_locations = []
    face_encodings = []
    detected_face = []
    detected_face_img = []
    frame_number = 0
    
    save_image_count = 0

    fourcc = cv2.VideoWriter_fourcc(*'x264')
    output_movie = cv2.VideoWriter('./static/save-data/' + timestr + '.mp4', fourcc, length, (1280, 720))
    while True:
    # Grab a single frame of video
        ret, frame = cap.read()
        appendData = False
        if not ret:
            break
        rgb_frame = frame[:, :, ::-1]
        face_locations = face_recognition.face_locations(rgb_frame)
        face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)

        if(len(detected_face) == 0):
            for face_encoding in face_encodings:
                detected_face.append(face_encoding)
            appendData = True
        else:
            print('else')
            for face_encoding in face_encodings:
                matchs = face_recognition.compare_faces(detected_face, face_encoding, tolerance=0.5)
                if any(matchs) == False:
                    face_distances = face_recognition.face_distance(detected_face, face_encoding)
                    for face_distance in face_distances:
                        print(face_distance)
                        if face_distance > 0.65:
                            appendData = True
                            detected_face.append(face_encoding)
                            print("append")


        for top, right, bottom, left in face_locations:
            # Draw a box around the face
            if appendData:
                crop_img = frame[top:bottom, left:right]
                save_image_count = save_image_count + 1
                cv2.imwrite("./static/save-data/" + timestr + '-' + str(save_image_count)+".png", crop_img)
                detected_face_img.append({"id": save_image_count, "url" : "/static/save-data/" + timestr + '-' + str(save_image_count)+".png"})
                print('Save Image')
            cv2.rectangle(frame, (left, top), (right, bottom), (0, 0,  
            255), 2)
        # Display the resulting image
        output_movie.write(frame)
        #cv2.imshow('Video', frame)
    
        # Wait for Enter key to stop
    return render_template("process.html", detected_face_img = detected_face_img, fileName = timestr)

@app.route('/upload/ml/render', methods=["POST"])
def render():
    fileName = request.form.get('file')
    print('ifle =' +fileName)
    seletedFormFace = request.form.getlist('face')
    print(seletedFormFace)
    cap = cv2.VideoCapture('./upload-video/' + fileName +'.mp4')
    length = 20
    seletedFaces = []
    detected_face = []
    face_locations = []
    face_encodings = []
    return_seleted_img = []
    detected_face_count = 0


    for face in seletedFormFace:
       # image = face_recognition.load_image_file("./static/save-data/" + fileName + '-' + face+".png")
       # seletedFaces.append(face_recognition.face_encodings(image)[0])
        seletedFaces.append(int(face) - 1)
        return_seleted_img.append("/static/save-data/" + fileName + '-' + face+ ".png")

    print(seletedFaces)

    fourcc = cv2.VideoWriter_fourcc(*'x264')
    output_movie = cv2.VideoWriter('./static/save-data/' + fileName + '-finish.mp4', fourcc, length, (1280, 720))

    while True:
    # Grab a single frame of video
        ret, frame = cap.read()
        match_check = False
        if not ret:
            break
        rgb_frame = frame[:, :, ::-1]
        face_locations = face_recognition.face_locations(rgb_frame)
        face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)
        match_list = []
        if(len(detected_face) == 0):
            for face_encoding in face_encodings:
                detected_face.append(face_encoding)
                detected_face_count = detected_face_count + 1

        for face_encoding in face_encodings:
            matchs = face_recognition.compare_faces(detected_face, face_encoding, tolerance=0.5)
            if any(matchs) == False:
                face_distances = face_recognition.face_distance(detected_face, face_encoding)
                for face_distance in face_distances:
                    print(face_distance)
                    if face_distance > 0.65:
                        detected_face.append(face_encoding)
                        print('append')
                        detected_face_count = detected_face_count + 1
                
        detected_face_index = 0
        for face in detected_face:
            matchs = face_recognition.compare_faces(face_encodings, face, tolerance=0.5)
            match_count = 0
            for match in matchs:
                print(matchs)
                if match == True and detected_face_index in seletedFaces:
                    print('match')
                    print(match)
                    match_check = True
                    match_list.append(match_count)
                    print(match_count)
                match_count = match_count + 1
            detected_face_index = detected_face_index + 1


                        #match_count = 0
                        #for match in matchs:
                        #    print(matchs)
                        #    if match == True:
                        #        print('match')
                        #        print(match)
                        #        match_check = True
                        #        match_list.append(match_count)
                        #        print(match_count)
                        #    match_count = match_count + 1
                                
        print(match_list)
        if match_check == True:
            location_count = 0
            for top, right, bottom, left in face_locations:
                print(match_list)
                print(location_count)

                if location_count in match_list:
                    print('loca')
                    print(location_count)
                    roi_color = rgb_frame[top:bottom, left:right]
                    # blur the colored image
                    blur = cv2.GaussianBlur(roi_color, (99,99), 30)
                    # Insert ROI back into image
                    rgb_frame[top:bottom, left:right] = blur
                location_count = location_count + 1
            # Draw a box around the face
            
        # Display the resulting image
        output_movie.write(frame)
        #cv2.imshow('Video', frame)
    
        # Wait for Enter key to stop
    return render_template("render.html", detected_face_img = return_seleted_img, fileName = fileName)


@app.route('/upload/test')
def test():
    detected_face_img = []
    detected_face_img.append({"id":1, "url": "/static/save-data/20200507-032151-1.png"})
    detected_face_img.append({"id":2, "url": "/static/save-data/20200507-032151-2.png"})
    detected_face_img.append({"id":3, "url": "/static/save-data/20200507-032151-3.png"})
    return render_template("process.html", detected_face_img = detected_face_img, fileName = '20200507-032151')
    

if __name__ == '__main__':
    app.run()