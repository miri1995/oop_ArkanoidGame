/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 24/05/2017
 */
public class Counter {

    private int count;

    /**
     * Constructor of Counter.
     */
    public Counter() {
        this.count = 0;
    }


    /**
     * Add number to current count.
     *
     * @param number .
     */
    public void increase(int number) {
        this.count = this.count + number;
    }


    /**
     * Subtract number from current count.
     *
     * @param number .
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}