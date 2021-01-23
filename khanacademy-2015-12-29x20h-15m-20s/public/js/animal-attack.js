




// Click on canvas and press 'h'





























var bodyX = 300;
var bodyY = 330;
var bodyH = 450;
var bodyW = bodyH;

function setup(){
    createCanvas(600,600);
}

var instructions = function(){
    noLoop();
    background(255, 255, 255);
    fill(97, 97, 97);
    textSize(25);
    //textFont(createFont("cursive"));
    textFont("cursive");
    var txt = "Instructions: \nPress 'S': Start\nPress 'T': Stop \nPress 'R': reset the Character \nPress 'H': instructions\nPress 'Left MB': gets bigger\nPress 'Right MB': gets smaller \n\n\n MB = Mousebutton";
    text(txt,30,55);
};

mouseMoved = function() {
    bodyX = mouseX;
    bodyY = mouseY;
};

keyReleased = function() {
  if(String.fromCharCode(keyCode) === 'R'){
    bodyX = 300;
    bodyY = 330;
    bodyH = 450;
  }
  if(String.fromCharCode(keyCode) === 'S'){
      loop();
  } else if(String.fromCharCode(keyCode) === 'H') {
      instructions();
  } else if(String.fromCharCode(keyCode) === 'T'){
      noLoop();
    }
};


draw = function() {
    
    if(mouseIsPressed && mouseButton === LEFT){
        bodyH += 2;
    } else { if(mouseIsPressed && mouseButton === RIGHT && bodyH>0) {
        bodyH -= 2;
    }}

    bodyW = bodyH;
    
    background(171, 234, 255); 
    noStroke();
    
    fill(252, 118, 0);
    var x1 = bodyX + bodyW/2;
    var x2 = bodyX + bodyW/8;
    var y1 = bodyH/2 + bodyY;
    var y2 = y1 - bodyH/6;
    triangle(x1, y1, x2,y2, x2,y1); // foot (right)
    x1 = bodyX - bodyW/2;
    x2 = bodyX - bodyW/8;
    triangle(x1, y1, x2,y2, x2,y1); // foot (left)
    
    
    fill(0, 0, 100);
    ellipse(bodyX, bodyY, bodyW, bodyH); // body(blue)
    
    fill(255, 255, 255);
    var W =  bodyW/1.3;
    ellipse(bodyX, bodyY, W, bodyH); // inner-body(white)
    
    fill(0, 0, 100);
    var y = bodyY  - bodyH/2;
    var headW = bodyW/2;
    var headH = bodyH/2.4;
    ellipse(bodyX, y, headW, headH); // head
    //116 64 55
    
    fill(255, 255, 255);
    var x = headW/5; 
    var y = bodyY - headH;
    var w = headW/2;
    var h = w*1.2;
    ellipse(bodyX+x, y, w, h); // white eyehole (right)
    ellipse(bodyX-x, y, w, h); // white eyehole (left)
    
    fill(0, 0, 0);
    var r = headW/16;
    ellipse(bodyX-x, y, r, r); // eye (right)
    ellipse(bodyX+x, y, r, r); // eye (left)
    
    fill(252, 118, 0);
    var y1 = bodyY - bodyH/8;
    var y2 = bodyY - bodyH/3;
    var x1 = bodyX - bodyW/8;
    var x2 = bodyX + bodyW/8;
    triangle(bodyX, y1,x1, y2,x2, y2); // nose
};

