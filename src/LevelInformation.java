
import java.util.List;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 26/05/2017
 */
public interface LevelInformation {

    /**
     * @return a number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return a speed of paddle.
     */
    int paddleSpeed();

    /**
     * @return a width of paddle.
     */
    int paddleWidth();


    /**
     * @return the level name.
     */
    String levelName();


    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();


    /**
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();
    /**
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}