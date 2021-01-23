public class Score  {
private int score;
private String naam;

public Score() {

}

public int getScore() {
    return score;
}

public String getNaam() {
    return naam;
}

public Score(String naam, int score) {
    this.score = score;
    this.naam = naam;
}
}
//implements Serializable