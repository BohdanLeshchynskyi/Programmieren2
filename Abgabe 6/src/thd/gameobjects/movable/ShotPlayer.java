package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

/**
 * class representing a shot of a player`s helicopter.
 */
class ShotPlayer extends CollidableGameObject implements AutoMovable {
    private String shotPixel;

    /**
     * Cunstrucotor of {@link ShotPlayer}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param xStart a start x-coordinate position which is the current x-coordinate of the player`s helicopter
     * @param yStart a start y-coordinate position which is the current y-coordinate of the player`s helicopter
     */
    ShotPlayer(GameView gameView, GamePlayManager gamePlayManager, double xStart, double yStart) {
        super(gameView, gamePlayManager);
        this.shotPixel = "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM";
        this.position.x = xStart;
        this.position.y = yStart;
        this.speedInPixel = 7;
        this.height = 1;
        this.width = 30;
        this.rotation = 0;
        this.size = 2.5;
        this.hitBoxOffsetX = width * size * 0.5;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size * 0.5;
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
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

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == HelicopterEnemy.class || other.getClass() == JetEnemy.class) {
            gamePlayManager.destroy(this);
        }
    }
}
