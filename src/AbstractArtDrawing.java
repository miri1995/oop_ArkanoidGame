import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 10/04/2017
 */
public class AbstractArtDrawing {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point start;
    private Point end;

    /**
     * The function draw line.
     *
     * @param l1 line to paint
     * @param d  draw surface
     */
    public void paintLine(Line l1, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l1.start().getX(), (int) l1.start().getY(), (int) l1.end().getX(), (int) l1.end().getY());
    }

    /**
     * The function draw (in blue) a middle point of the line.
     *
     * @param l1 the line on which drawn the middle point
     * @param d  draw surface
     */
    public void paintMiddle(Line l1, DrawSurface d) {
        int middleX = (int) l1.middle().getX();
        int middleY = (int) l1.middle().getY();
        int r = 3;
        d.setColor(Color.BLUE);
        d.fillCircle(middleX, middleY, r);
    }

    /**
     * The function draw (in red) a intersection point of the lines.
     *
     * @param inter the intersection point.
     * @param d     draw surface.
     */
    public void paintIntersection(Point inter, DrawSurface d) {
        int x = (int) inter.getX();
        int y = (int) inter.getY();
        int r = 3;
        d.setColor(Color.RED);
        d.fillCircle(x, y, r);
    }


    /**
     * The function draw 10 lines and calls the appropriate functions to paint middle point and intersection point.
     */
    public void drawLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "AbstractArtDrawing"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("AbstractArtDrawing", 400, 300);
        AbstractArtDrawing simp = new AbstractArtDrawing();
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[10];
        for (int i = 0; i < arr.length; i++) {
            int newX1 = rand.nextInt(300) + 1;
            int newX2 = rand.nextInt(300) + 1;
            int newY1 = rand.nextInt(300) + 1;
            int newY2 = rand.nextInt(300) + 1;
            arr[i] = new Line(newX1, newY1, newX2, newY2);
        }

        for (int i = 0; i < arr.length; i++) {
            simp.paintLine(arr[i], d);
            simp.paintMiddle(arr[i], d);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i].isIntersecting(arr[j])) {
                    simp.paintIntersection(arr[i].intersectionWith(arr[j]), d);
                }
            }
        }

        gui.show(d);
    }

    /**
     * @param args user input
     */
    public static void main(String[] args) {

        AbstractArtDrawing example = new AbstractArtDrawing();

        example.drawLines();

    }
}