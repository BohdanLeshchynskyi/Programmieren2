package thd.gameobjects.movable;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

/**
 * represents an enemy helicopter.
 */
public class HelicopterEnemy {
    private final Position position;
    private double speedInPixel;
    private final GameView gameView;
    private double height;
    private double width;
    private boolean flyFromLeftToRight;

    /**
     * Constructor of the {@link HelicopterEnemy}.
     * @param gameView shared reference of a {@link GameView} object for displaing the game
     */
    public HelicopterEnemy(GameView gameView) {
        this.speedInPixel = 1;
        this.gameView = gameView;
        this.height = 50;
        this.width = 100;
        this.position = new Position(width/2,GameView.HEIGHT - height/2 - 6);
        this.flyFromLeftToRight = true;
    }

    /**
     * updates current position of the object.
     */
    public void updatePosition() {
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
            flyFromLeftToRight = position.getX() + width/2 >= GameView.WIDTH ? false : true;
        } else {
            position.left(speedInPixel);
            flyFromLeftToRight = position.getX() - width/2 <= 0 ? true : false;
        }
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 5, false, Color.YELLOW);
        gameView.addRectangleToCanvas(position.getX() - width/2, position.getY() - height/2, width, height, 5, false, Color.BLUE);
    }
}
