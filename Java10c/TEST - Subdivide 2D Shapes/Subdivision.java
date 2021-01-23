
public class Subdivision
{
    Koordinaten[] Start_points;
    Koordinaten[] Start_points_backup;
    Koordinaten[] Split;
    Koordinaten[] Split2;
    Koordinaten[] Average;
    
    private int subs = 0;
    
    public Subdivision(Koordinaten[] Sp){
        Start_points = newArray(Sp);
            for( int i = 0; i<Start_points.length; i++){
                Start_points[i].add_mouseListener();
            }
            
        Start_points_backup = newArray(Sp);
        Split = newArray(Sp);
        ZEICHENFENSTER.gibFenster().setzeSichtbar(true);
        
        draw_shape(Start_points, "rot",false,null);
    }
    
    private Koordinaten[] newArray(Koordinaten[] Arr){
        Koordinaten[] N_Array = new Koordinaten[Arr.length];
        
            for( int i = 0; i<N_Array.length; i++){
                N_Array[i] = new Koordinaten(Arr[i].Xpos, Arr[i].Ypos);
            }
        return N_Array;
    }
    
    public void undivide(){
        
        ZEICHENFENSTER.gibFenster().loescheAlles();
        
        Split = newArray(Start_points);
        Start_points_backup = newArray(Start_points);
        Average = null;  
        
         draw_shape(Start_points, "rot",true,"rot" );
    }
    
    public void subdivide(int times){
        
        Split = newArray(Start_points);
        Average = null; 
        subs = 0;
        
        for(int i = 0; i<times; i++){
                 subdivide();
             }
    }
   
    public void subdivide(){
 
        if(check()){
            
            if(subs>10){
                undivide();
                return;
            }
            
            ZEICHENFENSTER.gibFenster().loescheAlles();
            
                 for(int i = 0; i<Start_points.length; i++){
                     Start_points[i].mark(5,"rot");
                 }
            
            Split();
            Average();
            draw_shape(Split, "rot",false,null);
            draw_shape(Average, "hellblau",false,null);
            Split = Average;
            subs++;
        }
    }
    
        private boolean check(){
        for( int i = 0; i<Start_points.length; i++){
                if(Start_points[i].Ypos != Start_points_backup[i].Ypos || Start_points[i].Xpos != Start_points_backup[i].Xpos){
                    undivide();
                    subdivide(subs);
                    return false;
                }
        }
        return true;
    }
    
    public void draw_shape(Koordinaten[] sp, String farbe, boolean mark, String markfarbe){
        
        if(mark){sp[0].mark(5,markfarbe);}
        Line.verbinde(sp[0],sp[sp.length-1], farbe);
                 
            for(int i = 1; i<sp.length; i++){
                               
                if(mark){sp[i].mark(5,markfarbe);}
                Line.verbinde(sp[i-1],sp[i], farbe);
            }

    }
    
    public void Split(){
        
        Koordinaten[] temp_Split = new Koordinaten[Split.length];
        
        int count = 0;
        
            for(int i = 1; i<temp_Split.length; i++){
                
                    temp_Split[count] = Line.gib_Mitte( Split[i-1],  Split[i]); 
                    temp_Split[count].print();
                    System.out.println();
                    count++;
            }
        
       temp_Split[count] = Line.gib_Mitte(Split[Split.length-1], Split[0] );          
       
       Split = Add_up_Arrays(Split, temp_Split);     
    }
    
    public Koordinaten[] Add_up_Arrays(Koordinaten[] A1, Koordinaten[] A2){
        
        Koordinaten[] new_A = new Koordinaten[A1.length + A2.length];
        
        int countA = 0;
        int countB = 0;
        
            for(int i = 0; i<new_A.length; i++){
                
               if(i % 2 == 0){
                   new_A[next(new_A)] = A1[countA];
                   countA++;
                }
                
                if(i % 2 == 1){
                    new_A[next(new_A)] = A2[countB];
                    countB++;   
                }              
            }
            
        return new_A;
    }
    
    public int next(Koordinaten[] A){
        
        for(int i = 0; i<A.length; i++){
                
               if(A[i] == null){
                   return i;
                }
        }
        return -1;
    }
    
    public void Average(){
            
        Average = new Koordinaten[Split.length];
        
            for(int i = 1; i<Average.length; i++){
                
                Average[i-1] = Line.gib_Mitte(Split[i-1],Split[i]);
            }
            
        Average[Average.length-1] = Line.gib_Mitte(Split[Split.length-1], Split[0]);

    }


}
