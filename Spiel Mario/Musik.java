import java.awt.*;
import java.applet.*;

public class Musik extends Applet{
	
  AudioClip music = null;
	
  public void init(){
    music = getAudioClip(getCodeBase(),"Musik/Dominic iTunes Käufe/Windows Down.m4a");	
    music.loop();
    add(new Label("Wenn es euch langt, auf den Button klicken"));
    add(new Button("anhalten"));
  }
  
  public boolean action(Event e, Object arg){
    music.stop();
    return true;
  }

  public void destroy(){
    music.stop();
  }

}
