/**
 * Write a description of class Math here.
 * 
 * @author (Daniel Landau) 
 * @version (01.06.2015v1)
 */

import java.util.Random;

public class Mathe
{

   public static int gibZufallszahl(int MinWert, int MaxWert){ // gibt Zufalllzahlen
        int Zufallszahl=(int)(Math.random()*(MaxWert-MinWert)+MinWert);
        return Zufallszahl;
   }
    
   public static int[] gibArrayausZufallszahlen(int Arraylaenge,int MinWert, int MaxWert){ //gibt ein Array mitZufallszahlrn
        int Zufallszahl[] = new int[Arraylaenge];           
        for(int i=0;i<Arraylaenge;i++){
            Zufallszahl[i]=(int)(Math.random()*(MaxWert-MinWert)+MinWert);
        }
        return Zufallszahl;
   }
   
}
