<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.btnClearAll = New System.Windows.Forms.Button()
        Me.txtName = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.gbModuleResult = New System.Windows.Forms.GroupBox()
        Me.txtRemark = New System.Windows.Forms.TextBox()
        Me.txtModuleMark = New System.Windows.Forms.TextBox()
        Me.txtModuleGrade = New System.Windows.Forms.TextBox()
        Me.txtCAMark = New System.Windows.Forms.TextBox()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.gbCAAndExam = New System.Windows.Forms.GroupBox()
        Me.btnConfirm = New System.Windows.Forms.Button()
        Me.txtExamMark = New System.Windows.Forms.TextBox()
        Me.txtQuizMark = New System.Windows.Forms.TextBox()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.txtProjectMark = New System.Windows.Forms.TextBox()
        Me.txtTestMark = New System.Windows.Forms.TextBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.lblCAComponent = New System.Windows.Forms.Label()
        Me.GroupBox4 = New System.Windows.Forms.GroupBox()
        Me.ltbStudents = New System.Windows.Forms.ListBox()
        Me.btnFind = New System.Windows.Forms.Button()
        Me.txtFindStudent = New System.Windows.Forms.TextBox()
        Me.GroupBox5 = New System.Windows.Forms.GroupBox()
        Me.txtCountOfF = New System.Windows.Forms.TextBox()
        Me.txtModuleAvg = New System.Windows.Forms.TextBox()
        Me.Label14 = New System.Windows.Forms.Label()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.txtCountOfA = New System.Windows.Forms.TextBox()
        Me.txtNumOfStudents = New System.Windows.Forms.TextBox()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.btnShowStatistic = New System.Windows.Forms.Button()
        Me.GroupBox1.SuspendLayout()
        Me.gbModuleResult.SuspendLayout()
        Me.gbCAAndExam.SuspendLayout()
        Me.GroupBox4.SuspendLayout()
        Me.GroupBox5.SuspendLayout()
        Me.SuspendLayout()
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.btnClearAll)
        Me.GroupBox1.Controls.Add(Me.txtName)
        Me.GroupBox1.Controls.Add(Me.Label1)
        Me.GroupBox1.Controls.Add(Me.gbModuleResult)
        Me.GroupBox1.Controls.Add(Me.gbCAAndExam)
        Me.GroupBox1.Location = New System.Drawing.Point(41, 48)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(922, 793)
        Me.GroupBox1.TabIndex = 0
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Marks and Grade for Individual Student"
        '
        'btnClearAll
        '
        Me.btnClearAll.Location = New System.Drawing.Point(588, 56)
        Me.btnClearAll.Name = "btnClearAll"
        Me.btnClearAll.Size = New System.Drawing.Size(150, 46)
        Me.btnClearAll.TabIndex = 5
        Me.btnClearAll.Text = "Clear All"
        Me.btnClearAll.UseVisualStyleBackColor = True
        '
        'txtName
        '
        Me.txtName.Location = New System.Drawing.Point(148, 61)
        Me.txtName.Name = "txtName"
        Me.txtName.Size = New System.Drawing.Size(400, 38)
        Me.txtName.TabIndex = 4
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(34, 64)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(87, 30)
        Me.Label1.TabIndex = 3
        Me.Label1.Text = "Name:"
        '
        'gbModuleResult
        '
        Me.gbModuleResult.Controls.Add(Me.txtRemark)
        Me.gbModuleResult.Controls.Add(Me.txtModuleMark)
        Me.gbModuleResult.Controls.Add(Me.txtModuleGrade)
        Me.gbModuleResult.Controls.Add(Me.txtCAMark)
        Me.gbModuleResult.Controls.Add(Me.Label10)
        Me.gbModuleResult.Controls.Add(Me.Label9)
        Me.gbModuleResult.Controls.Add(Me.Label8)
        Me.gbModuleResult.Controls.Add(Me.Label7)
        Me.gbModuleResult.Location = New System.Drawing.Point(34, 549)
        Me.gbModuleResult.Name = "gbModuleResult"
        Me.gbModuleResult.Size = New System.Drawing.Size(850, 204)
        Me.gbModuleResult.TabIndex = 2
        Me.gbModuleResult.TabStop = False
        Me.gbModuleResult.Text = "Module Result"
        '
        'txtRemark
        '
        Me.txtRemark.Location = New System.Drawing.Point(564, 130)
        Me.txtRemark.Name = "txtRemark"
        Me.txtRemark.ReadOnly = True
        Me.txtRemark.Size = New System.Drawing.Size(221, 38)
        Me.txtRemark.TabIndex = 18
        '
        'txtModuleMark
        '
        Me.txtModuleMark.Location = New System.Drawing.Point(626, 63)
        Me.txtModuleMark.Name = "txtModuleMark"
        Me.txtModuleMark.ReadOnly = True
        Me.txtModuleMark.Size = New System.Drawing.Size(126, 38)
        Me.txtModuleMark.TabIndex = 17
        '
        'txtModuleGrade
        '
        Me.txtModuleGrade.Location = New System.Drawing.Point(268, 130)
        Me.txtModuleGrade.Name = "txtModuleGrade"
        Me.txtModuleGrade.ReadOnly = True
        Me.txtModuleGrade.Size = New System.Drawing.Size(86, 38)
        Me.txtModuleGrade.TabIndex = 16
        '
        'txtCAMark
        '
        Me.txtCAMark.Location = New System.Drawing.Point(226, 63)
        Me.txtCAMark.Name = "txtCAMark"
        Me.txtCAMark.ReadOnly = True
        Me.txtCAMark.Size = New System.Drawing.Size(126, 38)
        Me.txtCAMark.TabIndex = 15
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label10.Location = New System.Drawing.Point(404, 133)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(112, 30)
        Me.Label10.TabIndex = 11
        Me.Label10.Text = "Remarks"
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label9.Location = New System.Drawing.Point(404, 71)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(180, 30)
        Me.Label9.TabIndex = 10
        Me.Label9.Text = "Module Marks"
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label8.Location = New System.Drawing.Point(42, 133)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(180, 30)
        Me.Label8.TabIndex = 9
        Me.Label8.Text = "Module Grade"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label7.Location = New System.Drawing.Point(42, 71)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(122, 30)
        Me.Label7.TabIndex = 8
        Me.Label7.Text = "CA Marks"
        '
        'gbCAAndExam
        '
        Me.gbCAAndExam.Controls.Add(Me.btnConfirm)
        Me.gbCAAndExam.Controls.Add(Me.txtExamMark)
        Me.gbCAAndExam.Controls.Add(Me.txtQuizMark)
        Me.gbCAAndExam.Controls.Add(Me.Label6)
        Me.gbCAAndExam.Controls.Add(Me.Label5)
        Me.gbCAAndExam.Controls.Add(Me.txtProjectMark)
        Me.gbCAAndExam.Controls.Add(Me.txtTestMark)
        Me.gbCAAndExam.Controls.Add(Me.Label4)
        Me.gbCAAndExam.Controls.Add(Me.Label3)
        Me.gbCAAndExam.Controls.Add(Me.lblCAComponent)
        Me.gbCAAndExam.Location = New System.Drawing.Point(34, 146)
        Me.gbCAAndExam.Name = "gbCAAndExam"
        Me.gbCAAndExam.Size = New System.Drawing.Size(850, 376)
        Me.gbCAAndExam.TabIndex = 1
        Me.gbCAAndExam.TabStop = False
        Me.gbCAAndExam.Text = "Input CA and Exam Marks"
        '
        'btnConfirm
        '
        Me.btnConfirm.Location = New System.Drawing.Point(635, 301)
        Me.btnConfirm.Name = "btnConfirm"
        Me.btnConfirm.Size = New System.Drawing.Size(150, 46)
        Me.btnConfirm.TabIndex = 6
        Me.btnConfirm.Text = "Confirm"
        Me.btnConfirm.UseVisualStyleBackColor = True
        '
        'txtExamMark
        '
        Me.txtExamMark.Location = New System.Drawing.Point(585, 212)
        Me.txtExamMark.Name = "txtExamMark"
        Me.txtExamMark.Size = New System.Drawing.Size(200, 38)
        Me.txtExamMark.TabIndex = 14
        '
        'txtQuizMark
        '
        Me.txtQuizMark.Location = New System.Drawing.Point(585, 143)
        Me.txtQuizMark.Name = "txtQuizMark"
        Me.txtQuizMark.Size = New System.Drawing.Size(200, 38)
        Me.txtQuizMark.TabIndex = 13
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label6.Location = New System.Drawing.Point(447, 220)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(81, 30)
        Me.Label6.TabIndex = 12
        Me.Label6.Text = "Exam:"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label5.Location = New System.Drawing.Point(447, 151)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(110, 30)
        Me.Label5.TabIndex = 11
        Me.Label5.Text = "Quizzes:"
        '
        'txtProjectMark
        '
        Me.txtProjectMark.Location = New System.Drawing.Point(152, 217)
        Me.txtProjectMark.Name = "txtProjectMark"
        Me.txtProjectMark.Size = New System.Drawing.Size(200, 38)
        Me.txtProjectMark.TabIndex = 10
        '
        'txtTestMark
        '
        Me.txtTestMark.Location = New System.Drawing.Point(152, 143)
        Me.txtTestMark.Name = "txtTestMark"
        Me.txtTestMark.Size = New System.Drawing.Size(200, 38)
        Me.txtTestMark.TabIndex = 9
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label4.Location = New System.Drawing.Point(32, 220)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(100, 30)
        Me.Label4.TabIndex = 8
        Me.Label4.Text = "Project:"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label3.Location = New System.Drawing.Point(32, 151)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(67, 30)
        Me.Label3.TabIndex = 7
        Me.Label3.Text = "Test:"
        '
        'lblCAComponent
        '
        Me.lblCAComponent.AutoSize = True
        Me.lblCAComponent.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.lblCAComponent.Location = New System.Drawing.Point(54, 59)
        Me.lblCAComponent.Name = "lblCAComponent"
        Me.lblCAComponent.Size = New System.Drawing.Size(207, 30)
        Me.lblCAComponent.TabIndex = 6
        Me.lblCAComponent.Text = "CA Components:"
        '
        'GroupBox4
        '
        Me.GroupBox4.Controls.Add(Me.ltbStudents)
        Me.GroupBox4.Controls.Add(Me.btnFind)
        Me.GroupBox4.Controls.Add(Me.txtFindStudent)
        Me.GroupBox4.Location = New System.Drawing.Point(1001, 62)
        Me.GroupBox4.Name = "GroupBox4"
        Me.GroupBox4.Size = New System.Drawing.Size(502, 1053)
        Me.GroupBox4.TabIndex = 3
        Me.GroupBox4.TabStop = False
        Me.GroupBox4.Text = "Student Record"
        '
        'ltbStudents
        '
        Me.ltbStudents.FormattingEnabled = True
        Me.ltbStudents.ItemHeight = 30
        Me.ltbStudents.Location = New System.Drawing.Point(36, 51)
        Me.ltbStudents.Name = "ltbStudents"
        Me.ltbStudents.Size = New System.Drawing.Size(422, 874)
        Me.ltbStudents.TabIndex = 2
        '
        'btnFind
        '
        Me.btnFind.Location = New System.Drawing.Point(352, 974)
        Me.btnFind.Name = "btnFind"
        Me.btnFind.Size = New System.Drawing.Size(106, 46)
        Me.btnFind.TabIndex = 1
        Me.btnFind.Text = "Find"
        Me.btnFind.UseVisualStyleBackColor = True
        '
        'txtFindStudent
        '
        Me.txtFindStudent.Location = New System.Drawing.Point(36, 979)
        Me.txtFindStudent.Name = "txtFindStudent"
        Me.txtFindStudent.Size = New System.Drawing.Size(282, 38)
        Me.txtFindStudent.TabIndex = 0
        '
        'GroupBox5
        '
        Me.GroupBox5.Controls.Add(Me.txtCountOfF)
        Me.GroupBox5.Controls.Add(Me.txtModuleAvg)
        Me.GroupBox5.Controls.Add(Me.Label14)
        Me.GroupBox5.Controls.Add(Me.Label13)
        Me.GroupBox5.Controls.Add(Me.txtCountOfA)
        Me.GroupBox5.Controls.Add(Me.txtNumOfStudents)
        Me.GroupBox5.Controls.Add(Me.Label12)
        Me.GroupBox5.Controls.Add(Me.Label11)
        Me.GroupBox5.Controls.Add(Me.btnShowStatistic)
        Me.GroupBox5.Location = New System.Drawing.Point(41, 867)
        Me.GroupBox5.Name = "GroupBox5"
        Me.GroupBox5.Size = New System.Drawing.Size(922, 248)
        Me.GroupBox5.TabIndex = 4
        Me.GroupBox5.TabStop = False
        Me.GroupBox5.Text = "Module Statistics"
        '
        'txtCountOfF
        '
        Me.txtCountOfF.Location = New System.Drawing.Point(731, 174)
        Me.txtCountOfF.Name = "txtCountOfF"
        Me.txtCountOfF.ReadOnly = True
        Me.txtCountOfF.Size = New System.Drawing.Size(95, 38)
        Me.txtCountOfF.TabIndex = 25
        '
        'txtModuleAvg
        '
        Me.txtModuleAvg.Location = New System.Drawing.Point(731, 116)
        Me.txtModuleAvg.Name = "txtModuleAvg"
        Me.txtModuleAvg.ReadOnly = True
        Me.txtModuleAvg.Size = New System.Drawing.Size(116, 38)
        Me.txtModuleAvg.TabIndex = 24
        '
        'Label14
        '
        Me.Label14.AutoSize = True
        Me.Label14.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label14.Location = New System.Drawing.Point(563, 182)
        Me.Label14.Name = "Label14"
        Me.Label14.Size = New System.Drawing.Size(132, 30)
        Me.Label14.TabIndex = 23
        Me.Label14.Text = "Count of F"
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label13.Location = New System.Drawing.Point(489, 124)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(206, 30)
        Me.Label13.TabIndex = 22
        Me.Label13.Text = "Module Average"
        '
        'txtCountOfA
        '
        Me.txtCountOfA.Location = New System.Drawing.Point(318, 179)
        Me.txtCountOfA.Name = "txtCountOfA"
        Me.txtCountOfA.ReadOnly = True
        Me.txtCountOfA.Size = New System.Drawing.Size(132, 38)
        Me.txtCountOfA.TabIndex = 21
        '
        'txtNumOfStudents
        '
        Me.txtNumOfStudents.Location = New System.Drawing.Point(362, 116)
        Me.txtNumOfStudents.Name = "txtNumOfStudents"
        Me.txtNumOfStudents.ReadOnly = True
        Me.txtNumOfStudents.Size = New System.Drawing.Size(88, 38)
        Me.txtNumOfStudents.TabIndex = 19
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label12.Location = New System.Drawing.Point(147, 187)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(136, 30)
        Me.Label12.TabIndex = 20
        Me.Label12.Text = "Count of A"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Font = New System.Drawing.Font("Microsoft JhengHei UI", 9.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point)
        Me.Label11.Location = New System.Drawing.Point(34, 119)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(249, 30)
        Me.Label11.TabIndex = 19
        Me.Label11.Text = "Number of Students"
        '
        'btnShowStatistic
        '
        Me.btnShowStatistic.Location = New System.Drawing.Point(581, 39)
        Me.btnShowStatistic.Name = "btnShowStatistic"
        Me.btnShowStatistic.Size = New System.Drawing.Size(238, 46)
        Me.btnShowStatistic.TabIndex = 15
        Me.btnShowStatistic.Text = "Show Statistics"
        Me.btnShowStatistic.UseVisualStyleBackColor = True
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(14.0!, 30.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1520, 1147)
        Me.Controls.Add(Me.GroupBox5)
        Me.Controls.Add(Me.GroupBox4)
        Me.Controls.Add(Me.GroupBox1)
        Me.Name = "Form1"
        Me.Text = "Calculation of Module Grade"
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.gbModuleResult.ResumeLayout(False)
        Me.gbModuleResult.PerformLayout()
        Me.gbCAAndExam.ResumeLayout(False)
        Me.gbCAAndExam.PerformLayout()
        Me.GroupBox4.ResumeLayout(False)
        Me.GroupBox4.PerformLayout()
        Me.GroupBox5.ResumeLayout(False)
        Me.GroupBox5.PerformLayout()
        Me.ResumeLayout(False)

    End Sub

    Friend WithEvents GroupBox1 As GroupBox
    Friend WithEvents gbModuleResult As GroupBox
    Friend WithEvents gbCAAndExam As GroupBox
    Friend WithEvents GroupBox4 As GroupBox
    Friend WithEvents GroupBox5 As GroupBox
    Friend WithEvents btnClearAll As Button
    Friend WithEvents txtName As TextBox
    Friend WithEvents Label1 As Label
    Friend WithEvents txtRemark As TextBox
    Friend WithEvents txtModuleMark As TextBox
    Friend WithEvents txtModuleGrade As TextBox
    Friend WithEvents txtCAMark As TextBox
    Friend WithEvents Label10 As Label
    Friend WithEvents Label9 As Label
    Friend WithEvents Label8 As Label
    Friend WithEvents Label7 As Label
    Friend WithEvents btnConfirm As Button
    Friend WithEvents txtExamMark As TextBox
    Friend WithEvents txtQuizMark As TextBox
    Friend WithEvents Label6 As Label
    Friend WithEvents Label5 As Label
    Friend WithEvents txtProjectMark As TextBox
    Friend WithEvents txtTestMark As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents lblCAComponent As Label
    Friend WithEvents Label12 As Label
    Friend WithEvents Label11 As Label
    Friend WithEvents btnShowStatistic As Button
    Friend WithEvents ltbStudents As ListBox
    Friend WithEvents btnFind As Button
    Friend WithEvents txtFindStudent As TextBox
    Friend WithEvents txtCountOfF As TextBox
    Friend WithEvents txtModuleAvg As TextBox
    Friend WithEvents Label14 As Label
    Friend WithEvents Label13 As Label
    Friend WithEvents txtCountOfA As TextBox
    Friend WithEvents txtNumOfStudents As TextBox
End Class
