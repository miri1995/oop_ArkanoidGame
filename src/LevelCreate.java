import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;

/**
 * Created by MIRI on 15/06/2017.
 */
public class LevelCreate implements LevelInformation {

    private Map<String, String> map;
    private String blocksPattern;

    /**
     * Constructor of LevelCreate.
     *
     * @param map           of the items in the level.
     * @param blocksPattern .
     */
    public LevelCreate(Map<String, String> map, String blocksPattern) {
        this.map = new HashMap<>();
        this.map.putAll(map);
        this.blocksPattern = blocksPattern;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velo = new ArrayList<Velocity>();
        String[] parts = map.get("ball_velocities").split(" ");
        for (int i = 0; i < parts.length; i++) {
            String[] veloci = parts[i].split(",");
            Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(veloci[0]), Double.parseDouble(veloci[1]));
            velo.add(v);

        }
        return velo;
    }

    @Override
    public int paddleSpeed() {
        return Integer.parseInt(map.get("paddle_speed"));

    }

    @Override
    public int paddleWidth() {
        return Integer.parseInt(map.get("paddle_width"));
    }

    @Override
    public String levelName() {
        return map.get("level_name");

    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                if (map.get("background").substring(0, 5).equals("image")) {
                    Image img = null;
                    String s = map.get("background").substring(6, map.get("background").length() - 1);
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
                        img = ImageIO.read(is);
                    } catch (IOException e) {
                        System.out.println("can't find the image");
                    }
                    d.drawImage(0, 0, img); // draw the image at location 10, 20.
                } else {
                    ColorsParser color = new ColorsParser(map.get("background").substring(6));
                    d.setColor(color.colorFromString());
                    d.fillRectangle(20, 40, 780, 560);
                }
            }

            @Override
            public void timePassed(double dt) {
                ;
            }
        };
        return sprite;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(map.get("block_definitions"));
            Reader r = new InputStreamReader(is);
            reader = new BufferedReader(r);


        } catch (Exception e) {
            e.printStackTrace();
        }
        BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(reader);
        int startX = Integer.parseInt(
                map.get("blocks_start_x")), startY = Integer.parseInt(map.get("blocks_start_y")),
                rowHeight = Integer.parseInt(map.get("row_height"));
        int x, y = startY;
        String[] lines = this.blocksPattern.split("\n");
        for (int i = 0; i < lines.length; i++) {
            x = startX;
            for (int j = 0; j < lines[i].length(); j++) {
                char c = lines[i].charAt(j);
                String sym = Character.toString(c);
                if (factory.isBlockSymbol(sym)) {
                    Block b = factory.getBlock(sym, x, y);
                    blocks.add(b);
                    x += b.getCollisionRectangle().getWidth();
                } else {
                    if (factory.isSpaceSymbol(sym)) {
                        x += factory.getSpaceWidth(sym);
                    }
                }
            }
            y += rowHeight;
        }
        return blocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return Integer.parseInt(map.get("num_blocks"));
    }

}