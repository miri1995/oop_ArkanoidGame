import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIRI on 10/06/2017.
 */

/**
 * @param <T> .
 */
public class MenuAnimation<T> implements Menu<T> {


    private List<Selection<T>> menuList;
    private T status;
    private String title;
    private boolean stop;
    private KeyboardSensor keyboardSensor;


    /**
     * @param title          .
     * @param keyboardSensor .
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor) {
        this.menuList = new ArrayList<>();
        this.keyboardSensor = keyboardSensor;
        this.title = title;
        this.stop = false;

    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        Selection<T> selection = new Selection<>(key, message, returnVal, null/*, Boolean.valueOf(false)*/);
        this.menuList.add(selection);
    }

    /**
     * @return the status.
     */
    public T getStatus() {

        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLUE);
        int xPos = 100;
        d.drawText(xPos + 100, 150, this.title, 65);
        d.setColor(Color.BLACK);
        d.drawText(xPos + 1 + 100, 150 + 1, this.title, 65);
        for (int i = 0; i < menuList.size(); i++) {
            d.setColor(Color.BLUE);
            int yPos = 350 + 45 * i;
            d.drawText(xPos, yPos, menuList.get(i).getMessage(), 35);
            d.setColor(Color.BLACK);
            d.drawText(xPos + 2, yPos + 2, menuList.get(i).getMessage(), 35);
            if (this.keyboardSensor.isPressed(menuList.get(i).getKey())) {
                this.status = menuList.get(i).getValue();
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * clear the menu.
     */
    public void clearMenu() {
        this.stop = false;
    }

    /**
     * Add submenu.
     *
     * @param key     .
     * @param message .
     * @param subMenu .
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        Selection<T> menuChoice = new Selection<T>(key, message, null, subMenu);
        this.menuList.add(menuChoice);

    }

}