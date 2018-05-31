import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by MIRI on 11/06/2017.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * @param sensor    .
     * @param key       .
     * @param animation .
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;

    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else if (this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
