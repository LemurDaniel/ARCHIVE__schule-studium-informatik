import java.util.Random;

public class Main {
  public static void main(String[] argv) throws Exception {

    Random rand = new Random();

    byte[] bytes = new byte[5];
    rand.nextBytes(bytes);
  }
}