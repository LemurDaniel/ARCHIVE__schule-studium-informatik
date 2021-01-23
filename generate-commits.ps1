$dest = "C:\Users\Daniel Notebook\Documents\Git\schule-studium-programming\Spiel Mario\"
$src = "C:\Users\Daniel Notebook\Documents\Git\Programmieren-Schule-Studium\Java10c Projekte\PROJEKT - Mario\Spiel Mario - "
$count = 3
$target_count = 23

cd "C:\Users\Daniel Notebook\Documents\Git\schule-studium-programming"

while($count -lt $target_count){
    Remove-Item -Path $dest -Recurse

    New-Item -Path $dest -ItemType Directory

    $item = $src+$count

    ls $item | Copy-Item -Recurse -Destination $dest

    git add -A

    Start-Sleep 1

    $message = "Version "+$count

    git commit -m $message

$count = $count+1
}
