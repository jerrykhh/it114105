

#Create Home Folder Directory
$folderPath = "C:\personal"
New-Item $folderPath -ItemType Directory

$acl = Get-Acl $folderPath
$acl.SetAccessRuleProtection($True, $False)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("SYSTEM", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
$acl.SetAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Administrators", "FullControl", "None", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("CREATOR OWNER", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Trainees", "ReadData", "None", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("OnlineTrainer", "ReadData", "None", "None", "Allow" )
$acl.AddAccessRule($ace)

Set-Acl $folderPath -AclObject $acl

New-SmbShare -Name "Personal" -Path $folderPath -FullAccess "Authenticated Users"

#Create New Quota Template

$action = New-FsrmAction Event -EventType Information -Body "The user [File Owner] is about to reach the end of his available storage."
$Threshold = New-FsrmQuotaThreshold -Percentage 75 -Action $action
New-FsrmQuotaTemplate -Name "HomeFolder_Quota" -Size 8GB -Threshold $Threshold