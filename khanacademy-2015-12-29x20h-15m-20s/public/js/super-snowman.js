var snowflakeAmount = 600;

function random (max) {
    return Math.random()*max;
}


var newSnowflake = function(){
    
    var snowflake = {
        xPos: 400,
        yPos: 400,
        speed: random(1,4),
        radius: random(4,8),
        color: color(255, 255, 255),
        drawSnow: function(){}, 
        snow: ""
    };
 

    snowflake.drawSnow = function(){
        noStroke();
        fill(this.color);
        ellipse(this.xPos,this.yPos,this.radius,this.radius);
    };
 
    
    snowflake.snow = function(){
        this.drawSnow();
        this.yPos += this.speed;
        this.xPos += this.speed;
        
        if(this.yPos > 400+this.radius){
            this.yPos = random(-200,200);
            this.xPos = random(-200,200);
            this.Size = random(1,4);
        }
    };
    
    return snowflake;
};


var snowflakes = [];
function setup() {
    createCanvas(400, 400);

    while(snowflakes.length < snowflakeAmount){
        snowflakes.push(newSnowflake());
    }
}

var drawSnowman = function(){
    //grass
    fill(84, 247, 155);
    rect(0,300,400,100);
    
    // simple snowman
    fill(255,255,255);
    ellipse(200, 300, 150, 150);
    ellipse(200, 200, 100, 100);
    ellipse(200, 120, 75, 75);
    
    //hat
    fill(0,0,0);
    rect(160,80,80,15);
    rect(175,30,50,50);
    
    // eyes
    fill(0,0,0);
    ellipse(185,110,5,5);
    ellipse(215,110,5,5);
    
    //nose
    fill(255, 89, 0);
    ellipse(200,120,10,10);
    
    //mouth
    noFill();
    stroke(0,0,0);
    strokeWeight(5);
    arc(200,135,40,20,170,22);
    
    //arms
    stroke(112, 80, 4);
    strokeWeight(5);
    line(170,200,100,100);
    line(230,200,300,100);
    
    //lefthand
    line(100,100,85,105);
    line(100,100,100,85);
    line(100,100,86,92);
    line(100,100,115,93);
    
    //righthand
    line(300,100,315,105);
    line(300,100,300,85);
    line(300,100,286,92);
    line(300,100,315,93);
    
    //blackdots
    var posx = 200;
    var posy = 180;
    
    stroke(0,0,0);
    fill(255,255,255);
    while(true){
        
        ellipse(posx,posy,8,8);
        posy += 30;
      
        if(posy>=350){ break;}
    }
};

draw = function() {
    
    background(0,255,255);
    
    for(var i = 0; i < snowflakes.length; i++){
        snowflakes[i].snow();
    }
    
    drawSnowman();
};


