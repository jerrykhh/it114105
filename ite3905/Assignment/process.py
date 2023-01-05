import cv2
import face_recognition
import time

cap = cv2.VideoCapture('./data/source-1.mp4')
length = 20
fourcc = cv2.VideoWriter_fourcc('m', 'p', '4', 'v')
output_movie = cv2.VideoWriter('output2_9.mp4', fourcc, length, (1280, 720))

face_locations = []
face_encodings = []
detected_face = []
frame_number = 0
timestr = time.strftime("%Y%m%d-%H%M%S")
save_image_count = 0

fourcc = cv2.VideoWriter_fourcc(*'x264')
output_movie = cv2.VideoWriter(timestr + '.mp4', fourcc, length, (1280, 720))


while True:
    # Grab a single frame of video
    ret, frame = cap.read()
    appendData = False
    if not ret:
        break
    # Convert the image from BGR color (which OpenCV uses) to RGB 
      
    # color (which face_recognition uses)
    rgb_frame = frame[:, :, ::-1]
    # Find all the faces in the current frame of video
   # print('code')
   # print(face_locations)
    face_locations = face_recognition.face_locations(rgb_frame)
    face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)
    for endcoding in face_encodings:
        if(len(detected_face) == 0):
            for face_encoding in face_encodings:
                detected_face.append(face_encoding)
            appendData = True
        else:
            print('else')
            count = 1
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
            cv2.imwrite("./save-data/" + timestr + '-' + str(save_image_count)+".png", crop_img)
            print('Save Image')
        cv2.rectangle(frame, (left, top), (right, bottom), (0, 0,  
        255), 2)
    # Display the resulting image
    output_movie.write(frame)
    #cv2.imshow('Video', frame)
    
    # Wait for Enter key to stop

print(len(detected_face))
output_movie.release()
cv2.destroyAllWindows()
