
public class Curve
{
    Koordinaten A;
    Koordinaten B;
    Koordinaten C;
    
    Koordinaten Q;
    Koordinaten R;
    Koordinaten P;
    
    public Curve(double aX,double aY, double bX,double bY, double cX, double cY){         
        float AX = (float) aX;
        float AY = (float) aY;
        
        float BX = (float) bX;
        float BY = (float) bY;
        
        float CX = (float) cX;
        float CY = (float) cY;
        
        A = new Koordinaten(AX, AY);
        B = new Koordinaten(BX, BY);
        C = new Koordinaten(CX, CY);
    }

    public Curve(Koordinaten a, Koordinaten b, Koordinaten c){          
        A = a;
        B = b;
        C = c;
    }
    
    public void newKoo(double aX,double aY, double bX,double bY, double cX, double cY){         
        float AX = (float) aX;
        float AY = (float) aY;
        
        float BX = (float) bX;
        float BY = (float) bY;
        
        float CX = (float) cX;
        float CY = (float) cY;
        
        A.change(AX, AY);
        B.change(BX, BY);
        C.change(CX, CY);
    }
    
    public void Calculate_QRP(double tt){
        
        float t = (float)tt;
      
        Q = Average_out(A, B, t);
        R = Average_out(B, C, t);
        P = Average_out(Q, R, t);
        
        print();
    }
    
    private void print(){
        System.out.print("Koordinaten Punkt Q:  ");
        Q.print();
        System.out.println();
        
        System.out.print("Koordinaten Punkt R:  ");
        R.print();
        System.out.println();
        
        System.out.print("Koordinaten Punkt P:  ")    ;
        P.print();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Koordinaten Average_out(Koordinaten P1, Koordinaten P2, float t){
        
        Koordinaten P3 = new Koordinaten();
            
            P3.Xpos = (1-t)*P1.Xpos + t*P2.Xpos;
            P3.Ypos = (1-t)*P1.Ypos + t*P2.Ypos;
        
        return P3;
    }
    
    static Koordinaten Average_out(double P1x, double P1y, double P2x, double P2y, double tt){
        
        Koordinaten P1 = new Koordinaten((float)P1x, (float)P1y );
        Koordinaten P2 = new Koordinaten((float)P2x, (float)P2y );
        Koordinaten P3 = new Koordinaten();
        
        float t = (float)tt;
            
            P3.Xpos = (1-t)*P1.Xpos + t*P2.Xpos;
            P3.Ypos = (1-t)*P1.Ypos + t*P2.Ypos;
        
        return P3;
    }
}
