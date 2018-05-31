import java.awt.Color;
import java.awt.Image;

/**
 * Created by MIRI on 14/06/2017.
 */
public class BackgroundForBlock {
    private java.awt.Color color;
    private Image img;

    /**
     * Constructor of BackgroundForBlock.
     *
     * @param color .
     */
    public BackgroundForBlock(Color color) {
        this.color = color;
        this.img = null;
    }

    /**
     * Constructor of BackgroundForBlock.
     *
     * @param img .
     */
    public BackgroundForBlock(Image img) {
        this.img = img;
        this.color = null;
    }

    /**
     * @return color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return image.
     */
    public Image getImg() {
        return this.img;
    }
}