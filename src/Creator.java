import javax.imageio.ImageIO;
import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MIRI on 14/06/2017.
 */
public class Creator implements BlockCreator {
    private Map<String, String> values;
    private Map<Integer, BackgroundForBlock> background;
    private java.io.BufferedReader reader;

    /**
     * Constructor of Creator.
     *
     * @param v .
     */
    public Creator(Map<String, String> v) {
        this.values = v;
    }

    /**
     * @param xpos .
     * @param ypos .
     * @return block.
     */
    public Block create(int xpos, int ypos) {
        Rectangle rectangle = new Rectangle(xpos, ypos, Integer.parseInt(values.get("width")),
                Integer.parseInt(values.get("height")));

        background = createMap();

        Block block = new Block(rectangle, background, Integer.parseInt(values.get("hit_points")));
        return block;
    }

    /**
     * @return map.
     */
    Map<Integer, BackgroundForBlock> createMap() {
        Map<Integer, BackgroundForBlock> b = new HashMap<>();
        int hitPoints = Integer.parseInt(values.get("hit_points"));
        if (values.get("fill") != null) {
            for (int i = 1; i <= hitPoints; i++) {
                b.put(i, createBackground(values.get("fill")));
            }
        }
        for (int i = 1; i <= hitPoints; i++) {
            if (values.get("fill-" + Integer.toString(i)) != null) {
                b.put(i, createBackground(values.get("fill-" + Integer.toString(i))));
            }
        }
        return b;
    }

    /**
     * @param s .
     * @return Background to block.
     */
    public BackgroundForBlock createBackground(String s) {
        BackgroundForBlock bb = null;
        if (s.startsWith("image")) {
            Image img = null;
            String st = s.substring(6, s.length() - 1);
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(st);
                img = ImageIO.read(is);
                bb = new BackgroundForBlock(img);
            } catch (IOException e) {
                System.out.println("can't find the image");
            }
        } else {
            ColorsParser color = new ColorsParser(s.substring(6, s.length() - 1));
            bb = new BackgroundForBlock(color.colorFromString());
        }
        return bb;
    }
}