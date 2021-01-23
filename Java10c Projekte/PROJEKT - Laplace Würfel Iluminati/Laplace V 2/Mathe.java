/**
 * Write a description of class Math here.
 * 
 * @author (Daniel Landau) 
 * @version (01.06.2015v1)
 */

import java.util.Random;

public class Mathe
{

   public static int gibZufallszahl(int MinWert, int MaxWert, int method_number){ // gibt Zufalllzahlen
       if(method_number==1){ 
           int Zufallszahl = (int)Math.round((Math.random()*(MaxWert-MinWert)+MinWert));
           return Zufallszahl;
       }
       
       if(method_number==2){  
           return (int)((Math.random()*(6-0))+0+0.99999999999999999999999);       
       }
       
       if(method_number==3){ 
           int Zufallszahl = (int)((Math.random()*(MaxWert+1 -MinWert)+MinWert));
           return Zufallszahl;
       }
       
       return -1;      
    }
}
