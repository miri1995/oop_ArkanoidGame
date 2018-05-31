import biuoop.DrawSurface;

import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 27/04/2017
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private Map<Integer, BackgroundForBlock> background;
    private int hitCounter;
    private List<HitListener> hitListeners;


    /**
     * The constructor of block.
     *
     * @param block      the block is the rectangle.
     * @param background .
     * @param hitCounter the counter of the hit of the ball.
     */

    public Block(Rectangle block, Map<Integer, BackgroundForBlock> background, int hitCounter) {
        this.block = block;
        this.background = background;
        this.hitCounter = hitCounter;
        this.hitListeners = new ArrayList<>();


    }

    /**
     * The function draw the block on the given DrawSurface.
     *
     * @param surface the board.
     */

    public void drawOn(DrawSurface surface) {
        Point upperRight = new Point((int) block.getUpperLeft().getX() + block.getWidth(),
                (int) block.getUpperLeft().getY());
        Point bottomLeft = new Point((int) block.getUpperLeft().getX(),
                (int) block.getUpperLeft().getY() + block.getHeight());
        if (background != null) {
            BackgroundForBlock bfb = background.get(this.hitCounter);
            Image img = bfb.getImg();
            if (img == null) {
                surface.setColor(bfb.getColor());
                surface.fillRectangle((int) block.getUpperLeft().getX(),
                        (int) block.getUpperLeft().getY(), (int) block.getWidth(), (int) block.getHeight());
            } else {
                surface.drawImage((int) block.getUpperLeft().getX(),
                        (int) block.getUpperLeft().getY(), img);
            }
        }
    }

    /**
     * @return the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * The function checking the coliision point between block and ball and return them in the list.
     * if the ball collision with the upper limit of the rectangle = 1
     * if the ball collision with the lower limit of the rectangle = 2
     * if the ball collision with the left limit of the rectangle = 3
     * if the ball collision with the right limit of the rectangle = 4
     *
     * @param collisionPoint the collision point between block and ball.
     * @return the list of the collision point.
     */
    public List<Integer> checkingCollisionPoints(Point collisionPoint) {
        List<Integer> ints = new ArrayList<>();
        double width = this.block.getWidth();
        double height = this.block.getHeight();
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        //if the ball collision with the upper limit of the rectangle.
        if (collisionPoint.getY() == upperLeft.getY()
                && upperLeft.getX() <= collisionPoint.getX() && collisionPoint.getX() <= upperRight.getX()) {
            ints.add(1);
        }
        //if the ball collision with the lower limit of the rectangle.
        if (collisionPoint.getY() == bottomLeft.getY() && bottomLeft.getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= bottomRight.getX()) {
            ints.add(2);
        }
        //if the ball collision with the left limit of the rectangle.
        if (collisionPoint.getX() == bottomLeft.getX() && upperLeft.getY() <= collisionPoint.getY()
                && collisionPoint.getY() <= bottomLeft.getY()) {
            ints.add(3);
        }
        //if the ball collision with the right limit of the rectangle.
        if (collisionPoint.getX() == bottomRight.getX() && upperRight.getY() <= collisionPoint.getY()
                && collisionPoint.getY() <= bottomRight.getY()) {
            ints.add(4);
        }
        //if the ball collision with the corner.
        if (collisionPoint.equals(bottomLeft) || collisionPoint.equals(bottomRight) || collisionPoint.equals(upperLeft)
                || collisionPoint.equals(upperRight)) {
            ints.add(5);
        }

        return ints;
    }

    /**
     * The function returns the velocity after the collision between the ball and block.
     * (If hit the speed block which is in the opposite direction)
     *
     * @param collisionPoint  the collision point between block and ball.
     * @param currentVelocity the velocity of the ball.
     * @param hitter          the ball that hitter.
     * @return the velocity after the collision between the ball and block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Integer> value = checkingCollisionPoints(collisionPoint);
        //if the ball collision with the corner.
        if (value.contains(5)) {
            Velocity v = new Velocity(-1 * currentVelocity.getDX(), -1 * currentVelocity.getDY());
            if (this.hitCounter > 0) {
                this.hitCounter--;
            }
            return v;
        }
        //if the ball collision with the upper limit of the rectangle or lower limit - change in Y axis.
        if (value.contains(1) || value.contains(2)) {
            currentVelocity.setDY(-1 * currentVelocity.getDY());
            //Velocity v = new Velocity(currentVelocity.getDX(), (-1) * currentVelocity.getDY());
            if (this.hitCounter > 0) {
                this.hitCounter--;
            }
            // return v;
        }
        //if the ball collision with the left limit of the rectangle or right limit - change in X axis.
        if (value.contains(3) || value.contains(4)) {
            currentVelocity.setDX(-1 * currentVelocity.getDX());
            // Velocity v = new Velocity(-1 * currentVelocity.getDX(), currentVelocity.getDY());
            if (this.hitCounter > 0) {
                this.hitCounter--;
            }
            //return v;
        }

        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * The function add the block to the game.
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * The function do noting.
     *
     * @param dt .
     */
    public void timePassed(double dt) {

    }

    /**
     * remove the block from the game.
     *
     * @param game .
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }


    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter ball hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @return the hit points.
     */
    public int getHitPoints() {
        return this.hitCounter;
    }

}
