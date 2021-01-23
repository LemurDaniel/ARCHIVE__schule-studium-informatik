var drawTomato = function(){
    createCanvas(600, 600);
    background(255, 255, 255);
    
    // tomato
    noStroke();
    fill(224, 90, 90);
    ellipse(150, 200, 150, 150);
    ellipse(212, 200, 150, 150);
    
    // stem
    fill(48, 130, 31);
    rect(176, 103, 12, 32); 
    
    };
    

function setup() {

    drawTomato();
    
    mouseClicked = function() {
        fill(255, 255, 255);
        ellipse(mouseX,mouseY,60,60);
        //playSound(getSound("rpg/hit-splat"));
    };
    
    mouseOut = function(){
        drawTomato();
    };
  
}