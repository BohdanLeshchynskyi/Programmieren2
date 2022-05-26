package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

class ShotFractionEnemy extends CollidableGameObject implements AutoMovable {
    private String shotPixel;
    enum MovingMode {
        UP, DOWN, LEFT_UP_DIAGONAL, LEFT_DOWN_DIAGONAL, RIGHT_UP_DIAGONAL, RIGHT_DOWN_DIAGONAL;
    }
    private MovingMode movingMode;
    /**
     * Cunstrucotor of {@link ShotEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param xStart a start x-coordinate position which is the current x-coordinate of some enemy
     * @param yStart a start y-coordinate position which is the current y-coordinate of some enemy
     * @param movingMode determines the way of movement
     */
    ShotFractionEnemy(GameView gameView, GamePlayManager gamePlayManager, double xStart, double yStart, MovingMode movingMode) {
        super(gameView, gamePlayManager);
        this.shotPixel = "OOO";
        this.position.x = xStart;
        this.position.y = yStart;
        this.speedInPixel = 5;
        this.height = 1;
        this.width = 3;
        this.rotation = 0;
        this.size = 4;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.movingMode = movingMode;
    }

    @Override
    public void updatePosition() {
        if(this.movingMode == MovingMode.UP) {
            position.up(speedInPixel);
        } else if (this.movingMode == MovingMode.DOWN) {
            position.down(speedInPixel);
        } else if (this.movingMode == MovingMode.LEFT_UP_DIAGONAL) {
            position.up(speedInPixel);
            position.left(speedInPixel);
        } else if (this.movingMode == MovingMode.LEFT_DOWN_DIAGONAL) {
            position.down(speedInPixel);
            position.left(speedInPixel);
        } else if (this.movingMode == MovingMode.RIGHT_UP_DIAGONAL) {
            position.up(speedInPixel);
            position.right(speedInPixel);
        } else if (this.movingMode == MovingMode.RIGHT_DOWN_DIAGONAL) {
            position.down(speedInPixel);
            position.right(speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(shotPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {
        if(!gameView.alarmIsSet("CounterUntilDestruction", this)){
            gameView.setAlarm("CounterUntilDestruction", this, 1500);
        } else if (gameView.alarm("CounterUntilDestruction", this)) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == HelicopterPlayer.class || other.getClass() == CarFriend.class) {
            gamePlayManager.destroy(this);
        }
    }
}