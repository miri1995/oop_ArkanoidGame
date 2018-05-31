/**
 * Created by MIRI on 10/06/2017.
 */

/**
 * @param <T> .
 */
public class Selection<T> {
    private String key;
    private String message;
    private T value;
    // private boolean isSubMenu;
    private Menu<T> subMenu;

    /**
     * Constructor of Selection.
     *
     * @param key     .
     * @param message .
     * @param value   .
     * @param subMenu .
     */
    public Selection(String key, String message, T value, Menu<T> subMenu/*, boolean isSubMenu*/) {
        this.key = key;
        this.message = message;
        this.value = value;
        //this.isSubMenu = isSubMenu;
        this.subMenu = subMenu;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }


    /**
     * @return the value
     */
    public T getValue() {
        return this.value;
    }

 /*   public Menu<T> getSubMenu() {
        return this.subMenu;
    }

    public boolean isSubMenu() {
        return this.isSubMenu;
    }*/

}