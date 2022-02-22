Import-Module '.\User.psm1'

$trainers = Get-TrainerList '.\Trainers.txt'
New-Trainers $trainers