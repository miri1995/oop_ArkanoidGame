import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by MIRI on 07/06/2017.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreList;


    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size means that the table holds up to size top scores..
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>();
    }


    /**
     * Add a high-score.
     *
     * @param score .
     */
    public void add(ScoreInfo score) {
        scoreList.add(score);
        for (int i = 0; i < scoreList.size(); i++) {
            for (int j = 0; j < scoreList.size() - i - 1; j++) {
                if (scoreList.get(j).getScore() < scoreList.get(j + 1).getScore()) {
                    ScoreInfo tempScore = scoreList.get(j);
                    this.scoreList.set(j, this.scoreList.get(j + 1));
                    this.scoreList.set((j + 1), tempScore);
                }
            }
        }
        System.out.println(" " + scoreList.size());
        System.out.println(" " + this.size);
        if (this.scoreList.size() > this.size) {
            this.scoreList.remove(this.size - 1);
        }
        System.out.println(" " + scoreList.size());
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.size;
    }


    /**
     * @return the current high scores.
     * The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreList;
    }


    /**
     * @param score .
     * @return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     */
    public int getRank(int score) {
        List<Integer> list = new ArrayList<>();
        List<Integer> reverse = new ArrayList<>();
        if (this.scoreList.size() == this.size()) {
            if (this.scoreList.get(this.size() - 1).getScore() >= score) {
                return this.size + 1;
            }
        }
        for (ScoreInfo s : this.scoreList) {
            list.add(s.getScore());
        }
        list.add(score);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            reverse.add(list.get(list.size() - 1 - i));
        }
        return reverse.indexOf(score) + 1;
    }


    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreList.clear();
    }


    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename .
     * @throws IOException .
     */
    public void load(File filename) throws IOException {
        HighScoresTable scoresTable = loadFromFile(filename);
        this.scoreList.clear();
        this.scoreList = scoresTable.getHighScores();
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename .
     * @throws IOException .
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oS = null;
        try {
            oS = new ObjectOutputStream(new FileOutputStream(filename));
            oS.writeObject(this.getHighScores());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oS != null) {
                oS.close();
            }
        }
    }

    /**
     * @param filename .
     * @return Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable scoresTable = new HighScoresTable(2);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            List<ScoreInfo> fromFile = (List<ScoreInfo>) objectInputStream.readObject();
            if (fromFile != null) {
                scoresTable.clear();
                scoresTable.scoreList.addAll(fromFile);
            }
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            //return scoresTable;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            //return scoresTable;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            //return scoresTable;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        return scoresTable;
    }


}


