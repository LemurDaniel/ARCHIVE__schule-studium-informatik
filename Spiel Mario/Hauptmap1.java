public class Hauptmap1
{
    
   RECHTECK [] Boden = new RECHTECK [150];
   private int breite = 6;
   private int x=30;
   private int y=330;
   private int t=10;
   Hauptmap1 Map1;
   RECHTECK [] Block= new RECHTECK [150];
   RECHTECK [] Gras= new RECHTECK [150];
   RECHTECK Mast;
   RECHTECK Fahne;
   KREIS Muster;
    public Hauptmap1()
    {
        Boden[0]= new RECHTECK(x,y,2*x,x-t,"braun");// 1 Erdblock 
        //Boden[0]= new RECHTECK(x-x,y,60*x,x-t,"braun");// 1 Erdblock 
        //Boden[1]= new RECHTECK(x-x,y-t,60*x,t,"gruen"); // 1 Grasblock               
        //Block[0]= new RECHTECK(120,290,70,30,"braun");
        //Block[1]= new RECHTECK(190,260,70,30,"braun");
        //Block[2]= new RECHTECK(260,230,70,30,"braun");
        //Block[3]= new RECHTECK(330,200,70,30,"braun");
        //Block[4]= new RECHTECK(400,170,70,30,"braun");
        //Block[5]= new RECHTECK(540,170,70,30,"braun");
        //Block[6]= new RECHTECK(680,200,70,30,"braun");
        //Block[7]= new RECHTECK(820,170,70,30,"braun");
        //Block[8]= new RECHTECK(960,230,70,30,"braun");
        //Block[9]= new RECHTECK(1100,200,70,30,"braun");
        //Block[10]= new RECHTECK(1240,260,70,30,"braun");
        //Block[11]= new RECHTECK(1380,215,70,30,"braun");
        //Gras[0]= new RECHTECK(120,290,70,10,"gruen");
        //Gras[1]= new RECHTECK(190,260,70,10,"gruen");
        //Gras[2]= new RECHTECK(260,230,70,10,"gruen");
        //Gras[3]= new RECHTECK(330,200,70,10,"gruen");
        //Gras[4]= new RECHTECK(400,170,70,10,"gruen");
        //Gras[5]= new RECHTECK(540,170,70,10,"gruen");
        //Gras[6]= new RECHTECK(680,200,70,10,"gruen");
        //Gras[7]= new RECHTECK(820,170,70,10,"gruen");
        //Gras[8]= new RECHTECK(960,230,70,10,"gruen");
        //Gras[9]= new RECHTECK(1100,200,70,10,"gruen");
        //Gras[10]= new RECHTECK(1240,260,70,10,"gruen");
        //Gras[11]= new RECHTECK(1380,215,70,10,"gruen");
        //Mast = new RECHTECK(1540,90,10,240,"weiss");
        //Fahne = new RECHTECK(1490,90,60,40,"weiss");
        //Muster = new KREIS(1515,110,15,"blau");
    
}
}
