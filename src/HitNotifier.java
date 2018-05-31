/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 24/05/2017
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl .
     */
    void addHitListener(HitListener hl);


    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl .
     */
    void removeHitListener(HitListener hl);
}