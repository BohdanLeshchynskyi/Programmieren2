package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

/**
 * represents an enemy helicopter.
 */
public class HelicopterEnemy extends CollidableGameObject implements AutoMovable {
    private boolean flyFromLeftToRight;
    private long shootingRateMs;
    private String helicopterPixel;
    private final HelicopterPlayer player;
    private final CarFriend carToAttack;

    private enum Status {
        STANDART_LEFT("WWWWW       \n" +
                                      "   WWWWW    \n" +
                                      "   WW       \n" +
                                      "  WWWW   WWW\n" +
                                      "WWWWWWWWWWW \n" +
                                      "WWWWWWWW    \n" +
                                      "  WWWW      \n" +
                                      "   WW       \n" +
                                      "  WWWW      \n"),
        STANDART_RIGHT("       WWWWW\n" +
                                       "    WWWWW   \n" +
                                       "       WW   \n" +
                                       "WWW   WWWW  \n" +
                                       " WWWWWWWWWWW\n" +
                                       "    WWWWWWWW\n" +
                                       "      WWWW  \n" +
                                       "       WW   \n" +
                                       "      WWWW  \n"),
        DESTROYED("   o   \n" +
                                  "    o  \n" +
                                  "  o   o\n" +
                                  "   o   \n" +
                                  "o      \n" +
                                  "  o   o\n" +
                                  "     o \n");
        private String pixelRepresentation;
        Status(String pixelRepresentation) {
            this.pixelRepresentation = pixelRepresentation;
        }
    }
    private Status status;
    /**
     * Constructor of the {@link HelicopterEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param player reference on te player for tracking
     * @param carToAttack reference of a eading car in the group of cars for orientation
     * @param startX start x coordinate
     * @param startY start y coordinate
     * @param speedInPixel parameter that sets speed in pixels
     */
    public HelicopterEnemy(GameView gameView, GamePlayManager gamePlayManager, HelicopterPlayer player, CarFriend carToAttack, double startX, double startY, double speedInPixel) {
        super(gameView, gamePlayManager);
        this.player = player;
        this.carToAttack = carToAttack;
        this.shootingRateMs = 2000;
        this.speedInPixel = speedInPixel;
        this.height = 9;
        this.width = 12;
        this.rotation = 0;
        this.size = 3;
        this.position.x = startX;
        this.position.y = startY;
        this.flyFromLeftToRight = true;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.status = Status.STANDART_RIGHT;
        this.helicopterPixel = status.pixelRepresentation;
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition() {
        if (flyFromLeftToRight && this.position.x - carToAttack.getposition().x <= 10 * 11 * 3.5 + 100){
            status = Status.STANDART_RIGHT;
            position.right(speedInPixel);
        } else {
            flyFromLeftToRight = carToAttack.getposition().x - this.position.x >= 100;
            status = Status.STANDART_LEFT;
            position.left(speedInPixel);
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
    public void updateStatus() {
        helicopterPixel = status.pixelRepresentation;

        if (!gameView.alarmIsSet("allowShooting", this)){
            gameView.setAlarm("allowShooting", this, 2000);
        } else if (gameView.alarm("allowShooting", this)) {
            gamePlayManager.spawn(new ShotEnemy(gameView, gamePlayManager, position.x, position.y));
        }

        if (Math.abs(carToAttack.getposition().x - this.position.x) >= GameView.WIDTH * 3) {
            if (carToAttack.getposition().x - this.position.x > 0) {
                this.position.x += GameView.WIDTH * 9;
            } else {
                this.position.x -= GameView.WIDTH * 9;
            }
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == ShotPlayer.class || other.getClass() == HelicopterPlayer.class) {
            gamePlayManager.destroy(this);
        }
    }
}