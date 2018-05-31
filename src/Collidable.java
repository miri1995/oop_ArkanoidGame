/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 27/04/2017
 */
public interface Collidable {


    /**
     * The function return the "collision shape" of the object.
     *
     * @return he "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point between block and ball.
     * @param currentVelocity the velocity of the ball.
     * @param hitter          the ball that hitter.
     * @return the velocity after the collision between the ball and block.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}