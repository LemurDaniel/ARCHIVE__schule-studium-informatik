help ls 
ls | Get-Member


# Excercise 1
Get-Service -DisplayName windows*


# Excercise 2
Get-EventLog *


# Excercise 3
Get-Command Remove*


# Excercise 4
# Restart-Computer


# Excercise 5
Get-Module -ListAvailable


# Excercise 6
Start-Service -name BITS


# Excercise 7
ls c:\temp -Recurse 


# Excercise 8
Get-Acl -Path C:\WINDOWS\system32\notepad.exe | Format-List


# Excercise 9
help regex


# Excercise 10
Get-EventLog System -Newest 10


# Excercise 11 (PSReadline nicht gefunden)
Get-Module ISE | Get-Member -Name Get*


# Excercise 12
Get-Process powershell* | Select-Object Name, ProductVersion
start-process powershell -PassThru | Select-Object  Name, ProductVersion


# Excercise 13
Start-Process Powershell


# Excercise 14
Get-Alias | Measure-Object | Select-Object -Property Count


# Excercise 15
Get-Process | Where { $_.WS -ge 50MB } | Select-Object ProcessName, WS | Sort-Object WS -Descending


# Excercise 16
ls $env:TEMP -File -Recurse -ErrorAction Ignore | where { $_.LastWriteTime -ge (Get-Date).AddDays(-1) } | Select-Object Name, LastWriteTime, Length | Sort-Object Name


# Excercise 17
ls $Home\documents -File -Recurse | Where { $_.Length -ge 0MB -and $_.LastWriteTime -ge (Get-Date).AddDays(-90) } | Select-Object Name, CreationTime, LastWriteTime, Length
ls $Home\documents -File -Recurse | Where { $_.Length -ge 0MB -and $_.LastWriteTime -ge (Get-Date).AddDays(-90) } | Select-Object Name, CreationTime, LastWriteTime, Length | Export-Csv $Home\Desktop\test.csv 
# (Sort-Variante kann ein paar sekunden dauern)
ls $Home\documents -File -Recurse | Where { $_.Length -ge 1MB -and $_.LastWriteTime -ge (Get-Date).AddDays(-90) } | Select-Object Name, LastWriteTime, Length | Sort-Object Name
import-cs $Home\Desktop\test.csv 



# Excercise 18
ls $env:TEMP -File -Recurse | Group-Object extension | Sort-Object Name -Descending | Select-Object Count, Name


# Excercise 19 (Xml-Datei Erscheint auf Desktop)
Get-Process -IncludeUserName | Select-Object Id, ProcessName, CPU, WS, Handles, UserName | where { $_.username -eq $env:USERDOMAIN+"\"+$env:USERNAME } | Sort-Object Id | Export-Clixml  -Path $Home\Desktop\Processes.xml


# Excercise 20
Import-Clixml $Home\Desktop\Processes.xml | Format-Table  # <- In Console
Import-Clixml $Home\Desktop\Processes.xml | Out-GridView  # <- Grid View
#Dokument löschen
Remove-Item $Home\Desktop\Processes.xml


# Excercise 21
for ($i=1; $i -le 10; $i++) { $rand = Get-Random 50; Write-Host ($rand*$rand) }


# Excercise 22 (Html-Datei Erscheint auf Desktop)
Get-EventLog System -Newest 20| Select-Object EventID, TimeGenerated, EntryType, Source, Message | ConvertTo-Html -body ("<H1><b>Computername: "+$env:USERDOMAIN+"</b></h1>")  > $Home\Desktop\EventlogsSystem.html
#Dokument löschen
Remove-Item $Home\Desktop\EventlogsSystem.html


# Excercise 23 ???
Get-Module 


# Excercise 24 (Json-Datei Erscheint auf Desktop)
Get-Service | where { $_.Status -eq "Running" } | Select-Object -Property * -ExcludeProperty RequiredServices, DependentServices | Format-Table
Get-Service | where { $_.Status -eq "Running" } | Select-Object -Property * -ExcludeProperty RequiredServices, DependentServices | ConvertTo-Json > $Home\Desktop\Services.Json
#Import 
Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json | Format-Table
Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json | Format-List
Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json | Get-Member  # Importiertes und konvertiertes Json vom Typ 'System.Object[]'

[System.Object[]](Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json) | Out-GridView # <- GridView Ansicht

 
#Excercise 24 Variante 2 (Casten in 'System.ServiceProcess.ServiceController'-Objekt)
Get-Service | Select-Object -Property * -ExcludeProperty RequiredServices, DependentServices, ServicesDependedOn | where { $_.Status -eq "Running" }
# Alle anderen Properties außer Name, DisplayName, MachineName und Site sind 'ReadOnlyProperties' und verhindern das Casting
Get-Service | where { $_.Status -eq "Running" } | Select-Object Name, DisplayName, MachineName, Site | ConvertTo-Json > $Home\Desktop\Services.Json

#Import mit Cast
[System.ServiceProcess.ServiceController[]](Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json) | Format-table
[System.ServiceProcess.ServiceController[]](Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json) | Format-List
[System.ServiceProcess.ServiceController[]](Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json) | Get-Member  # Importiertes und konvertiertes Json vom Typ 'System.ServiceProcess.ServiceController'

[System.ServiceProcess.ServiceController[]](Get-Content $Home\Desktop\Services.Json | ConvertFrom-Json) | Out-GridView



Remove-Item $Home\Desktop\Services.Json



# Excercise 25
help Test-NetConnection
Test-NetConnection localhost -Port 80 | Select-Object *


Test-NetConnection www.Google.com | Select-Object *



