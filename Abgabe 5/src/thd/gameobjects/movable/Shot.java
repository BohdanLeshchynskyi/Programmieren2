package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * class representing a shot of a player`s helicopter.
 */
class Shot extends GameObject implements AutoMovable {
    private String shotPixel;
    private boolean outOfScreen;

    /**
     * Cunstrucotor of {@link Shot}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param xStart a start x-coordinate position which is the current x-coordinate of the player`s helicopter
     * @param yStart a start y-coordinate position which is the current y-coordinate of the player`s helicopter
     */
    Shot (GameView gameView, GamePlayManager gamePlayManager, double xStart, double yStart) {
        super(gameView, gamePlayManager);
        this.shotPixel = "MMMMMMMMMMMMMMMMMMMM";
        this.position.x = xStart;
        this.position.y = yStart;
        this.outOfScreen = false;
        this.speedInPixel = 7;
        this.height = 1;
        this.width = 15;
        this.rotation = 0;
        this.size = 2.5;
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
        outOfScreen = position.x + width * size + speedInPixel >= GameView.WIDTH;
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(shotPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {
        if (position.x + width < 0 || position.x < -width) {
            this.gamePlayManager.destroy(this);
        }
    }
}
