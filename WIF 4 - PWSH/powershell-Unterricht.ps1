("").GetType().FullName;
(42).GetType().FullName;
(1tb).GetType().FullName;


$a = 'Expanded String'
'Working with a $a'
"Working with an $a"



$a = Get-Service BITS
$b = "$($a.Name is $a.Status)"