$dest = "C:\Users\Daniel Notebook\Documents\Git\Programmieren-Schule-Studium\Spiel Mario\"
$src = "C:\Users\Daniel Notebook\Documents\Git\Programmieren-Schule-Studium\Java10c Projekte\PROJEKT - Mario\Spiel Mario - "
$count = 2
$target_count = 22

cd "C:\Users\Daniel Notebook\Documents\Git\Programmieren-Schule-Studium"

while($count -lt $target_count){
    Remove-Item -Path $dest -Recurse

    New-Item -Path $dest -ItemType Directory

    $item = $src+$count

    ls $item | Copy-Item -Recurse -Destination $dest

    git add -A

    Start-Sleep 1

    $message = "Version "+$count

    git commit -m $message

    Remove-Item $item -Force -Recurse

$count = $count+1
}
