var drawing = false;
var random_n;

function setup() {
    createCanvas(400, 400);
}

mouseOver = function(){
   drawing = true;
};

mouseOut = function(){
    drawing = false;
};

mouseClicked = function(){
    if(mouseButton === LEFT){
        drawing = true;
    } 
    if(mouseButton === RIGHT){
        drawing = false;
    } 

};
//frameRate(60);
draw = function() {
    
    if(drawing===true){
        fill( round(random(10,255)), round(random(10,255)),round(random(10,255)) );
        stroke( round(random(10,255)), round(random(10,255)),round(random(10,255)) );
        ellipse(mouseX, mouseY, round(random(1,100)),round(random(1,100)));

    }
};
