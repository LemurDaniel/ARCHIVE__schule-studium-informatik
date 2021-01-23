var xPos = -10;
var yPos = 40;
var x = 0;

function setup() {
    createCanvas(400, 400);
}

draw = function() {
    background(29, 40, 115);
    
    xPos+= 4;
    yPos+= 1;
    
    x--;
    
    noStroke();
    fill(97, 91, 91);
    rect(30,150,90,249);
    rect(180,230,190,170);
    
    fill(255, 255, 0);
    
     //skyscrapper 1
    var x1 = 40;
    var y1 = 160;
    
    while(true){
        rect(x1,y1,10,10);
        x1 += 20;
        if(x1>100){y1 += 20;x1 = 40;}
        if(y1>360){break;}
    }
    
    //skyscrapper 2
    var x2 = 190;
    var y2 = 240;
    
    while(true){
        rect(x2,y2,10,10);
        x2 += 20;
        if(x2>350){y2 += 20;x2 = 190;}
        if(y2>370){break;}
    }
    
    fill(255, 242, 0);
    ellipse(xPos, yPos, 10, 10);
    triangle(412+x,163+x, 395+x,177+x, 381+x,148+x);
};



