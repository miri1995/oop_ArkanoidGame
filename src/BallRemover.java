/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 25/05/2017
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBall;

    /**
     * Constructor of ball remove.
     *
     * @param game        .
     * @param removedBall .
     */
    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.remainingBall = removedBall;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBall.decrease(1);
    }
}