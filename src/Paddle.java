import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 01/05/2017
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private double screenWidth;
    private int velocity;


    /**
     * The constructor of Paddle.
     *
     * @param keyboard    the keyboard sensor.
     * @param paddle      the rectangle that create rhe paddle.
     * @param screenWidth the width of the screen.
     * @param velocity    the velocity of paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle, double screenWidth, int velocity) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.screenWidth = screenWidth;
        this.velocity = velocity;
    }

   /* public int getSpeed() {
        return this.velocity;
    }*/

    /**
     * The function move left if the user click the left arrow (changes direction in X axis).
     *
     * @param dt .
     */
    public void moveLeft(double dt) {
        Rectangle rectangle = new Rectangle(this.paddle.getUpperLeft().getX() - this.velocity * dt,
                this.paddle.getUpperLeft().getY(), this.paddle.getWidth()
                , this.paddle.getHeight());
        this.paddle = rectangle;
    }

    /**
     * The function move right if the user click the right arrow.
     *
     * @param dt .
     */
    public void moveRight(double dt) {
        Rectangle rectangle = new Rectangle(this.paddle.getUpperLeft().getX() + this.velocity * dt,
                this.paddle.getUpperLeft().getY(), this.paddle.getWidth()
                , this.paddle.getHeight());
        this.paddle = rectangle;
    }

    /**
     * The function check if the "left" or "right" keys are pressed, and if so move it accordingly.
     *
     * @param dt .
     */
    public void timePassed(double dt) {
        double upperRightX = this.paddle.getUpperLeft().getX() + this.paddle.getWidth();
        double upperRightY = this.paddle.getUpperLeft().getY() + this.paddle.getWidth();
        Point upperRight = new Point(upperRightX, upperRightY);
        double upperLeftX = this.paddle.getUpperLeft().getX();
        if (paddle.getUpperLeft().getX() + paddle.getWidth() < this.screenWidth - 20) {
            if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight(dt);
            }
        }
        if (paddle.getUpperLeft().getX() > 20) {
            if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft(dt);
            }
        }

    }

    /**
     * The function draw the paddle on the given DrawSurface.
     *
     * @param d surface the board.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle((int) paddle.getUpperLeft().getX(),
                (int) paddle.getUpperLeft().getY(), (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    /**
     * The function return the "collision shape" of the object.
     *
     * @return he "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }


    /**
     * The function returns the velocity after the collision between the paddle and block.
     * (If hit the speed block which is in the opposite direction)
     * if the collision on the 1 part of the paddle- change velocity on the angle 300 degrees.
     * if the collision on the 2 part of the paddle- change velocity on the angle 330 degrees.
     * if the collision on the 3 part of the paddle- only change its vertical one.
     * if the collision on the 4 part of the paddle- change velocity on the angle 30 degrees.
     * if the collision on the 5 part of the paddle- change velocity on the angle 60 degrees.
     *
     * @param collisionPoint  the collision point between paddle and ball.
     * @param currentVelocity the velocity of the ball.
     * @param hitter          the ball that hitter.
     * @return the velocity after the collision between the ball and block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double width = this.paddle.getWidth();
        double upperLeftX = this.paddle.getUpperLeft().getX();
        double lengthOfPicePaddle = this.paddle.getWidth() / 5;

        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + paddle.getHeight());
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + paddle.getHeight());
        // if a collision on the up of limit of paddle.
        if (collisionPoint.getY() == this.paddle.getUpperLeft().getY()) {
            // the collision on the 1 part of the paddle2
            if (collisionPoint.getX() >= upperLeftX && collisionPoint.getX() <= upperLeftX + lengthOfPicePaddle) {
                Velocity v = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                return v;
            }
            // the collision on the 2 part of the paddle
            if (collisionPoint.getX() >= upperLeftX + lengthOfPicePaddle && collisionPoint.getX() <= upperLeftX
                    + (2 * lengthOfPicePaddle)) {
                Velocity v = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                return v;
            }
            // the collision on the 3 part of the paddle
            if (collisionPoint.getX() >= upperLeftX + (2 * lengthOfPicePaddle) && collisionPoint.getX() <= upperLeftX
                    + (3 * lengthOfPicePaddle)) {
                Velocity v = new Velocity(currentVelocity.getDX(), (-1) * currentVelocity.getDY());
                return v;
            }
            // the collision on the 4 part of the paddle
            if (collisionPoint.getX() >= upperLeftX + 3 * lengthOfPicePaddle && collisionPoint.getX() <= upperLeftX
                    + (4 * lengthOfPicePaddle)) {
                Velocity v = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                return v;
            }
            // the collision on the 5 part of the paddle
            if (collisionPoint.getX() >= upperLeftX + 4 * lengthOfPicePaddle && collisionPoint.getX() <= upperLeftX
                    + (5 * lengthOfPicePaddle)) {
                Velocity v = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                return v;
            }
        }
        // if a collision on the lower of limit of paddle.
        if (collisionPoint.getY() == bottomLeft.getY() && bottomLeft.getX() <= collisionPoint.getX()
                && collisionPoint.getX() <= bottomRight.getX()) {
            currentVelocity.setDY(-1 * currentVelocity.getDY());
        }
        // if a collision on the left of limit of paddle.
        if (collisionPoint.getX() == bottomLeft.getX() && upperLeft.getY() <= collisionPoint.getY()
                && collisionPoint.getY() <= bottomLeft.getY()) {
            currentVelocity.setDX(-1 * currentVelocity.getDX());
        }
        // if a collision on the right of limit of paddle.
        if (collisionPoint.getX() == bottomRight.getX() && upperRight.getY() <= collisionPoint.getY()
                && collisionPoint.getY() <= bottomRight.getY()) {
            currentVelocity.setDX(-1 * currentVelocity.getDX());
        }
        return currentVelocity;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove this paddle from the game.
     *
     * @param g .
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}
