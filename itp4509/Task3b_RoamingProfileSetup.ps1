$folderPath = "C:\Profiles"
New-Item $folderPath -ItemType Directory

$acl = Get-Acl $folderPath
$acl.SetAccessRuleProtection($True, $False)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("SYSTEM", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
$acl.SetAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Administrators", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("CREATOR OWNER", "FullControl","ContainerInherit,ObjectInherit","InheritOnly", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("Trainees", "ReadAndExecute", "None", "None", "Allow" )
$acl.AddAccessRule($ace)
$ace = New-Object System.Security.AccessControl.FileSystemAccessRule("OnlineTrainer", "ReadAndExecute", "None", "None", "Allow" )
$acl.AddAccessRule($ace)

Set-Acl $folderPath -AclObject $acl

New-SmbShare -Name "Profiles$" -Path $folderPath -FullAccess "Everyone"