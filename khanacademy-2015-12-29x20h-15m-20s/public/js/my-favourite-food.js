var Size = 32;
var line_spacing = 32;
var x = 10;
var y = 40;























var txt = function() {
    textSize(Size);
    var size = Size + line_spacing;
    text("My Favorite Foods: ",x,y);
    text("1. Spaghetti",x,y+size);
    text("2. Fleischküchle",x,y+size*2);
    text("3. Hackbraten",x,y+size*3);
};

var x1 = x;
var y1 = y;
mouseOut = function(){
    x = x1;
    y = y1;
    background(255, 255, 255);
    txt();
};

mouseMoved = function(){
    x = mouseX-Size*4;
    y = mouseY-Size*4;
    background(255, 255, 255);
    txt();
}; 

function setup() {
    createCanvas(400, 400);
    frameRate(30);
}

var s = Size;
draw = function() {
     if(mouseIsPressed && mouseButton === RIGHT && Size > 0){
         fill(random(0,255),random(0,255),random(0,255));
         Size += 0.5;
     } else if(mouseIsPressed && mouseButton === LEFT && Size > 0){
         fill(random(0,255),random(0,255),random(0,255));
         Size -= 0.5;
     } else if(!mouseIsPressed){
        background(255, 255, 255);
        fill(130, 123, 130);
        Size = s;
     }
     txt();
};

