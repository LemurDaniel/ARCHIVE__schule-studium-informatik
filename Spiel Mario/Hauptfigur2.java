import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
public class Hauptfigur2
{
    
    RECHTECK [] Pixel = new RECHTECK [144];
    RECHTECK Strich;
    int breite = 6;
    int x=15;
    int y=315;
    Hauptfigur2 Figur2;
    
    Timer Lauftimer;
    Timer Springhochtimer;
    Timer Springruntertimer;
    Image Mario = getImage(getCodeBase(),"Mario.jpg");
        
    public Hauptfigur2()
    {
        this(10,315);
    }
    
    public Hauptfigur2(int x, int y)
    {
        Lauftimer = new Timer(1, new ActionListener()
            {
             public void actionPerformed(ActionEvent e)
                   {
                       lauf(1,1);
                                }
           });
           
         Springhochtimer = new Timer(1, new ActionListener()
            {
             public void actionPerformed(ActionEvent e)
                   {
                       springhoch(1,1,280);
                                }
           });
          Springruntertimer = new Timer(1, new ActionListener()
            {
             public void actionPerformed(ActionEvent e)
                   {
                       springrunter(1,1);
                                }
           });
      
      Pixel[0]= new RECHTECK(x,y,breite,breite,"braun", true); //1 Reihe von unten des linken Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[1]= new RECHTECK(x+breite,y,breite,breite,"braun", true); //1 Reihe von unten des linken Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[2]= new RECHTECK(x+2*breite,y,breite,breite,"braun", true); //1 Reihe von unten des linken Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[3]= new RECHTECK(x+3*breite,y,breite,breite,"braun", true); //1 Reihe von unten des linken Fuﬂes 4 Pixel von links aus gez‰hlt
      
      Pixel[4]= new RECHTECK(x+8*breite,y,breite,breite,"braun", true); //1 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[5]= new RECHTECK(x+9*breite,y,breite,breite,"braun", true); //1 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[6]= new RECHTECK(x+10*breite,y,breite,breite,"braun", true); //1 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[7]= new RECHTECK(x+11*breite,y,breite,breite,"braun", true); //1 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
            
      Pixel[8]= new RECHTECK(x+breite,y-breite,breite,breite,"braun", true); //2Reihe von unten des linken Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[9]= new RECHTECK(x+2*breite,y-breite,breite,breite,"braun", true); //2 Reihe von unten des linken Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[10]= new RECHTECK(x+3*breite,y-breite,breite,breite,"braun", true); //2 Reihe von unten des linken Fuﬂes 3 Pixel von links aus gez‰hlt
            
      Pixel[11]= new RECHTECK(x+8*breite,y-breite,breite,breite,"braun", true); //2 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[12]= new RECHTECK(x+9*breite,y-breite,breite,breite,"braun", true); //2 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[13]= new RECHTECK(x+10*breite,y-breite,breite,breite,"braun", true); //2 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      
      Pixel[14]= new RECHTECK(x+2*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des linken Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[15]= new RECHTECK(x+3*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des linken Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[16]= new RECHTECK(x+4*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des linken Fuﬂes 3 Pixel von links aus gez‰hlt
            
      Pixel[17]= new RECHTECK(x+7*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[18]= new RECHTECK(x+8*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[19]= new RECHTECK(x+9*breite,y-2*breite,breite,breite,"blau", true); //3 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      
      Pixel[20]= new RECHTECK(x,y-3*breite,breite,breite,"weiss", true); //4 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[21]= new RECHTECK(x+1*breite,y-3*breite,breite,breite,"weiss", true); //4 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[22]= new RECHTECK(x+2*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[23]= new RECHTECK(x+3*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[24]= new RECHTECK(x+4*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[25]= new RECHTECK(x+5*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[26]= new RECHTECK(x+6*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[27]= new RECHTECK(x+7*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[28]= new RECHTECK(x+8*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[29]= new RECHTECK(x+9*breite,y-3*breite,breite,breite,"blau", true); //4 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      Pixel[30]= new RECHTECK(x+10*breite,y-3*breite,breite,breite,"weiss", true); //4 Reihe von unten des rechten Fuﬂes 11 Pixel von links aus gez‰hlt
      Pixel[31]= new RECHTECK(x+11*breite,y-3*breite,breite,breite,"weiss", true); //4 Reihe von unten des rechten Fuﬂes 12 Pixel von links aus gez‰hlt
      
      Pixel[32]= new RECHTECK(x,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[33]= new RECHTECK(x+1*breite,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[34]= new RECHTECK(x+2*breite,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[35]= new RECHTECK(x+3*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[36]= new RECHTECK(x+4*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[37]= new RECHTECK(x+5*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[38]= new RECHTECK(x+6*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[39]= new RECHTECK(x+7*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[40]= new RECHTECK(x+8*breite,y-4*breite,breite,breite,"blau", true); //5 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[41]= new RECHTECK(x+9*breite,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      Pixel[42]= new RECHTECK(x+10*breite,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 11 Pixel von links aus gez‰hlt
      Pixel[43]= new RECHTECK(x+11*breite,y-4*breite,breite,breite,"weiss", true); //5 Reihe von unten des rechten Fuﬂes 12 Pixel von links aus gez‰hlt
      
      Pixel[44]= new RECHTECK(x,y-5*breite,breite,breite,"weiss", true); //6 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[45]= new RECHTECK(x+1*breite,y-5*breite,breite,breite,"weiss", true); //6 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[46]= new RECHTECK(x+2*breite,y-5*breite,breite,breite,"gruen", true); //6 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[47]= new RECHTECK(x+3*breite,y-5*breite,breite,breite,"blau", true); //6 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[48]= new RECHTECK(x+4*breite,y-5*breite,breite,breite,"gelb", true); //6 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[49]= new RECHTECK(x+5*breite,y-5*breite,breite,breite,"blau", true); //6 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[50]= new RECHTECK(x+6*breite,y-5*breite,breite,breite,"blau", true); //6 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[51]= new RECHTECK(x+7*breite,y-5*breite,breite,breite,"gelb", true); //6 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[52]= new RECHTECK(x+8*breite,y-5*breite,breite,breite,"blau", true); //6 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[53]= new RECHTECK(x+9*breite,y-5*breite,breite,breite,"gruen", true); //6 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      Pixel[54]= new RECHTECK(x+10*breite,y-5*breite,breite,breite,"weiss", true); //6 Reihe von unten des rechten Fuﬂes 11 Pixel von links aus gez‰hlt
      Pixel[55]= new RECHTECK(x+11*breite,y-5*breite,breite,breite,"weiss", true); //6 Reihe von unten des rechten Fuﬂes 12 Pixel von links aus gez‰hlt
      
      Pixel[56]= new RECHTECK(x,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[57]= new RECHTECK(x+1*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[58]= new RECHTECK(x+2*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[59]= new RECHTECK(x+3*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[60]= new RECHTECK(x+4*breite,y-6*breite,breite,breite,"blau", true); //7 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[61]= new RECHTECK(x+5*breite,y-6*breite,breite,breite,"blau", true); //7 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[62]= new RECHTECK(x+6*breite,y-6*breite,breite,breite,"blau", true); //7 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[63]= new RECHTECK(x+7*breite,y-6*breite,breite,breite,"blau", true); //7 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[64]= new RECHTECK(x+8*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[65]= new RECHTECK(x+9*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      Pixel[66]= new RECHTECK(x+10*breite,y-6*breite,breite,breite,"gruen", true); //7 Reihe von unten des rechten Fuﬂes 11 Pixel von links aus gez‰hlt
      Pixel[67]= new RECHTECK(x+11*breite,y-6*breite,breite,breite,"gruen",true); //7 Reihe von unten des rechten Fuﬂes 12 Pixel von links aus gez‰hlt
      
      
      Pixel[68]= new RECHTECK(x+1*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[69]= new RECHTECK(x+2*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[70]= new RECHTECK(x+3*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[71]= new RECHTECK(x+4*breite,y-7*breite,breite,breite,"blau", true); //8 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[72]= new RECHTECK(x+5*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[73]= new RECHTECK(x+6*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[74]= new RECHTECK(x+7*breite,y-7*breite,breite,breite,"blau", true); //8 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[75]= new RECHTECK(x+8*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[76]= new RECHTECK(x+9*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[77]= new RECHTECK(x+10*breite,y-7*breite,breite,breite,"gruen", true); //8 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
            
      Pixel[78]= new RECHTECK(x+2*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[79]= new RECHTECK(x+3*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[80]= new RECHTECK(x+4*breite,y-8*breite,breite,breite,"blau", true); //9 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[81]= new RECHTECK(x+5*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[82]= new RECHTECK(x+6*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[83]= new RECHTECK(x+7*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[84]= new RECHTECK(x+8*breite,y-8*breite,breite,breite,"gruen", true); //9 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      
      
      Pixel[85]= new RECHTECK(x+3*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[86]= new RECHTECK(x+4*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[87]= new RECHTECK(x+5*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[88]= new RECHTECK(x+6*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[89]= new RECHTECK(x+7*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[90]= new RECHTECK(x+8*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[91]= new RECHTECK(x+9*breite,y-9*breite,breite,breite,"hautfarbe", true); //10 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      
      Pixel[92]= new RECHTECK(x+1*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[93]= new RECHTECK(x+2*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt 
      Pixel[94]= new RECHTECK(x+3*breite,y-10*breite,breite,breite,"hautfarbe", true); //11 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[95]= new RECHTECK(x+4*breite,y-10*breite,breite,breite,"hautfarbe", true); //11 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[96]= new RECHTECK(x+5*breite,y-10*breite,breite,breite,"hautfarbe", true); //11 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[97]= new RECHTECK(x+6*breite,y-10*breite,breite,breite,"hautfarbe", true); //11 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[98]= new RECHTECK(x+7*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[99]= new RECHTECK(x+8*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[100]= new RECHTECK(x+9*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[101]= new RECHTECK(x+10*breite,y-10*breite,breite,breite,"braun", true); //11 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      
      Pixel[102]= new RECHTECK(x+1*breite,y-11*breite,breite,breite,"braun", true); //12 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[103]= new RECHTECK(x+2*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt 
      Pixel[104]= new RECHTECK(x+3*breite,y-11*breite,breite,breite,"braun", true); //12 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[105]= new RECHTECK(x+4*breite,y-11*breite,breite,breite,"braun", true); //12 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[106]= new RECHTECK(x+5*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[107]= new RECHTECK(x+6*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[108]= new RECHTECK(x+7*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[109]= new RECHTECK(x+8*breite,y-11*breite,breite,breite,"braun", true); //12 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[110]= new RECHTECK(x+9*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[111]= new RECHTECK(x+10*breite,y-11*breite,breite,breite,"hautfarbe", true); //12 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      Pixel[112]= new RECHTECK(x+11*breite,y-11*breite,breite,breite,"braun", true); //12 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
      
      Pixel[113]= new RECHTECK(x+1*breite,y-12*breite,breite,breite,"braun", true); //13 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt
      Pixel[114]= new RECHTECK(x+2*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt 
      Pixel[115]= new RECHTECK(x+3*breite,y-12*breite,breite,breite,"braun", true); //13 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[116]= new RECHTECK(x+4*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[117]= new RECHTECK(x+5*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[118]= new RECHTECK(x+6*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[119]= new RECHTECK(x+7*breite,y-12*breite,breite,breite,"braun", true); //13 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[120]= new RECHTECK(x+8*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[121]= new RECHTECK(x+9*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
      Pixel[122]= new RECHTECK(x+10*breite,y-12*breite,breite,breite,"hautfarbe", true); //13 Reihe von unten des rechten Fuﬂes 10 Pixel von links aus gez‰hlt
            
      Pixel[123]= new RECHTECK(x+2*breite,y-13*breite,breite,breite,"braun", true); //14 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt 
      Pixel[124]= new RECHTECK(x+3*breite,y-13*breite,breite,breite,"braun", true); //14 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[125]= new RECHTECK(x+4*breite,y-13*breite,breite,breite,"braun", true); //14 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[126]= new RECHTECK(x+5*breite,y-13*breite,breite,breite,"hautfarbe", true); //14 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[127]= new RECHTECK(x+6*breite,y-13*breite,breite,breite,"hautfarbe", true); //14 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[128]= new RECHTECK(x+7*breite,y-13*breite,breite,breite,"braun", true); //14 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[129]= new RECHTECK(x+8*breite,y-13*breite,breite,breite,"hautfarbe", true); //14 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
            
      Pixel[130]= new RECHTECK(x+2*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 1 Pixel von links aus gez‰hlt 
      Pixel[131]= new RECHTECK(x+3*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[132]= new RECHTECK(x+4*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[133]= new RECHTECK(x+5*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[134]= new RECHTECK(x+6*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[135]= new RECHTECK(x+7*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
      Pixel[136]= new RECHTECK(x+8*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 7 Pixel von links aus gez‰hlt
      Pixel[137]= new RECHTECK(x+9*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 8 Pixel von links aus gez‰hlt
      Pixel[138]= new RECHTECK(x+10*breite,y-14*breite,breite,breite,"gruen", true); //15 Reihe von unten des rechten Fuﬂes 9 Pixel von links aus gez‰hlt
                  
      Pixel[139]= new RECHTECK(x+3*breite,y-15*breite,breite,breite,"gruen", true); //16 Reihe von unten des rechten Fuﬂes 2 Pixel von links aus gez‰hlt
      Pixel[140]= new RECHTECK(x+4*breite,y-15*breite,breite,breite,"gruen", true); //16 Reihe von unten des rechten Fuﬂes 3 Pixel von links aus gez‰hlt
      Pixel[141]= new RECHTECK(x+5*breite,y-15*breite,breite,breite,"gruen", true); //16 Reihe von unten des rechten Fuﬂes 4 Pixel von links aus gez‰hlt
      Pixel[142]= new RECHTECK(x+6*breite,y-15*breite,breite,breite,"gruen", true); //16 Reihe von unten des rechten Fuﬂes 5 Pixel von links aus gez‰hlt
      Pixel[143]= new RECHTECK(x+7*breite,y-15*breite,breite,breite,"gruen", true); //16 Reihe von unten des rechten Fuﬂes 6 Pixel von links aus gez‰hlt
             
    }
    
    public void zeichnen(){
        for(int i=0;i<=143;i++){
            Pixel[i].zeichnen();
        }
    }
    
    public void loeschen(){
        for(int i=0;i<=143;i++){
            Pixel[i].loeschen();
        }
    }
  
    public void LaufenStart(){
          Lauftimer.start();
    }
    
    public void LaufenStop(){
          Lauftimer.stop();
    }
    
      public void lauf(int zeit, int Geschwindigkeit){
        int vx=Geschwindigkeit;
        
        int newposx = vx * zeit;   
            for(int i=0;i<=143;i++){
            Pixel[i].bewegex(newposx,"rechts");
            }
  

    }
    
    public void SpringenStart(){
        Springhochtimer.start();
    }
    
    public void SpringenStop(){
        Springhochtimer.stop();
        Springruntertimer.stop();
    }
    
   public void springhoch(int zeit, int Geschwindigkeit, int maxSprunghoehe){
        int vy=Geschwindigkeit;
        
        int newposy = vy * zeit;
        y=y-newposy;
        System.out.print(y);
        System.out.print(",   ");
        Strich = new RECHTECK(0,maxSprunghoehe-15*breite,100,1,"schwarz", false);
        
        if(y==maxSprunghoehe){
                Springhochtimer.stop();
                Springruntertimer.start();
                
        } else {
                for(int i=0;i<=143;i++){
                Pixel[i].bewegey(newposy,"hoch");
                }
        }
   }
   
   public void springrunter(int zeit, int Geschwindigkeit){
        int vy=Geschwindigkeit;
        
        int newposy = vy * zeit;
        y=y+newposy;
        System.out.print(y);
        System.out.print(",   ");
        
                for(int i=0;i<=143;i++){
                Pixel[i].bewegey(newposy,"runter");
                }
                
        if(y==315){
         Springruntertimer.stop();
        }
   }
   
}

