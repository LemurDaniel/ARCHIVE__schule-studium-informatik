// Click on the Face




var posy = 160;
var click_count = 0;
var m_start = 10;
var m_stop = 170;
var h = 0;

var Gesicht = function() {
background(255, 255, 255);

    if(click_count % 2 ===1){
        h = 0;
        m_start = 0;
        m_stop = 360;
        textSize(90);
        text("Hi",0,200,200,200);
    }else{
        h = 80;
        m_start = 10;
        m_stop = 170;
    }
    

    strokeWeight(1);    
    // hair ?
    fill(61, 32, 2);
    rect(101, 21, 195, 156, 10);

    // face ?
    fill(207, 188, 140);
    ellipse(200, 200, 197, 285);

    //eyes
    fill(255,255,255);
    ellipse(160,160,50,80); //left
    ellipse(240,160,50,80); //right

    //eye-dots
    fill(0, 0, 0);
    ellipse(160,170,15,24); //left
    ellipse(240,170,15,24); //right


    //eyelid
    noStroke();
    fill(191, 124, 65);
    arc(160,160,60,80,180,360);
    arc(240,160,60,80,180,360);

    arc(160,160,60,h,0,180);
    arc(240,160,60,h,0,180);

    //eyebrows
    noFill();
    stroke(71, 44, 3);
    strokeWeight(10);
    arc(160,160,80,120,220,330);
    arc(240,160,80,120,210,320);

    //mouth
    noFill();
    strokeWeight(20);
    stroke(166, 53, 53);
    arc(200,270,90,90,m_start,m_stop);

    //nose
    fill(191, 124, 65);
    noStroke();
    triangle(180,195, 200,210, 220,195);

};

mouseClicked = function(){
    Gesicht();
    click_count++;
    //playSound(getSound("retro/coin"));
};

function setup() {
    createCanvas(400,400);
    Gesicht();
}



    
