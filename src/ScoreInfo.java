import java.io.Serializable;

/**
 * Created by MIRI on 07/06/2017.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Constructor of score info.
     *
     * @param name  .
     * @param score .
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return string of the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return int of the score.
     */
    public int getScore() {
        return this.score;
    }
}