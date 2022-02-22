Import-Module '.\User.psm1'

$trainees = Get-TraineeList '.\Intake20.csv'
foreach($trainee in $trainees){
    Set-UserHomeFolder $trainee.LoginID
}

$trainers = Get-TrainerList '.\Trainers.txt'
foreach($trainer in $trainers){
    Set-UserHomeFolder $trainer.LoginID
}