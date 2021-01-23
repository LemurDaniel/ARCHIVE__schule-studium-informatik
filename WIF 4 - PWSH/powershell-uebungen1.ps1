Start-Process powershell

Get-Alias | Measure-Object

Get-Process -


Get-Service -DisplayName Windows*

get-eventlog -list

get-command -verb remove

Get-Command -name -remove-*

restart-computer

Get-Module -ListAvailable

Restart-Service bits -PassThru ###

ls temp -path C:\temp -Recurse -file

get-help about_regular_expressions

Get-EventLog -LogName System -Newest 10 -EntryType Error



Get-Alias | Measure-Object

Get-Process | Where { $_.WorkingSet.CompareTo(50Mb).Equals(1)} | Sort-Object -Property WS -Descending | Select-Object -Property ProcessName, WorkingSet

Get-Process | Where { $_.WorkingSet -ge 50MB } | Sort-Object -Property WS -Descending | Select-Object -Property ProcessName, WorkingSet


ls C:\temp | Where { $_.LastWriteTime.CompareTo( (Get-Date).AddDays(-1) ).Equals(1)}

Get-Process | Select-Object -Property ProcessName ,WorkingSet | Out-GridView

Get-Process | Format-wide