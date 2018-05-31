import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 01/05/2017
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBall;
    private Counter score;
    private ScoreTrackingListener stl;
    private ScoreIndicator si;
    private Counter lives;
    private LivesIndicator liveInd;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private GameLevelIndicator levelIndicator;

    private HighScoresTable highScoresTable;
    private String endKey;


    /**
     * The constructor of GameLevel.
     *
     * @param levelInformation .
     * @param keyboard         .
     * @param runner           .
     * @param lives            .
     * @param score            .
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner,
                     Counter lives, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBall = new Counter();
        this.score = score;
        this.stl = new ScoreTrackingListener(score);
        this.si = new ScoreIndicator(score);
        this.lives = lives;
        this.liveInd = new LivesIndicator(lives);
        this.runner = runner;
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.levelIndicator = new GameLevelIndicator(this.levelInformation.levelName(), 800, 600);

    }

    /**
     * Add collidable to the GameLevel.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the GameLevel.
     *
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collidable to the GameLevel.
     *
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite to the GameLevel.
     *
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */

    public void initialize() {
        //   Sleeper sleeper = new Sleeper();
        HitListener blockRemove = new BlockRemover(this, this.remainingBlocks);
        HitListener ballRemove = new BallRemover(this, this.remainingBall);


        Map<Integer, BackgroundForBlock> map = new HashMap<>();
        map.put(-1, new BackgroundForBlock(Color.GRAY));

        //add background.
        this.sprites.addSprite(levelInformation.getBackground());

        //add a upper limit block.
        Rectangle upper = new Rectangle(0, 20, 800, 20);
        Block upperB = new Block(upper, map, -1);
        upperB.addToGame(this);

        //add a left limit block.
        Rectangle left = new Rectangle(0, 20, 20, 580);
        Block leftB = new Block(left, map, -1);
        leftB.addToGame(this);

        //add a lower limit block.
        Rectangle lower = new Rectangle(20, 600, 760, 20);
        Block lowerB = new Block(lower, map, -1);
        lowerB.addToGame(this);
        lowerB.addHitListener(ballRemove);

        //add a right limit block.
        Rectangle right = new Rectangle(780, 20, 20, 580);
        Block rightB = new Block(right, map, -1);
        rightB.addToGame(this);



        //add the name of the level.
        this.levelInformation.levelName();


        //add paddle.
        Rectangle rectToPaddle = new Rectangle(380, 560,
                this.levelInformation.paddleWidth(), 20);
        this.paddle = new Paddle(keyboard, rectToPaddle, 800, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);


        //add blocks.
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(stl);
            block.addHitListener(blockRemove);
            this.remainingBlocks.increase(1);
        }


        this.addSprite(si);
        this.addSprite(levelIndicator);
        this.addSprite(liveInd);

    }


    /**
     * Run the game -- start the animation loop.
     */

    public void playOneTurn() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.createBallsOnTopOfPaddle(); // or a similar method
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.remainingBall.getValue() == 0) {
            this.lives.decrease(1);
            this.paddle.removeFromGame(this);
        }
    }

    /**
     * create balls.
     */
    public void createBallsOnTopOfPaddle() {
        remainingBall.increase(this.levelInformation.numberOfBalls());
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(380, 550, 5, Color.RED);
            ball.addToGame(this);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.setGame(environment);
        }
        this.paddle.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);


        if (this.remainingBlocks.getValue() == 0) {
            score.increase(100);
            //paddle.removeFromGame(this);
            this.running = false;
        }

        if (this.remainingBall.getValue() == 0) {
            paddle.removeFromGame(this);
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }



    }

    /**
     * @return the remaining blocks.
     */
    public int getRemainBlocks() {

        return this.remainingBlocks.getValue();
    }

}

