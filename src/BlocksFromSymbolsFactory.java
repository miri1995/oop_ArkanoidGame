import java.util.HashMap;
import java.util.Map;

/**
 * Created by MIRI on 14/06/2017.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new HashMap<>();
    private Map<String, BlockCreator> blockCreators = new HashMap<>();

    /**
     * @param s .
     * @return returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        Integer intg = spacerWidths.get(s);
        if (intg != null) {
            return true;
        }
        return false;
    }


    /**
     * @param s .
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        BlockCreator bc = blockCreators.get(s);
        if (bc != null) {
            return true;
        }
        return false;
    }


    /**
     * @param s .
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }


    /**
     * @param s .
     * @param x .
     * @param y .
     * @return Return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * @param s .
     * @param i .
     */
    public void addSpacer(String s, Integer i) {
        spacerWidths.put(s, i);
    }

    /**
     * @param s  .
     * @param bc .
     */
    public void addBlockCreator(String s, BlockCreator bc) {
        blockCreators.put(s, bc);
    }
}