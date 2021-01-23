// Get new Fish: Click once on the canvas
// Change Size: Press LEFT mouse while on canvas
// Add Fish to fish tank: Click with your Right mouse button on canvas
// You don't like the fish --> Move out of the canvas to get rid of it

var BubblesAmount = 100;
var FishesAmount = 5;

function random(max){
    return Math.random() * (max - min) + min;
}

var newBubble = function(){
    
    var Bubble = {
        xPos: random(-10,410),
        yPos: 600,
        speed: random(1,3),
        radius: random(5,16),
        wobble: random(-0.5,0.5),
        count: 0,
        color: color(255, 255, 255),
        drawBubble: function(){}, 
        bubble: ""
    };
 

    Bubble.drawBubble = function(){
        noFill();
        strokeWeight(2);
        stroke(242, 242, 242);
        ellipse(this.xPos,this.yPos,this.radius,this.radius);
    };
 
    
    Bubble.bubble = function(){
        this.drawBubble();
        this.yPos -= this.speed;
        this.xPos -= this.wobble;
        this.count++;
        
        if( this.count > 200){
            this.wobble = random(-0.5,0.5);
            this.count = 0;
        }
        
        if(this.yPos < 0+this.radius){
            this.yPos = random(400+this.radius,400+this.radius);
            this.xPos = random(-10,410);
            this.Size = random(1,4);
        }
    };
    
    return Bubble;
};

var newFish = function(){
    
    var fish = {
        posX: random(0,400),
        posY: random(0,380),
        Size: random(15,100),
        Height: 10,
        Length: 0,
        Speed: random(1,10),
        bodyColor: color(random(0,255),random(0,255),random(0,255)),
        tailColor: color(random(0,255),random(0,255),random(0,255)),
        drawMe: "",
        changeSize: "",
        swim: ""
    };
    
    fish.drawMe = function(){
        
        var x = this.posX;
        var y = this.posY;
        var l = this.Size;
        var h = this.Size/3;
        this.Height = h;
        
        noStroke();
        // tail
        fill(this.tailColor);
        var tailWidth = l/5;
        var tailHeight = h/2;
        this.Length = tailWidth + l;
        triangle(x-l/2, y,
                 x-l/2-tailWidth, y-tailHeight,
                 x-l/2-tailWidth, y+tailHeight);
        triangle(x,y-h/3,
                 x-h/2,y-h,
                 x+l/4,y-h/2.5);
         
        triangle(x,y+h/2,
                 x-l/2,y+h/1.4,
                 x-l/4,y+h/3);
        //body
        fill(this.bodyColor);
        ellipse(x, y, l, h);
        // eye
        fill(33, 33, 33);
        ellipse(x+l/4, y, h/4, h/8);
    };
    
    fish.changeSize = function(){
        if(this.Size <= 200){
            this.Size += 1;
        } else {
            this.Size = 15;
        }
    };
    
    fish.swim = function(){
        if(this.posX <= 400+this.Length){
            this.posX += this.Speed;
        } else if(this.posX > 400+this.Length){
            this.posX = 0 - this.Length; 
            this.posY = random(0+this.Height,400-this.Height);
        }
        this.drawMe();
    };
    return fish;
};

var addFish = null;
var fishtank = [];
var Bubbles = [];


function setup() {

    createCanvas(400, 400)

    while(Bubbles.length < BubblesAmount){
        Bubbles.push(newBubble());
    }

    while(fishtank.length < FishesAmount){
        fishtank.push(newFish());
    }
}


keyPressed = function() {
    if(String.fromCharCode(keyCode) === 'H' && addFish !== null){
        fishtank.push(addFish);
        addFish = null;
    } 
}

mouseClicked = function(){
    if(mouseButton === LEFT && addFish === null){
        addFish = newFish();
        addFish.posX = mouseX;
        addFish.posY = mouseY;
        addFish.bodyColor = color(random(0,255),random(0,255),random(0,255));
        addFish.tailColor = color(random(0,255),random(0,255),random(0,255));
        addFish.drawMe();
    }
};

mouseMoved = function() {
    if(!addFish) return;
    addFish.posX = mouseX;
    addFish.posY = mouseY;
};

mouseOut = function() {
    addFish = null;
};

draw = function() {
    background(89, 216, 255);
    
    if(addFish !== null){
        addFish.drawMe();
    } 
    
    if(mouseIsPressed && mouseButton === LEFT && addFish !== null){
        addFish.changeSize();
    }
    
    for(var i=0; i < Bubbles.length; i++){
        Bubbles[i].bubble();
    } 
    
    for(var i=0; i < fishtank.length; i++){
        fishtank[i].swim();
    }  
};    

