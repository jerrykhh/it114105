Import-Module '.\User.psm1'

$trainers = Get-TrainerList '.\Trainers.txt'
foreach($trainer in $trainers){
    Set-TrainerAdditionalFolder $trainer.LoginID
}