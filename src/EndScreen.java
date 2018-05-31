

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 01/06/2017
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int status;
    private Counter score;

    /**
     * Constructor for end screen.
     *
     * @param k      .
     * @param status 1=win,0=lose
     * @param score  final score
     */
    public EndScreen(KeyboardSensor k, int status, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.status = status;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.status == 0) {
            d.drawText(300, 300, "GAME OVER", 32);
            d.drawText(325, 350, "YOU LOSE", 50);
            d.drawText(30, 560, "Final score: " + Integer.toString(this.score.getValue()), 18);
            d.drawText(30, 580, "Press space to continue", 15);
        } else {
            d.drawText(300, 300, "GAME OVER", 32);
            d.drawText(325, 350, "YOU WIN", 50);
            d.drawText(30, 560, "Final score: " + Integer.toString(this.score.getValue()), 18);
            d.drawText(30, 580, "Press space to continue", 15);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}