function setup() {

    createCanvas(600, 600);

    background(186, 145, 20); // wooden table


    ellipse(200, 200, 350, 350); // plate
    ellipse(200, 200, 300, 300); 

    //fork
    noStroke();
    fill(117, 108, 117);
    rect(0,250,80,20);
    rect(80,230,15,60);

    rect(95,230,35,10);
    rect(95,280,35,10);
rect(95,255,35,10);

    //image(getImage("creatures/OhNoes"),84,110,100,100);

    //sausage
    strokeWeight(12);
    stroke(61, 21, 1);
    point(114,306);
    point(313,222);

    //sausage-ends
    strokeWeight(35);
    stroke(112, 39, 0);
    bezier(132,306, 117,304, 227,315, 301,235);

    //egg
    noStroke();
    fill(255, 211, 161);
    ellipse(250,170,100,100);

    fill(255,190,0);
    ellipse(250,170,30,30);
}