import biuoop.DrawSurface;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 01/06/2017
 */
public class GameLevelIndicator implements Sprite {
    private Rectangle r;
    private String text;

    /**
     * Constructor of Game Level Indicator.
     * @param levelName .
     * @param width .
     * @param hi height.
     */
    public GameLevelIndicator(String levelName, int width, int hi) {
        r = new Rectangle(new Point(0, 0), width, 20);
        this.text = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((int) ((r.getUpperLeft().getX() + r.getUpperLeft().getX() + r.getWidth()) - 300),
                (int) ((r.getUpperLeft().getY() + r.getUpperLeft().getY() + r.getHeight()) / 2),
                "Level Name: " + this.text, 12);
    }

    @Override
    public void timePassed(double dt) {
    }

}