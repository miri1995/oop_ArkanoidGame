import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MIRI on 14/06/2017.
 */
public class BlocksDefinitionReader {

    /**
     * @param reader .
     * @return blocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.BufferedReader reader) {
        BlocksFromSymbolsFactory bfsf = new BlocksFromSymbolsFactory();
        try {
            Map<String, String> deafult = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    line = reader.readLine();
                }
                if (line.startsWith("default")) {
                    deafult = BlocksDefinitionReader.parseLine(line);
                }
                if (line.startsWith("bdef")) {
                    Map<String, String> creator = parseLine(line);
                    if (check(creator).size() != 0) {
                        for (String s : check(creator)) {
                            if (deafult.get(s) == null) {
                                throw new Exception("missed default values");
                            }
                            creator.put(s, deafult.get(s));
                        }
                    }
                    bfsf.addBlockCreator(creator.get("symbol"), new Creator(creator));
                }
                if (line.startsWith("sdef")) {
                    Map<String, String> spacer = parseLine(line);
                    if (spacer.size() < 2) {
                        System.out.println("missing values for spacer");
                    }
                    bfsf.addSpacer(spacer.get("symbol"), Integer.parseInt(spacer.get("width")));
                }
            }
            return bfsf;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bfsf;
    }

    /**
     * @param s string.
     * @return map.
     */
    private static Map<String, String> parseLine(String s) {
        Map<String, String> result = new HashMap<>();
        String[] parts = s.split(" ");
        for (int i = 1; i < parts.length; i++) {
            String[] values = parts[i].split(":");
            result.put(values[0], values[1]);
        }
        return result;
    }

    /**
     * @param result .
     * @return list of strings that component block.
     */
    public static List<String> check(Map<String, String> result) {
        List<String> l = new ArrayList<>();
        if (!result.containsKey("width")) {
            l.add("width");
        }
        if (!result.containsKey("height")) {
            l.add("height");
        }
        if (!result.containsKey("hit_points")) {
            l.add("hit_points");
        }
        if (!result.containsKey("fill") && !result.containsKey("fill-1")) {
            l.add("fill");
        }

        return l;
    }

}