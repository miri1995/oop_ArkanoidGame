import java.util.List;


/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 31/03/2017
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * Construct a line given starting point and end point.
     *
     * @param start the starting point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a line given (x1,y1) of one point and (x2,y2) to another point.
     *
     * @param x1 the X value of the starting point of the line.
     * @param y1 the Y value of the starting point of the line.
     * @param x2 the X value of the end point of the line.
     * @param y2 the Y value of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }


    /**
     * Calculates the length of the line: the square root of ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)).
     *
     * @return return the length of the line
     */
    public double length() {
        double dx = this.end.getX() - this.start.getX();
        double dy = this.end.getY() - this.start.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * Calculates the middle point of the line: (x1+x2)/2, (y1+y2)/2.
     *
     * @return return the middle point of the line
     */
    public Point middle() {
        double midx = ((this.start.getX() + this.end.getX()) / 2);
        double midy = ((this.start.getY() + this.end.getY()) / 2);
        Point middle = new Point(midx, midy);
        return middle;
    }


    /**
     * @return return the start point of the line
     */
    public Point start() {
        return this.start;
    }


    /**
     * @return return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return return the start point
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * @return return the end point
     */
    public Point getEnd() {
        return this.end;
    }


    /**
     * @param other the other line that testing if have  intersect point.
     * @return return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }


    /**
     * Calculates the intersection point:
     * calculates the slope of two lines:
     * 1) if they equals return null (there isn`t intersection point).
     * 2) they not equals: check if one of them infinity and then calculates the intersection point.
     *
     * @param other the other line that testing if have  intersect point.
     * @return return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {

        Line l1 = new Line(this.start, this.end);
        Line l2 = new Line(other.getStart(), other.getEnd());
        double xFirst = this.start().getX();
        double xSecond = this.end().getX();
        double yFirst = this.start().getY();
        double ySecond = this.end().getY();
        double x3 = other.start().getX();
        double x4 = other.end().getX();
        double y3 = other.start().getY();
        double y4 = other.end().getY();
        double m1 = (yFirst - ySecond) / (xFirst - xSecond);
        double m2 = (y3 - y4) / (x3 - x4);
        //calculating the slopes of the lines.
        double b1 = yFirst - m1 * xFirst;
        double b2 = y3 - m2 * x3;

        if (m1 == m2) {

            return null;
            //check if one of the slope equal infinity
        } else {
            if (m1 == Double.POSITIVE_INFINITY || m2 == Double.POSITIVE_INFINITY
                    || m1 == Double.NEGATIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY) {
                //if m1 equal infinity
                if (m1 == Double.POSITIVE_INFINITY || m1 == Double.NEGATIVE_INFINITY) {
                    if ((x3 <= xFirst && xFirst <= x4) || (x4 <= xFirst && xFirst <= x3)) {
                        double newX = xFirst;
                        double newY = newX * m2 + b2;
                        if ((yFirst <= newY && newY <= ySecond) || (ySecond <= newY && newY <= yFirst)) {
                            Point p = new Point(newX, newY);
                            return p; // if the point is inside the 2 lines we return it.
                        } else {
                            return null;
                        }
                    }
                    if ((x3 <= xSecond && xSecond <= x4) || (x4 <= xSecond && xSecond <= x3)) {
                        double intersectionX2 = xSecond;
                        double intersectionY2 = xSecond * m2 + b2;
                        if (yFirst <= intersectionY2 && intersectionY2 <= ySecond) {
                            Point intersectionPoint = new Point(intersectionX2, intersectionY2);
                            return intersectionPoint;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }

                } else {
                    if ((xFirst <= x3 && x3 <= xSecond) || (xSecond <= x3 && x3 <= xFirst)) {
                        double newX = x3;
                        double newY = newX * m1 + b1;
                        if ((y3 <= newY && newY <= y4) || (y4 <= newY && newY <= y3)) {
                            Point p = new Point(newX, newY);
                            return p; // if the point is inside the 2 lines we return it.
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                double newX = (b2 - b1) / (m1 - m2);
                double newY = newX * m1 + b1;
                //checks if the point is on the line (in its range)
                // then returns the intersection point if not return null.
                if ((xFirst <= newX && newX <= xSecond && x3 <= newX && newX <= x4)
                        || (xFirst <= newX && newX <= xSecond && x4 <= newX && newX <= x3)
                        || (xSecond <= newX && newX <= xFirst && x3 <= newX && newX <= x4)
                        || (xSecond <= newX && newX <= xFirst && x4 <= newX && newX <= x3)) {
                    Point p = new Point(newX, newY);
                    return p;
                } else {
                    return null;
                }
            }
        }
    }


    /**
     * Check if the value of x and also y of two lines are equals.
     *
     * @param other the other line that testing if the lines is equals.
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        Line l1 = new Line(this.end, this.start);
        Line l2 = new Line(other.end(), other.start());
        return ((this.end == other.end()) && this.start == other.start() || ((this.end == other.start())
                && (this.start == other.end())));
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect rectangle.
     * @return return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        Point closest = null;
        if (points.size() == 0) {
            return null;
        } else if (points.size() == 1) {
            return points.get(0);
        } else {
            Point listPoint1 = points.get(0);
            Point listPoint2 = points.get(1);
            if (listPoint1.distanceTo(this.start) <= listPoint2.distanceTo(this.start)) {
                closest = listPoint1;
            } else {
                closest = listPoint2;
            }
            return closest;
        }

    }


}