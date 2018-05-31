import java.util.List;
import java.util.ArrayList;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 27/04/2017
 */
public class GameEnvironment {

    private List<Collidable> gameEnvironmentList;

    /**
     * The constructor of GameEnvironment.
     */
    public GameEnvironment() {
        this.gameEnvironmentList = new ArrayList<>();

    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironmentList.add(c);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        this.gameEnvironmentList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line that collision.
     * @return info= the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        int flag = 0;
        java.util.List<Point> interPoints = new ArrayList<>();
        java.util.List<Collidable> interBlocks = new ArrayList<>();
        Point closest;
        for (int i = 0; i < this.gameEnvironmentList.size(); i++) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(this.gameEnvironmentList.get(i)
                    .getCollisionRectangle());
            if (collisionPoint != null) {
                interPoints.add(collisionPoint);
                interBlocks.add(gameEnvironmentList.get(i));
            }
        }
        if (interPoints.size() == 0) {
            return null;
        }
        closest = interPoints.get(0);
        for (int r = 1; r < interPoints.size(); r++) {
            if (closest.distanceTo(trajectory.getStart()) > interPoints.get(r).distanceTo(trajectory.getStart())) {
                closest = new Point(interPoints.get(r).getX(), interPoints.get(r).getY());
                flag = r;

            }
        }
        CollisionInfo info = new CollisionInfo(closest, interBlocks.get(flag));
        return info;
    }

}



