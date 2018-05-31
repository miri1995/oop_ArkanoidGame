/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 30/03/2017
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {

        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {

        return this.y;
    }

    /**
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distanceTo(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * @param other a point to measure the distance to
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        Point p1 = new Point(this.x, this.y);
        Point p2 = new Point(other.getX(), other.getY());
        return ((this.x == other.getX()) && this.y == other.getY());

    }
}

