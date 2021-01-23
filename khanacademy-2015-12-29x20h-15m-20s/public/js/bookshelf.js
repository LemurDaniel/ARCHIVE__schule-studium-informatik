var shelf = [];
var ScreenWidth = 600; //moust be a number like 100 or 200

var newBook = function(title, author, image, color, stars, recomend){
    
    if(stars > 4){
        stars = 4;
    }
    
    var book = {
        title: title,
        author: author,
        image: image,
        color: color,
        stars: stars,
        recommend: recomend
    };
    
    return book;
};


function setup() {

    shelf.push(newBook("The Giver" , "Someone Special", null , color(186, 130, 0), 0, false));
    shelf.push(newBook("How to   ARCHER", "Archer Sterling" , null, color(153, 9, 9), 4, true));
    shelf.push(newBook("THIS IS IT", "The Apocalpypse" , null, color(173, 168, 92), 4, true));
    shelf.push(newBook("The great Mysterie", " \n ???" , null, color(7, 148, 173), 1, false));

    shelf.push(newBook("tarms teg ot woH", "not so \nsmart guy" , null, color(7, 148, 173), 3,true));
    shelf.push(newBook("The Taker" , "No one Special", null , color(0, 179, 51), 1, false));
    shelf.push(newBook("How to ROCK", "Davy \nStones" , null, color(143, 142, 148), 2, true));
    shelf.push(newBook("GET SCHWIFTY", " \nRICK" , null, color(219, 149, 75), 4, true));

    shelf.push(newBook("Special Numbers I", "Math \nGuy" , null, color(48, 224, 255), 4,true));
    shelf.push(newBook("Special Numbers II", "Math \nGuy" , null , color(48, 224, 255), 3,true));
    shelf.push(newBook("Special Numbers III", "Math \nGuy" , null, color(48, 224, 255), 2,false));
    shelf.push(newBook("Special Numbers IV", "Math \nGuy" , null, color(48, 224, 255), 4,true));
    shelf.push(newBook("Special Numbers V", "Math \nGuy" , null, color(48, 224, 255), 3,true));
    shelf.push(newBook("Special Numbers VI", "Math \nGuy" , null , color(48, 224, 255), 4,true));
    shelf.push(newBook("Special Numbers VII", "Math \nGuy" , null, color(48, 224, 255), 2,false));
    shelf.push(newBook("Special Numbers VIII", "Math \nGuy" , null, color(48, 224, 255), 4,true));
    shelf.push(newBook("Special Numbers IX", "Math \nGuy" , null, color(48, 224, 255), 2,false));
    shelf.push(newBook("Special Numbers X", "Math \nGuy" , null, color(48, 224, 255), 1,false));

    createCanvas(600, 800);

    var x = 0;
    var y = -1;
    for (var i = 0; i < shelf.length; i++) {
        
        if(i % (ScreenWidth/100) === 0){
            y += 1;
            x = 0;
            fill(173, 117, 33);
            rect(0, y*180 + 120, ScreenWidth, 10);
        }
        
        fill(shelf[i].color);
        rect(x*100 + 5, y*180 + 20, 90, 100);
        rect(x*100 + 5, y*180 + 130, 90, 50);
        fill(0, 0, 0);
        //image(shelf[i].image, x*100 + 63, y*180 + 63, 30, 30);
        text(shelf[i].title,  x*100 + 10, y*180 + 30, 70, 100);
        text(shelf[i].author, x*100 + 10, y*180 + 63, 70, 100);
        
        if(shelf[i].recommend){
            //image(loadImage("space/healthheart"), x*100 + 40, y*180 + 135, 25, 25);
            text("Great book", x*100 + 20, y*180 + 165, 70, 100);
        } else {
            //image(loadImage("avatars/old-spice-man"), x*100 + 40, y*180 + 135, 30, 25);
            text("Meh!!", x*100 + 38, y*180 + 165, 70, 100);
        }
        //println(bookshelf[i].title);
        //println(bookshelf[i].stars);
        //println("----------------------");

        
        for (var ii = 0; ii < shelf[i].stars; ii++) {
            //image(loadImage("cute/Star"), (8 + ii * 20) + x*100, y*180 + 90, 20, 30);
        }
        x++;
    }

}
