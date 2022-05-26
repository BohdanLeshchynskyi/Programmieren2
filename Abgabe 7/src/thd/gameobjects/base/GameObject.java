package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameview.GameView;

/**
 * base class for game objects.
 */
public abstract class GameObject {
    protected final GameView gameView;
    protected final Position position;

    protected final GamePlayManager gamePlayManager;
    protected double speedInPixel;
    protected double height;
    protected double width;
    protected double rotation;
    protected double size;

    /**
     * constructor of GameObject.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        this.position = new Position();
    }

    /**
     * adds object to the canvas of GameView.
     */
    public abstract void addToCanvas();

    /**
     * updates the current state of the game object.
     */
    public abstract void updateStatus();

    /**
     * Adjusts the position according to the game world`s shift.
     * @param shiftX shift on the x-axis
     * @param shiftY shift on the y-axis
     */
    public void worldHasMoved(double shiftX, double shiftY) {
        position.x -= shiftX;
        position.y -= shiftY;
    }
}
