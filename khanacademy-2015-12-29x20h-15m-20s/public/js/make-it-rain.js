// Click on screen to add new drops


var drops = [];
var defaultDropAmount = 250;
var differentDrops = true;

function random(max) {
    return Math.random()*max;
}


var newDrop = function(){
    
    var drop = {
        xPos: random(0,450),
        yPos: -400,
        speed: random(3,7),
        radius: random(10,20),
        type: null,
        color: color(random(0,255),random(0,255),random(0,255)),
        drawDrop: function(){}, 
        rain: ""
    };
 
    if(differentDrops){
        switch(round(random(0,3))){
        
            case 0:    
                drop.type = "avatars/leafers-seed";
                break;
            case 1:    
                drop.type = "avatars/aqualine-seed";
                break;
            case 2:    
                drop.type = "avatars/piceratops-seed";
                break;
            default:
                drop.type = null;
        }
    }
    
    if(drop.type === null){
        drop.drawDrop = function(){
            noStroke();
            fill(this.color);
            ellipse(this.xPos,this.yPos,this.radius,this.radius);
        };
    } else {
        drop.drawDrop = function(){
            noStroke();
            fill(this.color);
            imageMode(CENTER);
            image(getImage(this.type),this.xPos,this.yPos,this.radius*2,this.radius*2);
        };
    }
    
    drop.rain = function(){
        this.drawDrop();
        this.yPos += this.speed;
        
        if(this.yPos > 400+this.radius){
            this.yPos = 0 - this.radius;
        }
    };
    
    return drop;
};

mouseClicked = function(){
    var drop = newDrop();
    drop.xPos = mouseX;
    drop.yPos = mouseY;
    drops.push(drop);
};

draw = function() {
    background(105, 228, 250);

    for (var i = 0; i < drops.length; i++) {
        drops[i].rain();
    }
    
};


function setup() {
    createCanvas(400, 400);
    for(var i = 0; i < defaultDropAmount; i++){
        drops.push(newDrop());
    }
    
}