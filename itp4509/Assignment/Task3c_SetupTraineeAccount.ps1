Import-Module '.\User.psm1'

$trainees = Get-TraineeList '.\Intake20.csv'
New-Trainees $trainees
