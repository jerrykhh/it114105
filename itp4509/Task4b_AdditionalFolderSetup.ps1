$folderPath = "C:\DropAndPick"
New-Item $folderPath -ItemType Directory

$acl = Get-Acl $folderPath
$acl.SetAccessRuleProtection($True, $False)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("SYSTEM", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
$acl.SetAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Administrators", "FullControl", "None", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("CREATOR OWNER", "FullControl","ContainerInherit,ObjectInherit","InheritOnly", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Trainees", "ReadData", "None", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("OnlineTrainer", "ReadData", "None", "None", "Allow" )
$acl.AddAccessRule($ace)

Set-Acl $folderPath -AclObject $acl

New-SmbShare -Name "DropAndPick" -Path $folderPath -FullAccess "Authenticated Users"