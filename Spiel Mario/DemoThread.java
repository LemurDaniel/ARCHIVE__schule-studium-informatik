class DemoThread extends Thread 
{
    Hauptmap1 Map1  = new Hauptmap1();
  
    public void run() {
      for(int i = 0; i < 100; i++) {
        try {
          sleep(500);
        }
        catch(InterruptedException e) {
        }
        Hauptfigur Figur  = new Hauptfigur();
                    
                    Map1.Bewegenstart();
      }
    }
      
  }