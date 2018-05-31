/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 09/04/2017
 */
public class LimitsOfBoard {
    private Line upper;
    private Line lower;
    private Line left;
    private Line right;


    /**
     * Construct a Limits of a board given two points (right and left).
     *
     * @param p1 the point of the left border
     * @param p2 the point of the right border
     */
    public LimitsOfBoard(Point p1, Point p2) {
        this.upper = new Line(p1.getX(), p1.getY(), p2.getX(), p1.getY());
        this.lower = new Line(p1.getX(), p2.getY(), p2.getX(), p2.getY());
        this.left = new Line(p1.getX(), p1.getY(), p1.getX(), p2.getY());
        this.right = new Line(p2.getX(), p1.getY(), p2.getX(), p2.getY());
    }


    /**
     * Construct a Limits of a board given four lines (upper, lower, right and left).
     *
     * @param lower the lower border.
     * @param upper the upper border.
     * @param left  the left border.
     * @param right the right border.
     */
    public LimitsOfBoard(Line lower, Line upper, Line left, Line right) {
        this.lower = lower;
        this.upper = upper;
        this.left = left;
        this.right = right;
    }

    /**
     * @return the upper border
     */
    public double getUpperLimit() {
        return this.upper.start().getY();
    }

    /**
     * @return the lower border
     */
    public double getLowerLimit() {
        return this.lower.start().getY();
    }

    /**
     * @return the left border
     */
    public double getLeftLimit() {
        return this.left.start().getX();
    }

    /**
     * @return the right border
     */
    public double getRightLimit() {
        return this.right.start().getX();
    }

}