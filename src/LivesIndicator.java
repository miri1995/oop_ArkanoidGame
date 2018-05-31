import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 25/05/2017
 */
public class LivesIndicator implements Sprite {

    private Counter lives;
    private Rectangle rectangle;

    /**
     * Constructor of Lives Indicator.
     *
     * @param lives .
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
        this.rectangle = new Rectangle(new Point(0, 0), 800 / 2, 20);

    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.drawText((int) ((rectangle.getUpperLeft().getX() + rectangle.getWidth()) / 4),
                (int) ((rectangle.getUpperLeft().getY() + rectangle.getUpperLeft().getY() + rectangle.getHeight()) / 2),
                "Lives: " + Integer.toString(lives.getValue()), 12);


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