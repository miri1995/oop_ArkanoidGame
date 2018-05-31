import java.io.FileNotFoundException;

/**
 * Created by MIRI on 10/06/2017.
 */

/**
 * @param <T> .
 */
public interface Task<T> {
    /**
     * @return task.
     * @throws FileNotFoundException if fill isn`t found.
     */
    T run() throws FileNotFoundException;

}