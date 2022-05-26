package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * represents an enemy helicopter.
 */
public class HelicopterEnemy extends GameObject implements AutoMovable {
    private boolean flyFromLeftToRight;
    private String helicopterPixel;
    /**
     * Constructor of the {@link HelicopterEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public HelicopterEnemy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.speedInPixel = 3;
        this.height = 9;
        this.width = 12;
        this.rotation = 0;
        this.size = 2.5;
        this.position.x = 0;
        this.position.y = 400;
        this.flyFromLeftToRight = true;
        this.helicopterPixel = "WWWWW\n" +
                               "   WWWWW\n" +
                               "   WW\n" +
                               "  WWWW   WWW\n" +
                               "WWWWWWWWWWW\n" +
                               "WWWWWWWW\n" +
                               "  WWWW\n" +
                               "   WW\n" +
                               "  WWWW\n";
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition() {
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
            flyFromLeftToRight = position.x + width * size + speedInPixel >= GameView.WIDTH ? false : true;
        } else {
            position.left(speedInPixel);
            flyFromLeftToRight = position.x - width * size - speedInPixel <= 0 ? true : false;
        }
    }
    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(helicopterPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {}
}
