
function setup() {
    createCanvas(600,600);
    background(10, 250, 250);

    var housewidth = 280;
    var househeight = 200;
    var houseX = 60;
    var houseY = 150;

    fill(135, 2, 2);
    triangle(houseX+housewidth/2, househeight/4, houseX+housewidth, houseY, houseX, houseY);

    fill(255, 255, 255);
    rect(60, 150, housewidth, househeight);


    var x = houseX;
    var y = houseY;
    var count = 1;
    fill(135, 2, 2);
    while(y < houseY+househeight){
        rect(x,y,16,8);
        x += 16;
        if(x > houseX+housewidth-16){
            if(count % 2 === 0){
                x = houseX;
            } else {
                rect(x,y,8,8);
                x = houseX + 8; 
                if(y < houseY+househeight-8){
                    rect(houseX,y+8,8,8);
                }
            }
            
            y += 8;
            count++;
        }
    } 

    var doorheight = househeight/3;
    var doorWidth = househeight/4;
    var doorX = houseX + housewidth/2 - doorWidth/2;
    var doorY = houseY + househeight - doorheight;
    fill(120, 80, 19);
    rect(doorX, doorY, doorWidth, doorheight);

    var a = housewidth/14;
    x = houseX + a;
    y = houseY + a;
    var Size = househeight/8;
    fill(255, 161, 20);
    while(y < houseY+househeight-40){
        if(x > doorX+Size+doorWidth || x < doorX-Size || y < doorY-a){
            rect(x,y,Size,Size);
        }
        x += Size + 10;
        if(x > houseX+housewidth-Size){
            y += Size + 10;
            x = houseX + a;
        }
    }

    x = -1;

    while(x < 400){
        // might not work in Chrome
        image(loadImage('js/grass-scaled.png'), x, 320, 101, 80);
        x += 80;
    }
}
