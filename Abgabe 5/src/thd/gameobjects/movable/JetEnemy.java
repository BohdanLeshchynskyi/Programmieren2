package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * represents an enemy jet.
 */
public class JetEnemy extends GameObject implements AutoMovable {
    private boolean flyFromLeftToRight;
    /**
     * Constructor of the {@link JetEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public JetEnemy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        //this.position.x = 0;
        //this.position.y = GameView.HEIGHT / 2;
        this.position.x = 100;
        this.position.y = 100;
        this.speedInPixel = 1;
        this.size = 0.1;
        this.rotation = 0;
        this.flyFromLeftToRight = true;
    }

    @Override
    public String toString() {
        return "Jet: " + position;
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition() {
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
            position.y = position.y + Math.round(Math.sin(Math.toRadians(position.x * 2)));
            flyFromLeftToRight = position.x + speedInPixel >= GameView.WIDTH ? false : true;
        } else {
            position.left(speedInPixel);
            position.y = position.y - Math.round(Math.sin(Math.toRadians(position.x * 2)));
            flyFromLeftToRight = position.x - speedInPixel <= 0 ? true : false;
        }
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("blueEnemyJet.png", position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {}
}
