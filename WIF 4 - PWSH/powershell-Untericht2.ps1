

// Frage 1

(Get-ADOrganizationalUnit -Filter 'Name -like "Studenten"').DistinguishedName
(Get-ADOrganizationalUnit -Filter 'Name -like "WIF0"').DistinguishedName

New-ADOrganizationalUnit -Name WIF1 -Path (Get-ADOrganizationalUnit -Filter 'Name -like "Studenten"').DistinguishedName
New-ADUser -Name "Bugs Bunny1" -Path (Get-ADOrganizationalUnit -Filter 'Name -like "WIF1"').DistinguishedName


// Frage 2
// DNS Einträge via GUI und Powershell
// neue Zone: demozone.de
// A Einträge Win10-01.demozone.de => 10.0.0.1

Add-DnsServerPrimaryZone -Name demozone.de -ReplicationScope Domain
Add-DnsServerResourceRecordA -Name  Win10-01 -ZoneName Test.de -AllowUpdateAny -IPv4Address 10.0.0.1


//Frage 3
// Organizational Unit via Powershell entfernen.

get-command -noun ADORG*
Get-ADOrganizationalUnit -Filter 'Name -like "Studenten"' | Set-adobject -ProtectedFromAccidentalDeletion $false -PassThru | Remove-ADOrganizationalUnit -Recursive

Remove-ADOrganizationalUnit -Identity (Get-ADOrganizationalUnit -Filter 'Name -like "Studenten"').DistinguishedName 