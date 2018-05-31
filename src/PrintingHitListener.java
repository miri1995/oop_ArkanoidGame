/**
 * Created by MIRI on 24/05/2017.
 */
public class PrintingHitListener implements HitListener {

    /**
     * @param beingHit block that hit.
     * @param hitter   ball that hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}