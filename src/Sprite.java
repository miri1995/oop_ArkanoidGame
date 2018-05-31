
import biuoop.DrawSurface;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 30/04/2017
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d surface the board.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void timePassed(double dt);
}
