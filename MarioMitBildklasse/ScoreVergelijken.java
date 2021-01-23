public class ScoreVergelijken {
public int compare(Score score1, Score score2) {

    int sc1 = score1.getScore();
    int sc2 = score2.getScore();

    if (sc1 > sc2){
        return -1;                   // -1 means first score is bigger then second score
    }else if (sc1 < sc2){
        return +1;                   // +1 means that score is lower
    }else{
        return 0;                     // 0 means score is equal
    }
}  } 

//implements Comparator<Score>