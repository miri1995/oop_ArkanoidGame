import java.awt.Color;
import java.util.Scanner;
import java.lang.reflect.Field;

/**
 * Created by MIRI on 15/06/2017.
 */
public class ColorsParser {

    private String s;

    /**
     * Constructor of ColorsParser.
     *
     * @param s .
     */
    public ColorsParser(String s) {
        this.s = s;
    }


    /**
     * parse color definition.
     *
     * @return return the specified color.
     */
    public java.awt.Color colorFromString() {
        if (s.startsWith("RGB")) {
            String n = s.substring(4, s.length() - 2);
            Scanner scan = new Scanner(n).useDelimiter(",");
            return new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
        if (s.contains("black")) {
            return Color.black;
        }
        if (s.contains("blue")) {
            return Color.blue;
        }
        if (s.contains("cyan")) {
            return Color.cyan;
        }
        if (s.contains("gray")) {
            return Color.gray;
        }
        if (s.contains("lightGray")) {
            return Color.lightGray;
        }
        if (s.contains("green")) {
            return Color.green;
        }
        if (s.contains("orange")) {
            return Color.orange;
        }
        if (s.contains("pink")) {
            return Color.pink;
        }
        if (s.contains("red")) {
            return Color.red;
        }
        if (s.contains("white")) {
            return Color.white;
        }
        if (s.contains("yellow")) {
            return Color.yellow;
        } else {
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                return (Color) field.get(null);
            } catch (Exception e) {
                return null; // Not defined
            }
        }
    }
}