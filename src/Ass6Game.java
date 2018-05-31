import biuoop.KeyboardSensor;
import biuoop.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by MIRI on 15/06/2017.
 */
public class Ass6Game {
    /**
     * @param args .
     * @throws FileNotFoundException if fill is`t found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui, 60);
        File highScoreFile = new File("highscores");
        GameFlow gameFlow = new GameFlow(ar, ks, highScoreFile);
        HighScoresTable highScoresTable = new HighScoresTable(2);
        try {
            highScoresTable.load(highScoreFile);
        } catch (IOException e) {
            System.out.println("can`t load file");
        }
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable, " ", ks);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        Reader r = new InputStreamReader(is);
        LevelSetsReader levelSetsReader = new LevelSetsReader();
        List<SetsInformation> l = levelSetsReader.readSets(r);

        MenuAnimation<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Choose level", ks);
        for (int i = 0; i < l.size(); i++) {
            final int j = i;
            Task<Void> levelList = new Task<Void>() {
                @Override
                public Void run() {
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(l.get(j).getPath());
                        Reader r = new InputStreamReader(is);
                        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                        List<LevelInformation> l = levelSpecificationReader.fromReader(r);
                        l.addAll(new LevelSpecificationReader().fromReader(r));
                        GameFlow gameFlow = new GameFlow(ar, ks, highScoreFile);
                        gameFlow.runLevels(l);
                    } catch (Exception e) {
                        ;
                    }
                    return null;
                }
            };
            subMenu.addSelection(l.get(i).getKey(), "(" + l.get(i).getKey().toString() + ") "
                    + l.get(i).getLevelOfDifficulty(), levelList);
        }


        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", ks);


        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };

        Task<Void> hiScoresTask = new Task<Void>() {
            @Override
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, highScoresAnimation));
                return null;
            }
        };

        Task<Void> runGame = new Task<Void>() {
            @Override
            public Void run() {
                try {
                    ar.run(subMenu);
                    subMenu.getStatus().run();
                } catch (Exception e) {
                    ;
                }
                return null;
            }
        };

        menu.addSelection("s", "(s) Start Game", runGame);
        menu.addSelection("h", "(h) High scores", hiScoresTask);
        menu.addSelection("q", "(q) Quit", quit);


        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.clearMenu();
            subMenu.clearMenu();
        }
    }

}