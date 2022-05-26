package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

/**
 * represents an enemy jet.
 */
public class JetEnemy extends CollidableGameObject implements AutoMovable {
    private boolean flyFromLeftToRight;
    private String jetPixel;
    /**
     * Constructor of the {@link JetEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public JetEnemy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.position.x = 100;
        this.position.y = 100;
        this.speedInPixel = 1;
        this.height = 6;
        this.width = 12;
        this.size = 3.5;
        this.rotation = 0;
        this.flyFromLeftToRight = true;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.jetPixel = "          BB\n" +
                        "         BBB\n" +
                        " BBB   BBBB \n" +
                        "BBBBBBBBBBB \n" +
                        "BBB      BBB\n" +
                        "  BBBBBBBB  \n";
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition() {
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
            position.y = position.y + Math.round(Math.sin(Math.toRadians(position.x * 2)));
            flyFromLeftToRight = !(position.x + speedInPixel >= GameView.WIDTH);
        } else {
            position.left(speedInPixel);
            position.y = position.y - Math.round(Math.sin(Math.toRadians(position.x * 2)));
            flyFromLeftToRight = (position.x - speedInPixel) <= 0;
        }
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(jetPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {
        if (!gameView.alarmIsSet("allowShooting", this)){
            gameView.setAlarm("allowShooting", this, 2000);
        } else if (gameView.alarm("allowShooting", this)) {
            gamePlayManager.spawn(new ShotEnemy(gameView, gamePlayManager, position.x, position.y));
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == ShotPlayer.class) {
            gamePlayManager.destroy(this);
        }
    }
}
