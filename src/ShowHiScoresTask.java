/**
 * Created by MIRI on 12/06/2017.
 */
public class ShowHiScoresTask implements Task<Void> {


    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * @param runner              .
     * @param highScoresAnimation .
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * @return run.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}