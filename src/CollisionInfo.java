/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 28/04/2017
 */
public class CollisionInfo {

    private Point point;
    private Collidable object;


    /**
     * The constructor of CollisionInfo.
     *
     * @param collisionPoint the collision point.
     * @param collisionObj   the collidable.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObj) {
        this.point = collisionPoint;
        this.object = collisionObj;
    }

    /**
     * @return the point at which the collision occurs.
     */

    public Point collisionPoint() {
        return this.point;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }


}

