package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * represents an enemy jet.
 */
public class JetEnemy extends GameObject {
    private boolean flyFromLeftToRight;


    /**
     * Constructor of the {@link JetEnemy}.
     * @param gameView shared reference of a {@link GameView} object for displaing the game
     */
    public JetEnemy(GameView gameView) {
        super(gameView);
        this.position.x = 0;
        this.position.y = GameView.HEIGHT / 2;
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
}
