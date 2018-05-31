import biuoop.DrawSurface;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 26/05/2017
 */
public interface Animation {

    /**
     * includes the gui and frame-management code, while the game-specific logic
     * and stopping conditions are handled in this.
     *
     * @param d  surface.
     * @param dt .
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return could be abstract, and differ from class to class
     */
    boolean shouldStop();
}