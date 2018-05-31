import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 01/05/2017
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * The constructor of SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Add sprite to the GameLevel.
     *
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);

    }


    /**
     * call timePassed() on all sprites.
     *
     * @param dt .
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed(dt);
        }
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d surface the board.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }

    /**
     * remove sprite from the game.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}