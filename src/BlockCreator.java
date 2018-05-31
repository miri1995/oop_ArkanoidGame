/**
 * Created by MIRI on 14/06/2017.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos .
     * @param ypos .
     * @return block.
     */
    Block create(int xpos, int ypos);
}