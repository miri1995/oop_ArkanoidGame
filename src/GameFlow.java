import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.GUI;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 31/05/2017
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private SpriteCollection spriteCollection;
    private HighScoresTable highScoresTable;
    private String endKey;
    private File file;
    private int status;


    /**
     * Constructor of game flow.
     *
     * @param animationRunner .
     * @param keyboardSensor  .
     * @param file            .
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, File file) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.lives = new Counter();
        this.lives.increase(7);
        this.score = new Counter();
        this.spriteCollection = new SpriteCollection();
        this.highScoresTable = new HighScoresTable(10);
        this.endKey = new String();
        this.file = file;
        this.status = 1;

    }

    /**
     * creating the differnet levels, and moving from one level to the next.
     *
     * @param levels .
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        try {
            this.highScoresTable.load(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.lives, this.score);
            level.initialize();
            while (lives.getValue() != 0 && level.getRemainBlocks() != 0) {
                level.playOneTurn();
            }
            if (lives.getValue() == 0) {
                status = 0;
                break;
            }
            /*
            if (++i == levels.size()) {
                status = true;
                break;
            }
            */
        }


        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, new EndScreen(this.keyboardSensor, status, this.score)));
        if (this.highScoresTable.getRank(score.getValue()) < this.highScoresTable.size()) {
            GUI gui = this.animationRunner.getGui();
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            System.out.println(name);
            highScoresTable.add(new ScoreInfo(name, score.getValue()));
            try {
                highScoresTable.save(file);
            } catch (IOException e) {
                System.err.println("failed dealing with the highscores table.");
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                this.keyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.highScoresTable, this.endKey, this.keyboardSensor)));
        return;
    }


   /* public int getScore() {
        return this.score.getValue();
    }*/

    /**
     * @return scores.
     */
    public HighScoresTable getScores() {
        return this.highScoresTable;
    }
}
