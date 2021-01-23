
function setup() {

    createCanvas(400, 400);

    fill(0, 0, 0);
    ellipse(200, 200, 375, 375);
    fill(255, 0, 255);
    triangle(200, 104, 280, 280, 120, 280);
    fill(255,255,255);
    //textFont(createFont("serif"));
    textFont("serif");
    textSize(30);
    text("CLICK", 163, 214);
    text("ME", 183, 250);
}

mouseClicked = function(){
    fill(random(0,255), random(0,255), random(0,255));
    triangle(200, 104, 280, 280, 120, 280);
    fill(255, 255, 255);
    fill(random(0,255), random(0,255), random(0,255));
    var answer = floor(random(1, 5));
    if (answer === 1) {
        text("Go", 183, 175);
        text("Take", 171, 203);
        text("A", 190, 228);
        text("SHIT", 176, 259); 
    } else if (answer === 2) {
        text("_____", 164, 191);
        text("|J|J|J|", 159, 217);
        text("_____", 164, 217);
        text("JACKPOT", 144, 265); 
    } else if (answer === 3) {
        text("DOOM", 160, 216);
        text("TO THE", 158, 241);
        text("WORLD", 156, 264); 
    } else if (answer === 4) {
        text("Chill", 176, 210);
        text("Out", 178, 250); 
    } else if (answer === 5) {
        text("GET", 176, 210);
        text("Schwifty", 151, 250); 
    } 
};
