function New-TraineesComputer($lab, $computer) {
    
    for($i = 1; $i -le $computer; $i++){
        $name = "Lab" + $lab + "-S"+ $i.ToString("00")
        New-ADComputer -Name $name -SAMAccountName $name -PATH "OU=Trainees, OU=Workstation, DC=OnlineB10, DC=hk"
    
    }

}

function New-TrainersComputer($lab, $computer){
    
    for($i = 1; $i -le $computer; $i++){
        $name = "Lab" + $lab + "-T"+ $i.ToString("00")
        New-ADComputer -Name $name -SAMAccountName $name -PATH "OU=Trainers, OU=Workstation, DC=OnlineB10, DC=hk"
    
    }

}