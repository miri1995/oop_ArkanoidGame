
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 26/05/2017
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Constructor of CountdownAnimation.
     * @param numOfSeconds .
     * @param countFrom .
     * @param gameScreen .
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, 380, String.valueOf(this.countFrom), 100);
        Sleeper sleeper1 = new Sleeper();
        sleeper1.sleepFor((long) (this.numOfSeconds / 3 * 1000));
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom--;

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}