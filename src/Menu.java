/**
 * Created by MIRI on 10/06/2017.
 */

/**
 * @param <T> .
 */
public interface Menu<T> extends Animation {

    /**
     * @param key       key to wait for.
     * @param message   line to print.
     * @param returnVal what to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status.
     */
    T getStatus();

    /**
     * add sub menu.
     *
     * @param key     .
     * @param message .
     * @param subMenu .
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}