package thd.gameobjects.base;

import thd.gameview.GameView;

/**
 * base class for game objects.
 */
public class GameObject {
    protected final GameView gameView;
    protected final Position position;
    protected double speedInPixel;
    protected double height;
    protected double width;
    protected double rotation;
    protected double size;

    /**
     * constructor of GameObject.
     * @param gameView reference of GameView
     */
    public  GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position();
    }

    /**
     * updates object`s Position.
     */
    public void updatePosition() {}

    /**
     * adds object to the canvas of GameView.
     */
    public void addToCanvas() {}

    /**
     * updates the current state of the game object.
     */
    public void updateStatus() {}

    /**
     * returns object`s Position.
     * @return object`s Postions
     */
    public Position getPosition() {
        return position;
    }
}
