class DemoThread extends Thread 
{
    
    Hauptmap1 Map1  = new Hauptmap1();
    DemoThread Thread;
  
    public void run1()
    {
      while (true){
          update();
          render();
        }
      
        
    } 
    
    private void update()
    {
        Hauptfigur Figur  = new Hauptfigur();
                    
                    Map1.Bewegenstart();// ... aktualisierd irgenetwas 
    }
    
    private void render ()
    {
        Hauptfigur Figur  = new Hauptfigur();
                    
                    Map1.Bewegenstart();// ... zeichnet irgendetwas
    }
    
    public void run2()
    {
        for(int i = 0; i < 1000; i++) {
        try {
          sleep(50);
        }
        catch(InterruptedException e) {
        }
        Hauptfigur Figur  = new Hauptfigur();
                    
                    Map1.Bewegenstart();
      }
    }
      
  }