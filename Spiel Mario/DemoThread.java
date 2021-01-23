class DemoThread extends Thread 
{
    
    Hauptmap Map1  = new Hauptmap();
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
                    
                    Map1.BewegenStart();// ... aktualisierd irgenetwas 
    }
    
    private void render ()
    {
        Hauptfigur Figur  = new Hauptfigur();
                    
                    Map1.BewegenStart();// ... zeichnet irgendetwas
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
                    
                    Map1.BewegenStart();
      }
    }
      
  }