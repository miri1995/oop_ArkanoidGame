import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 25/05/2017
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;

    /**
     * Constructor of Score Indicator.
     *
     * @param score .
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.rectangle = new Rectangle(0, 0, 800, 20);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.lightGray);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.lightGray);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.WHITE);
        surface.drawText((int) ((rectangle.getUpperLeft().getX() + rectangle.getWidth()) / 3),
                (int) ((rectangle.getUpperLeft().getY() + rectangle.getUpperLeft().getY() + rectangle.getHeight()) / 2),
                "score: " + Integer.toString(score.getValue()), 12);


    }

    /**
     * The function do noting.
     *
     * @param dt .
     */
    public void timePassed(double dt) {
        ;
    }

}