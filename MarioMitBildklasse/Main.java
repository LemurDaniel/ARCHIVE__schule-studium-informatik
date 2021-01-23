 public class Main {
    public static void main(String[] args) {
    Highscore hm = new Highscore();
    hm.addScore("Bart",240);
    hm.addScore("Marge",300);
    hm.addScore("Maggie",220);
    hm.addScore("Homer",100);
    hm.addScore("Lisa",270);
    hm.addScore(LabyrinthProject.View.MainMenu.username,290);

    System.out.print(hm.getHighscoreString());
} }