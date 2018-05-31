
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MIRI on 13/06/2017.
 */
public class LevelSpecificationReader {

    /**
     * @param reader .
     * @return list of level information.
     */
    public static List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader reader1 = new BufferedReader(reader);
        try {
            String line = new String();
            StringBuilder level = new StringBuilder();
            StringBuilder blocks = new StringBuilder();
            while ((line = reader1.readLine()) != null) {
                if (line.startsWith("START_LEVEL") || line.startsWith("END_LEVEL")) {
                    continue;
                } else {
                    level.append("\n" + line);
                    if (line.startsWith("START_BLOCKS")) {
                        while (!line.equals("END_BLOCKS")) {
                            line = reader1.readLine();
                            blocks.append(line + "\n");
                        }
                        if (line.equals("END_BLOCKS")) {
                            LevelInformation create = new
                                    LevelCreate(LevelSpecificationReader.parseLine(level.toString()),
                                    blocks.toString());
                            levels.add(create);
                            level = new StringBuilder();
                            blocks = new StringBuilder();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

    /**
     * @param s .
     * @return map of them
     */
    public static Map<String, String> parseLine(String s) {
        Map<String, String> res = new HashMap<>();
        String[] lines = s.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(":");
            if (values.length == 2) {
                res.put(values[0], values[1]);
            }
        }
        return res;
    }


}