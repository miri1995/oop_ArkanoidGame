import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIRI on 09/06/2017.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;
    private String endKey;
    private boolean stop = false;
    private KeyboardSensor keyboard;

    /**
     * @param scores   .
     * @param endKey   .
     * @param keyboard .
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor keyboard) {
        this.scores = scores;
        this.endKey = endKey;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.YELLOW);
        d.drawText(30, 50, "High Scores:", 32);
        d.drawText(100, 120, "Player Name", 24);
        d.drawText(450, 120, "Score", 24);
        d.setColor(Color.WHITE);
        d.drawLine(100, 100, 700, 100);
        int maxScores = this.scores.getHighScores().size();
        List<ScoreInfo> list = new ArrayList<ScoreInfo>();
        list = this.scores.getHighScores();
        int xName = 100;
        int xScore = 450;
        for (int i = 0; i < maxScores; i++) {
            int y = 150 + i * 25;
            d.setColor(Color.YELLOW);
            d.drawText(xName, y, list.get(i).getName(), 20);
            d.drawText(xScore, y, String.valueOf(list.get(i).getScore()), 20);
            d.drawText(200, 500, "Press space to continue", 30);
        }

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

   /* public HighScoresTable getScores() {
        return this.scores;
    }*/
}