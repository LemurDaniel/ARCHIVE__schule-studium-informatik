
function setup(){
    createCanvas(600,600);

    background(171, 234, 255); 
    noStroke();

    fill(252, 118, 0);
    triangle(260, 240, 220,220, 220,240); // foot (right)
    triangle(140, 240, 180,220, 180,240); // foot (left)

    fill(0, 0, 100);
    ellipse(200, 180, 130, 108); // body(blue)

    fill(255,220,255);
    ellipse(200, 180, 100, 108); // inner-body(white)

    fill(0, 0, 100);
    ellipse(200, 116, 64, 55); // head

    fill(255,255,255);
    ellipse(210, 125, 30, 35); // white eyehole (right)
    ellipse(190, 125, 30, 35); // white eyehole (left)

    fill(0, 0, 0);
    ellipse(210, 125, 4, 4); // eye (right)
    ellipse(190, 125, 4, 4); // eye (left)

    fill(252, 118, 0);
    triangle(200, 150,185, 135,215, 135); // nose

}


