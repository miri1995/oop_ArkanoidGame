/**
 * Created by MIRI on 17/06/2017.
 */
public class SetsInformation {
    private String key;
    private String levelOfDifficulty;
    private String path;

    /**
     * @param key               .
     * @param levelOfDifficulty .
     * @param path              .
     */
    public SetsInformation(String key, String levelOfDifficulty, String path) {
        this.key = key;
        this.levelOfDifficulty = levelOfDifficulty;
        this.path = path;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the levelOfDifficulty
     */
    public String getLevelOfDifficulty() {
        return levelOfDifficulty;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }


}