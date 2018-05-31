
import java.util.List;
import java.util.ArrayList;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 25/04/2017
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * The constructor of rectangle with location and width/height.
     *
     * @param upperLeft point of the rectangle.
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {

        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The constructor of rectangle with location and width/height.
     *
     * @param upperLeftX X rate of the left point of the rectangle.
     * @param upperLeftY Y rate of the left point of the rectangle.
     * @param width      of the rectangle.
     * @param height     of the rectangle.
     */
    public Rectangle(double upperLeftX, double upperLeftY, double width, double height) {
        upperLeft = new Point(upperLeftX, upperLeftY);
        this.width = width;
        this.height = height;
    }
    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line that check if have a intersction.
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public java.util.List intersectionPoints(Line line) {
        List<Point> intersection = new ArrayList<Point>();
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + height);
        Line upper = new Line(this.upperLeft, upperRight);
        Line lower = new Line(lowerLeft, lowerRight);
        Line left = new Line(this.upperLeft, lowerLeft);
        Line right = new Line(upperRight, lowerRight);
        if (upper.isIntersecting(line) || lower.isIntersecting(line)
                || left.isIntersecting(line) || right.isIntersecting(line)) {
            if (upper.intersectionWith(line) != null) {
                intersection.add(upper.intersectionWith(line));
            }
            if (lower.intersectionWith(line) != null) {
                intersection.add(lower.intersectionWith(line));
            }
            if (left.intersectionWith(line) != null) {
                intersection.add(left.intersectionWith(line));
            }
            if (right.intersectionWith(line) != null) {
                intersection.add(right.intersectionWith(line));
            }
        }
        return intersection;
    }


    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}

