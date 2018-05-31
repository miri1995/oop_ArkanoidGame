/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 24/05/2017
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit block that hit.
     * @param hitter   ball that hitter.
     */
    void hitEvent(Block beingHit, Ball hitter);
}