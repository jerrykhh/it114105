import Foundation

class Student{
    var classId:String
    var studentId:String
    init(classId:String, studentId: String) {
        self.classId = classId
        self.studentId = studentId
    }
}

var students = Array<Student>()
let totalStudent = Int.random(in: 30...100)
let totalClass: Int = Int(ceil((Double(totalStudent) / 30)))

func generateStudentId() -> String{
    let year = "20"
    let campusCode = "03"
    let randomDigit: Int = Int.random(in: 0...99999)
    return year + campusCode + String(format: "%05d", randomDigit)
}

func checkDuplicate(studentId:String) -> Bool{
    var duplicate = false
        for student in students{
            if student.studentId == studentId{
                duplicate = true
                break
            }
        }
    return duplicate
}

var distriClass:Int = 1
for count in 1...totalStudent{
    var studentId: String
    var student: Student

    repeat{
        studentId = generateStudentId()
    }while checkDuplicate(studentId: studentId)

    student = Student(classId:String(distriClass), studentId: studentId)
    students.append(student)
    if distriClass == 4 {
        distriClass = 0
    }
    distriClass += 1
}

print("Total Student: " + String(totalStudent))
print("Total Class: " + String(totalClass))
print("--------------------------------------------------------")
var classA:Int = 0
var classB:Int = 0
var classC:Int = 0
var classD:Int = 0
for student in students{
    print("Class: " + student.classId + ", Student ID: " + student.studentId)
    if "1" == student.classId{
        classA += 1
    }else if "2" == student.classId{
        classB += 1
    }else if "3" == student.classId{
        classC = classC + 1
    }else{
        classD = classD + 1
    }
}
print("classA: " + String(classA))
print("classB: " + String(classB))
print("classC: " + String(classC))
print("classD: " + String(classD))
