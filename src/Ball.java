import biuoop.DrawSurface;

/**
 * @author Miri Asher <miriasher@gmail.com>
 * @version 1.8
 * @since 04/04/2017
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private double dX;
    private double dY;
    private GameEnvironment gameEnvironment;
    private Velocity vl;


    /**
     * The constructor of  the Ball.
     *
     * @param center the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * The constructor of  the Ball.
     *
     * @param centerX the X value of the center of the ball.
     * @param centerY the Y value of the center of the ball.
     * @param r       the radius of the ball.
     * @param color   the color of the ball.
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color) {
        center = new Point(centerX, centerY);
        this.radius = r;
        this.color = color;
    }

    // accessors

    /**
     * @return the X value of the center of the ball.
     */
    public int getX() {
        int x = (int) this.center.getX();
        return x;
    }

    /**
     * @return the Y value of the center of the ball.
     */
    public int getY() {
        int y = (int) this.center.getY();
        return y;
    }

    /**
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {

        return this.color;
    }

    /**
     * The function draw the ball on the given DrawSurface.
     *
     * @param surface the board.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
    }

    /**
     * @param v velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.dX = v.getDX();
        this.dY = v.getDY();
        this.vl = v;
    }

    /**
     * @param game the GameEnvironment.
     */
    public void setGame(GameEnvironment game) {
        this.gameEnvironment = game;
    }

    /**
     * @param dx change on the X axis
     * @param dy change on the Y axis
     */
    public void setVelocity(double dx, double dy) {
        this.dX = dx;
        this.dY = dy;
        Velocity newV = new Velocity(dx, dy);
    }

    /**
     * @return return the new velocity (after the change on the X and Y axises.
     */
    public Velocity getVelocity() {
        double dX1 = this.dX;
        double dY1 = this.dY;
        Velocity vBall = new Velocity(dX1, dY1);
        return vBall;
    }

    /**
     * @param g game environment.
     */
    public void setGameEn(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * The function is responsible for the movement of the ball:
     * if it reaches the boundaries of the screen: changes its speed to the opposite direction (-dx).
     *
     * @param dt .
     */

    public void moveOneStep(double dt) {
        Point next = this.getVelocity().applyToPoint(this.center, dt);
        //next = new Point(Math.ceil(next.getX()), Math.ceil(next.getY()));
        Line trajectory = new Line(this.center, next);
        CollisionInfo collInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collInfo != null) {
            this.setVelocity(collInfo.collisionObject().hit(this, collInfo.collisionPoint(), this.getVelocity()));
            this.center = this.getVelocity().applyToPoint((this.center), dt);
            if (this.vl.getDX() < 0) {
                this.center = new Point(this.center.getX() + 1, this.center.getY());
            }
            if (this.vl.getDX() > 0) {
                this.center = new Point(this.center.getX() - 1, this.center.getY());
            }
            if (this.vl.getDY() > 0) {
                this.center = new Point(this.center.getX(), this.center.getY() - 1); //+ this.getSize());
            }
            if (this.vl.getDY() < 0) {
                this.center = new Point(this.center.getX(), this.center.getY() + 1); //- this.getSize());
            }
        } else {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }
    }

    /**
     * add the object to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * call to moveonestep.
     *
     * @param dt .
     */

    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * remove the ball from the game.
     *
     * @param game .
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}





