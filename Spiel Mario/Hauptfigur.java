
public class Hauptfigur
{
    
    RECHTECK [] Pixel = new RECHTECK [150]; 
    private int breite = 6;
    private int x=2;
    private int y=345;
    Hauptfigur Figur;
        
    public void erstelleHauptfigur()
    {
        
      Pixel[0]= new RECHTECK(x,y,breite,breite,"braun"); //1 Reihe von unten des linken Fußes 1 Pixel von links aus gezählt
      Pixel[1]= new RECHTECK(x+breite,y,breite,breite,"braun"); //1 Reihe von unten des linken Fußes 2 Pixel von links aus gezählt
      Pixel[2]= new RECHTECK(x+2*breite,y,breite,breite,"braun"); //1 Reihe von unten des linken Fußes 3 Pixel von links aus gezählt
      Pixel[3]= new RECHTECK(x+3*breite,y,breite,breite,"braun"); //1 Reihe von unten des linken Fußes 4 Pixel von links aus gezählt
      
      Pixel[4]= new RECHTECK(x+8*breite,y,breite,breite,"braun"); //1 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[5]= new RECHTECK(x+9*breite,y,breite,breite,"braun"); //1 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[6]= new RECHTECK(x+10*breite,y,breite,breite,"braun"); //1 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[7]= new RECHTECK(x+11*breite,y,breite,breite,"braun"); //1 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
            
      Pixel[8]= new RECHTECK(x+breite,y-breite,breite,breite,"braun"); //2Reihe von unten des linken Fußes 1 Pixel von links aus gezählt
      Pixel[9]= new RECHTECK(x+2*breite,y-breite,breite,breite,"braun"); //2 Reihe von unten des linken Fußes 2 Pixel von links aus gezählt
      Pixel[10]= new RECHTECK(x+3*breite,y-breite,breite,breite,"braun"); //2 Reihe von unten des linken Fußes 3 Pixel von links aus gezählt
            
      Pixel[11]= new RECHTECK(x+8*breite,y-breite,breite,breite,"braun"); //2 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[12]= new RECHTECK(x+9*breite,y-breite,breite,breite,"braun"); //2 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[13]= new RECHTECK(x+10*breite,y-breite,breite,breite,"braun"); //2 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      
      Pixel[14]= new RECHTECK(x+2*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des linken Fußes 1 Pixel von links aus gezählt
      Pixel[15]= new RECHTECK(x+3*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des linken Fußes 2 Pixel von links aus gezählt
      Pixel[16]= new RECHTECK(x+4*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des linken Fußes 3 Pixel von links aus gezählt
            
      Pixel[17]= new RECHTECK(x+7*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[18]= new RECHTECK(x+8*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[19]= new RECHTECK(x+9*breite,y-2*breite,breite,breite,"blau"); //3 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      
      Pixel[20]= new RECHTECK(x,y-3*breite,breite,breite,"weiss"); //4 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[21]= new RECHTECK(x+1*breite,y-3*breite,breite,breite,"weiss"); //4 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[22]= new RECHTECK(x+2*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[23]= new RECHTECK(x+3*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[24]= new RECHTECK(x+4*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[25]= new RECHTECK(x+5*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[26]= new RECHTECK(x+6*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[27]= new RECHTECK(x+7*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[28]= new RECHTECK(x+8*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[29]= new RECHTECK(x+9*breite,y-3*breite,breite,breite,"blau"); //4 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      Pixel[30]= new RECHTECK(x+10*breite,y-3*breite,breite,breite,"weiss"); //4 Reihe von unten des rechten Fußes 11 Pixel von links aus gezählt
      Pixel[31]= new RECHTECK(x+11*breite,y-3*breite,breite,breite,"weiss"); //4 Reihe von unten des rechten Fußes 12 Pixel von links aus gezählt
      
      Pixel[32]= new RECHTECK(x,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[33]= new RECHTECK(x+1*breite,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[34]= new RECHTECK(x+2*breite,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[35]= new RECHTECK(x+3*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[36]= new RECHTECK(x+4*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[37]= new RECHTECK(x+5*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[38]= new RECHTECK(x+6*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[39]= new RECHTECK(x+7*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[40]= new RECHTECK(x+8*breite,y-4*breite,breite,breite,"blau"); //5 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[41]= new RECHTECK(x+9*breite,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      Pixel[42]= new RECHTECK(x+10*breite,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 11 Pixel von links aus gezählt
      Pixel[43]= new RECHTECK(x+11*breite,y-4*breite,breite,breite,"weiss"); //5 Reihe von unten des rechten Fußes 12 Pixel von links aus gezählt
      
      Pixel[44]= new RECHTECK(x,y-5*breite,breite,breite,"weiss"); //6 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[45]= new RECHTECK(x+1*breite,y-5*breite,breite,breite,"weiss"); //6 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[46]= new RECHTECK(x+2*breite,y-5*breite,breite,breite,"rot"); //6 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[47]= new RECHTECK(x+3*breite,y-5*breite,breite,breite,"blau"); //6 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[48]= new RECHTECK(x+4*breite,y-5*breite,breite,breite,"gelb"); //6 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[49]= new RECHTECK(x+5*breite,y-5*breite,breite,breite,"blau"); //6 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[50]= new RECHTECK(x+6*breite,y-5*breite,breite,breite,"blau"); //6 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[51]= new RECHTECK(x+7*breite,y-5*breite,breite,breite,"gelb"); //6 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[52]= new RECHTECK(x+8*breite,y-5*breite,breite,breite,"blau"); //6 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[53]= new RECHTECK(x+9*breite,y-5*breite,breite,breite,"rot"); //6 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      Pixel[54]= new RECHTECK(x+10*breite,y-5*breite,breite,breite,"weiss"); //6 Reihe von unten des rechten Fußes 11 Pixel von links aus gezählt
      Pixel[55]= new RECHTECK(x+11*breite,y-5*breite,breite,breite,"weiss"); //6 Reihe von unten des rechten Fußes 12 Pixel von links aus gezählt
      
      Pixel[56]= new RECHTECK(x,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[57]= new RECHTECK(x+1*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[58]= new RECHTECK(x+2*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[59]= new RECHTECK(x+3*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[60]= new RECHTECK(x+4*breite,y-6*breite,breite,breite,"blau"); //7 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[61]= new RECHTECK(x+5*breite,y-6*breite,breite,breite,"blau"); //7 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[62]= new RECHTECK(x+6*breite,y-6*breite,breite,breite,"blau"); //7 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[63]= new RECHTECK(x+7*breite,y-6*breite,breite,breite,"blau"); //7 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[64]= new RECHTECK(x+8*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[65]= new RECHTECK(x+9*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      Pixel[66]= new RECHTECK(x+10*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 11 Pixel von links aus gezählt
      Pixel[67]= new RECHTECK(x+11*breite,y-6*breite,breite,breite,"rot"); //7 Reihe von unten des rechten Fußes 12 Pixel von links aus gezählt
      
      
      Pixel[68]= new RECHTECK(x+1*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[69]= new RECHTECK(x+2*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[70]= new RECHTECK(x+3*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[71]= new RECHTECK(x+4*breite,y-7*breite,breite,breite,"blau"); //8 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[72]= new RECHTECK(x+5*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[73]= new RECHTECK(x+6*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[74]= new RECHTECK(x+7*breite,y-7*breite,breite,breite,"blau"); //8 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[75]= new RECHTECK(x+8*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[76]= new RECHTECK(x+9*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[77]= new RECHTECK(x+10*breite,y-7*breite,breite,breite,"rot"); //8 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
            
      Pixel[78]= new RECHTECK(x+2*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[79]= new RECHTECK(x+3*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[80]= new RECHTECK(x+4*breite,y-8*breite,breite,breite,"blau"); //9 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[81]= new RECHTECK(x+5*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[82]= new RECHTECK(x+6*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[83]= new RECHTECK(x+7*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[84]= new RECHTECK(x+8*breite,y-8*breite,breite,breite,"rot"); //9 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      
      
      Pixel[85]= new RECHTECK(x+3*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[86]= new RECHTECK(x+4*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[87]= new RECHTECK(x+5*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[88]= new RECHTECK(x+6*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[89]= new RECHTECK(x+7*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[90]= new RECHTECK(x+8*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[91]= new RECHTECK(x+9*breite,y-9*breite,breite,breite,"hautfarbe"); //10 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      
      Pixel[92]= new RECHTECK(x+1*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[93]= new RECHTECK(x+2*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt 
      Pixel[94]= new RECHTECK(x+3*breite,y-10*breite,breite,breite,"hautfarbe"); //11 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[95]= new RECHTECK(x+4*breite,y-10*breite,breite,breite,"hautfarbe"); //11 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[96]= new RECHTECK(x+5*breite,y-10*breite,breite,breite,"hautfarbe"); //11 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[97]= new RECHTECK(x+6*breite,y-10*breite,breite,breite,"hautfarbe"); //11 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[98]= new RECHTECK(x+7*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[99]= new RECHTECK(x+8*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[100]= new RECHTECK(x+9*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[101]= new RECHTECK(x+10*breite,y-10*breite,breite,breite,"braun"); //11 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      
      Pixel[102]= new RECHTECK(x+1*breite,y-11*breite,breite,breite,"braun"); //12 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[103]= new RECHTECK(x+2*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt 
      Pixel[104]= new RECHTECK(x+3*breite,y-11*breite,breite,breite,"braun"); //12 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[105]= new RECHTECK(x+4*breite,y-11*breite,breite,breite,"braun"); //12 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[106]= new RECHTECK(x+5*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[107]= new RECHTECK(x+6*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[108]= new RECHTECK(x+7*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[109]= new RECHTECK(x+8*breite,y-11*breite,breite,breite,"braun"); //12 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[110]= new RECHTECK(x+9*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[111]= new RECHTECK(x+10*breite,y-11*breite,breite,breite,"hautfarbe"); //12 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      Pixel[112]= new RECHTECK(x+11*breite,y-11*breite,breite,breite,"braun"); //12 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
      
      Pixel[102]= new RECHTECK(x+1*breite,y-12*breite,breite,breite,"braun"); //13 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt
      Pixel[103]= new RECHTECK(x+2*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt 
      Pixel[104]= new RECHTECK(x+3*breite,y-12*breite,breite,breite,"braun"); //13 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[105]= new RECHTECK(x+4*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[106]= new RECHTECK(x+5*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[107]= new RECHTECK(x+6*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[108]= new RECHTECK(x+7*breite,y-12*breite,breite,breite,"braun"); //13 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[109]= new RECHTECK(x+8*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[110]= new RECHTECK(x+9*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
      Pixel[111]= new RECHTECK(x+10*breite,y-12*breite,breite,breite,"hautfarbe"); //13 Reihe von unten des rechten Fußes 10 Pixel von links aus gezählt
            
      Pixel[112]= new RECHTECK(x+2*breite,y-13*breite,breite,breite,"braun"); //14 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt 
      Pixel[113]= new RECHTECK(x+3*breite,y-13*breite,breite,breite,"braun"); //14 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[114]= new RECHTECK(x+4*breite,y-13*breite,breite,breite,"braun"); //14 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[115]= new RECHTECK(x+5*breite,y-13*breite,breite,breite,"hautfarbe"); //14 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[116]= new RECHTECK(x+6*breite,y-13*breite,breite,breite,"hautfarbe"); //14 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[117]= new RECHTECK(x+7*breite,y-13*breite,breite,breite,"braun"); //14 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[118]= new RECHTECK(x+8*breite,y-13*breite,breite,breite,"hautfarbe"); //14 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
            
      Pixel[119]= new RECHTECK(x+2*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 1 Pixel von links aus gezählt 
      Pixel[120]= new RECHTECK(x+3*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[121]= new RECHTECK(x+4*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[122]= new RECHTECK(x+5*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[123]= new RECHTECK(x+6*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[124]= new RECHTECK(x+7*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
      Pixel[125]= new RECHTECK(x+8*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 7 Pixel von links aus gezählt
      Pixel[126]= new RECHTECK(x+9*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 8 Pixel von links aus gezählt
      Pixel[127]= new RECHTECK(x+10*breite,y-14*breite,breite,breite,"rot"); //15 Reihe von unten des rechten Fußes 9 Pixel von links aus gezählt
                  
      Pixel[128]= new RECHTECK(x+3*breite,y-15*breite,breite,breite,"rot"); //16 Reihe von unten des rechten Fußes 2 Pixel von links aus gezählt
      Pixel[129]= new RECHTECK(x+4*breite,y-15*breite,breite,breite,"rot"); //16 Reihe von unten des rechten Fußes 3 Pixel von links aus gezählt
      Pixel[130]= new RECHTECK(x+5*breite,y-15*breite,breite,breite,"rot"); //16 Reihe von unten des rechten Fußes 4 Pixel von links aus gezählt
      Pixel[131]= new RECHTECK(x+6*breite,y-15*breite,breite,breite,"rot"); //16 Reihe von unten des rechten Fußes 5 Pixel von links aus gezählt
      Pixel[132]= new RECHTECK(x+7*breite,y-15*breite,breite,breite,"rot"); //16 Reihe von unten des rechten Fußes 6 Pixel von links aus gezählt
             
    }
    
}
