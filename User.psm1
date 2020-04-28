Import-Module '.\CheckInf.psm1'

function New-Trainees([Object] $trainees){

    "Create New Trainee on " + (Get-Date) > .\InvalidPassword.txt
    "Create New Trainee on " + (Get-Date) > .\InvalidPhone.txt
    "Create New Trainee on " + (Get-Date) > .\InvalidEmail.txt

    foreach($trainee in $trainees){
        
        $enable = Check-TraineeInformation $trainee
        
        New-ADUser -Name $trainee.LoginID -GivenName $trainee.FirstName -Surname $trainee.LastName -EmailAddress $trainee.Email -OfficePhone $trainee.Telephone -AccountPassword (ConvertTo-SecureString ($trainee.Password) -AsPlainText -Force) -Enabled $enable -Description "Traniee" -ProfilePath "\\OnlineDC\Profiles$\%username%" -Path "OU=Trainees, OU=Workstation, DC=OnlineB10, DC=hk"
        Add-ADGroupMember -Identity "Trainees" -Members $trainee.LoginID
    
    }
    

}



function New-Trainers([Object] $trainers){

    foreach($trainer in $trainers){
        #Get default Password
        $defaultPwd = Get-TrainerDefaultPassword $trainer.LastName $trainer.HKID

        #New Trainer User
        New-ADUser -Name $trainer.LoginID -GivenName $trainer.FirstName -Surname $trainer.LastName -OfficePhone $trainer.Telephone -AccountPassword (ConvertTo-SecureString ($defaultPwd) -AsPlainText -Force) -Enabled $True -Description "Tranier" -ProfilePath "\\OnlineDC\Profiles$\%username%" -Path "OU=Trainers, OU=Workstation, DC=OnlineB10, DC=hk"
        
        #Add User to Group
        Add-ADGroupMember -Identity "OnlineTrainer" -Members $trainer.LoginID

    }

}

function Get-TrainerDefaultPassword([String]$trainerLastName, [String]$trainHKID){

    $pwd = $trainerLastName.ToLower() + "$" + $trainHKID.Substring(0, $trainHKID.Length-3)
    
    Write-Output $pwd

}

function Get-TrainerList([String] $path) {
      #Get Data via Txt file
      $data = Get-Content $path
      $dictory = @()
      $trainers = @()
      for($i = 0; $i -lt $data.Length; $i++){
          $rowData = $data[$i].Split(",")
          if(!($rowData.Length -eq 1)){
              # Add the first row to dictory array
              if($dictory.Length -eq 0){
                  $rowData = $rowData.Replace(' ','')
                  foreach($dict in $rowData){
                      $dictory += $dict
                  }
  
              }else{
                  # Add data assoicate the dictory
                  if($dictory.Length -eq $rowData.Length){
                      $trainer = $null
                      for($count = 0; $count -lt $dictory.Length; $count++){
                          $trainer += @{$dictory[$count] = $rowData[$count]}
                      }
                      
                      $trainers += $trainer

                  }else{
                      Write-Host "Data Row " + ($i + 1) + "Error" 
                      
                  }
                  
  
              }
              
          }
             
      }
      
      Write-Output $trainers

}

function Get-TraineeList([String] $path){
    
    $trainees = Import-Csv -Path $path 

    Write-Output $trainees

}


function Set-UserHomeFolder([String]$username){
    
    Set-ADUser $username -HomeDirectory "\\OnlineDC\personal\$username" -HomeDrive "F:"

    #Create User Home Folder
    $folderPath = "C:\personal\$($username)"
    New-Item $folderPath -ItemType Directory

    #Set Permission
    $acl = Get-Acl $folderPath
    $acl.SetAccessRuleProtection($True, $False)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("SYSTEM", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.SetAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Administrators", "FullControl", "ContainerInherit, ObjectInherit","InheritOnly", "Allow" )
    $acl.AddAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule($username, "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.AddAccessRule($ace)

    Set-Acl $folderPath -AclObject $acl
    New-FSRMQuota -Path $folderPath -Size 8GB -Template "HomeFolder_Quota"
}

function Set-TrainerAdditionalFolder([String] $username){

    $folderPath = "C:\DropAndPick\$username"
    New-Item $folderPath -ItemType Directory

    $acl = Get-Acl $folderPath
    $acl.SetAccessRuleProtection($True, $False)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("SYSTEM", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.SetAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Administrators", "FullControl", "None", "None", "Allow" )
    $acl.AddAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule($username, "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.AddAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Trainees", "ListDirectory, CreateDirectories, CreateFiles", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.AddAccessRule($ace)
    $ace = New-Object System.Security.AccessControl.FileSystemAccessRule("OnlineTrainer", "ListDirectory, CreateDirectories, CreateFiles", "ContainerInherit, ObjectInherit", "None", "Allow" )
    $acl.AddAccessRule($ace)

    Set-Acl $folderPath -AclObject $acl

    New-FSRMQuota -Path $folderPath -Size 40GB

}

