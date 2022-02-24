Public Class Form1

    Dim students As ArrayList
    Dim showStatisticList As Boolean

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        students = New ArrayList()
        showStatisticList = False
        gbModuleResult.Text = "Module Result (CA:" + CStr(ModuleMark.CAPercent * 100) + "%, Exam:" + CStr(ModuleMark.ExamPercent * 100) + "%)"
    End Sub

    Private Sub btnConfirm_Click(sender As Object, e As EventArgs) Handles btnConfirm.Click
        If txtName.Text.Length = 0 Then
            MessageBox.Show("Please input the Name")
            Return
        End If

        If Not IsNumeric(txtTestMark.Text) Or Not IsNumeric(txtProjectMark.Text) Or Not IsNumeric(txtQuizMark.Text) Or Not IsNumeric(txtExamMark.Text) Then
            MessageBox.Show("Please input the number")
            Return
        End If

        If txtTestMark.Text > 100 Or txtProjectMark.Text > 100 Or txtQuizMark.Text > 100 Or txtExamMark.Text > 100 Then
            MessageBox.Show("The mark must small or equal to 100 ")
            Return
        End If

        If txtTestMark.Text < 0 Or txtProjectMark.Text < 0 Or txtQuizMark.Text < 0 Or txtExamMark.Text < 0 Then
            MessageBox.Show("The mark must bigger or equal to 0 ")
            Return
        End If

        Dim name As String = txtName.Text
        Dim markTest As Double = CDbl(txtTestMark.Text)
        Dim markProject As Double = CDbl(txtProjectMark.Text)
        Dim markQuiz As Double = CDbl(txtQuizMark.Text)
        Dim markExam As Double = CDbl(txtExamMark.Text)

        Dim student As Student = New Student(name, markTest, markQuiz, markProject, markExam)
        students.Add(student)
        ltbStudents.Items.Add(student.name)
        txtCAMark.Text = student.getCAMarks()
        txtModuleMark.Text = student.getModuleMark().ToString("F2")
        txtModuleGrade.Text = student.getModuleGrade()
        txtRemark.Text = student.getRemark()

        If showStatisticList Then
            updateStudentStatistic()
        End If
        clearInputField()
    End Sub

    Private Sub updateStudentStatistic()
        txtNumOfStudents.Text = students.Count
        Dim AllStudentMark As Double = 0.0
        Dim CountOfA As Integer = 0
        Dim CountOfF As Integer = 0
        For Each student As Student In students
            AllStudentMark += student.getModuleMark()
            If student.getModuleGrade = "A" Then
                CountOfA += 1
            ElseIf student.getModuleGrade = "F" Then
                CountOfF += 1
            End If
        Next
        txtCountOfA.Text = CountOfA
        txtCountOfF.Text = CountOfF
        If students.Count > 0 Then
            txtModuleAvg.Text = AllStudentMark / students.Count
        Else
            txtModuleAvg.Text = 0.0
        End If
    End Sub

    Private Sub hiddenStatistic()
        txtNumOfStudents.Clear()
        txtCountOfA.Clear()
        txtCountOfF.Clear()
        txtModuleAvg.Clear()
    End Sub

    Private Sub clearModuleResult()
        txtCAMark.Clear()
        txtModuleMark.Clear()
        txtModuleGrade.Clear()
        txtRemark.Clear()
    End Sub

    Private Sub clearInputField()
        txtName.Clear()
        txtTestMark.Clear()
        txtQuizMark.Clear()
        txtProjectMark.Clear()
        txtExamMark.Clear()
    End Sub


    Private Sub btnShowStatistic_Click(sender As Object, e As EventArgs) Handles btnShowStatistic.Click
        showStatisticList = Not showStatisticList
        If showStatisticList Then
            updateStudentStatistic()
            btnShowStatistic.Text = "Hidden Statistic"
        Else
            btnShowStatistic.Text = "Show Statistic"
            hiddenStatistic()
        End If
    End Sub

    Private Sub btnFind_Click(sender As Object, e As EventArgs) Handles btnFind.Click
        If txtFindStudent.Text.Length <= 0 Then
            MessageBox.Show("Please Input the student name to find the list")
            Return
        End If
        Dim FoundStudent As Student = Nothing
        For Each student As Student In students
            If student.name.Equals(txtFindStudent.Text) Then
                FoundStudent = student
                Exit For
            End If
        Next
        If Not IsNothing(FoundStudent) Then
            MessageBox.Show(FoundStudent.name + " is on line " + CStr(students.IndexOf(FoundStudent) + 1) + " of the list")
        Else
            MessageBox.Show("Student not found")
        End If

        txtFindStudent.Clear()
        ' Search Listbox version
        ' Dim studentIndex As Integer = -1
        ' For Each studentName As String In ltbStudents.Items
        '   If txtFindStudent.Text.Equals(studentName) Then
        '     Exit For
        '   End If
        '   studentIndex += 1
        ' Next
        ' If studentIndex > -1 Then
        '   MessageBox.Show(ltbStudents.Items.IndexOf(studentIndex) + " is on line " + (studentIndex + 1) + "of the list")
        ' Else
        '   MessageBox.Show("Student not found")
        ' End If
    End Sub

    Private Sub btnClearAll_Click(sender As Object, e As EventArgs) Handles btnClearAll.Click
        Dim result As DialogResult = MessageBox.Show("Clear All Records?", "Alert", MessageBoxButtons.YesNo)
        If result = DialogResult.Yes Then
            hiddenStatistic()
            clearInputField()
            clearModuleResult()
            students.Clear()
            ltbStudents.Items.Clear()
        End If

    End Sub


End Class
