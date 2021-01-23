Get-PSDrive

# Excercise 1
Get-PSDrive -PSProvider Registry


# Excercise 2
ls Cert:\LocalMachine | Measure-Object | Select-Object count


regedit.exe
# Excercise 3    Kein RegOwner vorhanden ???
ls HKLM:\SOFTWARE\Microsoft\"Windows NT"


# Excercise 4
Get-Command -CommandType Function | Measure-Object | Select-Object count


# Excercise 5
ls HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall | Format-List

# Challenge
ls HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall | Where { $_.Name -match "[0-9|A-F]{8}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{12}" } | Format-List
ls HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall | Where { $_.Name -match "[0-9|A-F]{8}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{12}" } | Select-Object name| Sort-Object name | Format-List

# All GUIDS
ls HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall | Where { $_.Name -match "[0-9|A-F]{8}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{12}" } |  % { $Matches}  | Sort-Object Value 

# GUID: XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX 8-4-4-4-12
#Regex Test
"\{B9998A13-5563-496C-B95E-597FFC70B670}" -match "[0-9|A-F]{8}[-[0-9|A-F]{4}]{3}-[0-9|A-F]{12}" | %{ $Matches }
"\{B9998A13-5563-496C-B95E-597FFC70B670}" -match "[0-9|A-F]{8}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{4}-[0-9|A-F]{12}" | %{ $Matches }



# Excercise 6 Sind nicht vorhanden
$key = Get-ItemProperty HKLM:\SOFTWARE\Microsoft\"Windows NT"\CurrentVersion | Select-Object ??
Set-ItemProperty HKLM:\SOFTWARE\Microsoft\"Windows NT"\CurrentVersion ?? Test
Get-ItemProperty HKLM:\SOFTWARE\Microsoft\"Windows NT"\CurrentVersion | Select-Object ??
Set-ItemProperty HKLM:\SOFTWARE\Microsoft\"Windows NT"\CurrentVersion ?? $key
Get-ItemProperty HKLM:\SOFTWARE\Microsoft\"Windows NT"\CurrentVersion | Select-Object ??



# Excercise 7 Sind nicht vorhanden
Get-PSProvider