Public Class Student
    Public name As String
    Public markTest As Double
    Public markQuiz As Double
    Public markProject As Double
    Public markExam As Double

    Sub New(name As String, markTest As Double, markQuiz As Double, markProject As Double, markExam As Double)
        Me.name = name
        Me.markTest = markTest
        Me.markQuiz = markQuiz
        Me.markProject = markProject
        Me.markExam = markExam
    End Sub

    Function getCAMarks() As Double
        Return markTest * CAMarksModule.TestPercent + markProject * CAMarksModule.ProjectPercent + markQuiz * CAMarksModule.QuizPercent
    End Function

    Function getExamMark() As Double
        Return markExam
    End Function

    Function getModuleMark() As Double
        Return getCAMarks() * ModuleMark.CAPercent + getExamMark() * ModuleMark.ExamPercent
    End Function

    Function getModuleGrade() As String
        Dim moduleMark As Double = getModuleMark()
        If moduleMark >= 75 And moduleMark <= 100 Then
            Return "A"
        ElseIf moduleMark >= 65 Then
            Return "B"
        ElseIf moduleMark >= 40 Then
            Return "C"
        Else
            Return "F"
        End If
    End Function

    Function getRemark() As String
        Select Case getModuleGrade()
            Case "A"
                Return "Pass"
            Case "B"
                Return "Pass"
            Case "C"
                Return "Pass"
            Case "F"
                If (getModuleMark() >= 30) Then
                    Return "Resit Exam"
                End If
        End Select
        Return "Retudy"
    End Function

End Class
