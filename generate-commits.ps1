$count = 59
while ($count -lt 84) {
    $path = "C:\Users\Daniel Notebook\Documents\Git\Programmieren-Schule-Studium\WIF 3 - ProjektFilmDB\Backup History\"
    $version_path = $path+$count.ToString()
    $src = $version_path+"\Filmdatenbank\"

    $dest = "C:\Users\Daniel Notebook\Documents\Git\schule-studium-programming\"

    $dst1 = $dest+"src"

    $dst2 = $dest+"bin"

    Remove-Item -Path $dst1 -Force -Recurse
    Remove-Item -Path $dst2 -Force -Recurse

    ls $src | Copy-Item -Recurse -Destination $dest 

    cd "C:\Users\Daniel Notebook\Documents\Git\schule-studium-programming\"

    git add -A

    Start-Sleep 2

    $message = "Version "+$count

    git commit -m $message

    $count = $count+1
} 