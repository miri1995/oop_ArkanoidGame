
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 26/05/2017
 */
public class PauseScreen  implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor of Pause Screen.
     *
     * @param k .
     */

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}