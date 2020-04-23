function Check-EmailAddressFormat([String]$email){
    
    $check = $true
    Try{
        New-Object System.Net.Mail.MailAddress($email)
    }Catch{
        $check = $false
    }

    Write-Output $check

}

function Check-PasswordLength([String]$pwd){

    $check = $pwd -match ".{8,}"
    Write-Output $check

}

function Check-PasswordContainDigit([String]$pwd){

    $check = $pwd -match "\d"
    Write-Output $check

}

function Check-PasswordContainSymbol([String]$pwd){

    $check = $pwd -match "[~!@#$%^&*()\\\-=+<>\?]"
    Write-Output $check

}

function Check-PhoneNumberFormat([String]$phone){

    $check = $phone -match "^\d{8}$"
    Write-Output $check

}

function Check-TraineeInformation([Object]$trainee){

    $valid = 1

    if(!(Check-PhoneNumberFormat $trainee.Telephone)){
        "Trainee " + $trainee.FullName + " (" + $trainee.LoginId + ") inputted error phone number(" + $trainee.Telephone + ")" >> .\InvalidPhone.txt
        $valid = 0
    }

    if(!(Check-EmailAddressFormat $trainee.Email)){
        "Trainee " + $trainee.FullName + " (" + $trainee.LoginId + ") inputted error email address(" + $trainee.Email + ")" >> .\InvalidEmail.txt
        $valid = 0
    }

    if(!(Check-PasswordLength $trainee.Password)){
        "Trainee " + $trainee.FullName + " (" + $trainee.LoginId + ") inputted password is not meet the password length requirement" >> .\InvalidPassword.txt
        $valid = 0

    }
    if(!(Check-PasswordContainDigit $trainee.Password)){
        "Trainee " + $trainee.FullName + " (" + $trainee.LoginId + ") inputted password is not contains a digit" >> .\InvalidPassword.txt
        $valid = 0
    }

    if(!(Check-PasswordContainSymbol $trainee.Password)){
        "Trainee " + $trainee.FullName + " (" + $trainee.LoginId + ") inputted password is not contains a special symbol" >> .\InvalidPassword.txt
        $valid = 0

    }

    Write-Output $valid


}



Export-ModuleMember -Function Check-EmailAddressFormat
Export-ModuleMember -Function Check-PasswordLength
Export-ModuleMember -Function Check-PasswordContainDigit
Export-ModuleMember -Function Check-PasswordContainSymbol
Export-ModuleMember -Function Check-PhoneNumberFormat
Export-ModuleMember -Function Check-TraineeInformation