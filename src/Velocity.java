
/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 04/04/2017
 */
public class Velocity {
    private double dx;
    private double dy;


    /**
     * @param dx the distance the ball passed in the X axis.
     * @param dy the distance the ball passed in the Y axis.
     *           The constructor of Velocity=specifies the change in position on the `x` and the `y` axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle the angle that the ball was thrown
     * @param speed speed of the ball.
     * @return new velocity
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = (-1) * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p  point.
     * @param dt .
     * @return return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p, double dt) {

        double newX = p.getX() + dx * dt;
        double newY = p.getY() + dy * dt;

        Point newP = new Point(newX, newY);
        return newP;

    }

    /**
     * @return return the increcement in the x axis.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * @return return the increcement in the y axis.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * @param veloX set the velocity of x axis.
     */
    public void setDX(double veloX) {
        this.dx = veloX;
    }

    /**
     * @param veloY set the velocity of y axis.
     */
    public void setDY(double veloY) {
        this.dy = veloY;
    }


    /**
     * @return return the speed (after pitaguras).
     */
    public double getSpeed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }
}


