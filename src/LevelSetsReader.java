import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIRI on 17/06/2017.
 */
public class LevelSetsReader {

    /**
     * @param reader .
     * @return list of sets level.
     */
    public List<SetsInformation> readSets(java.io.Reader reader) {
        List<SetsInformation> setInformationsList = new ArrayList<>();
        String key = null;
        String levelOfDifficulty = null;
        String path = null;
        try {
            LineNumberReader bufferedReader = new LineNumberReader(reader);
            String line;
            line = bufferedReader.readLine();
            String[] splittedLine;
            while (line != null) {
                if (bufferedReader.getLineNumber() % 2 == 0) {
                    path = line;
                    setInformationsList.add(new SetsInformation(key, levelOfDifficulty, path));
                } else {
                    splittedLine = line.split(":");
                    key = splittedLine[0];
                    levelOfDifficulty = splittedLine[1];
                }
                try {
                    line = bufferedReader.readLine();

                    if (line == null) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return setInformationsList;
    }

}