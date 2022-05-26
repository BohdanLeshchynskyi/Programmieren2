package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

class ShotEnemy extends CollidableGameObject implements AutoMovable {
    private String shotPixel;
    private enum MovingMode {
        STAY;
    }
    private MovingMode movingMode;
    /**
     * Cunstrucotor of {@link ShotEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param xStart a start x-coordinate position which is the current x-coordinate of some enemy
     * @param yStart a start y-coordinate position which is the current y-coordinate of some enemy
     */
    ShotEnemy(GameView gameView, GamePlayManager gamePlayManager, double xStart, double yStart) {
        super(gameView, gamePlayManager);
        this.shotPixel = "OOO\n" +
                         "OOO";
        this.position.x = xStart;
        this.position.y = yStart;
        this.speedInPixel = 7;
        this.height = 2;
        this.width = 3;
        this.rotation = 0;
        this.size = 4;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.movingMode = MovingMode.STAY;
    }

    @Override
    public void updatePosition() {
        //if(this.movingMode == MovingMode.STAY) {
            //do nothing
        //}
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
            ShotFractionEnemy shotFractionEnemy1 = new ShotFractionEnemy(gameView, gamePlayManager, position.x, position.y, ShotFractionEnemy.MovingMode.UP);
            ShotFractionEnemy shotFractionEnemy2 = new ShotFractionEnemy(gameView, gamePlayManager, position.x, position.y, ShotFractionEnemy.MovingMode.DOWN);
            gamePlayManager.spawn(shotFractionEnemy1);
            gamePlayManager.spawn(shotFractionEnemy2);
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