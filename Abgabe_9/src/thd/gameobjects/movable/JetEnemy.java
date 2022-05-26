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
    private long shootingRateMs;
    private String jetPixel;

    private final HelicopterPlayer player;

    private final CarFriend carToAttack;

    private enum Status {
        STANDART_LEFT("          BB\n" +
                                      "         BBB\n" +
                                      " BBB   BBBB \n" +
                                      "BBBBBBBBBBB \n" +
                                      "BBB      BBB\n" +
                                      "  BBBBBBBB  \n"),
        STANDART_RIGHT("BB          \n" +
                                       "BBB         \n" +
                                       " BBBB   BBB \n" +
                                       " BBBBBBBBBBB\n" +
                                       "BBB      BBB\n" +
                                       "  BBBBBBBB  \n"),
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
     * Constructor of the {@link JetEnemy}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param player reference on te player for tracking
     * @param carToAttack reference of a eading car in the group of cars for orientation
     * @param startX start x coordinate
     * @param startY start y coordinate
     * @param speedInPixel parameter that sets speed in pixels
     */
    public JetEnemy(GameView gameView, GamePlayManager gamePlayManager, HelicopterPlayer player, CarFriend carToAttack, double startX, double startY, double speedInPixel) {
        super(gameView, gamePlayManager);
        this.player = player;
        this.carToAttack = carToAttack;
        this.shootingRateMs = 2000;
        this.position.x = startX;
        this.position.y = startY;
        this.speedInPixel = speedInPixel;
        this.height = 6;
        this.width = 12;
        this.size = 3.5;
        this.rotation = 0;
        this.flyFromLeftToRight = true;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.status = Status.STANDART_RIGHT;
        this.jetPixel = status.pixelRepresentation;
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition() {
        if (flyFromLeftToRight && this.position.x - carToAttack.getposition().x <= 10 * 11 * 3.5 + 100){
            status = Status.STANDART_RIGHT;
            position.right(speedInPixel);
            if (position.y + Math.round(Math.sin(Math.toRadians(position.x * 2))) < 441.5) {
                position.y = position.y + Math.round(Math.sin(Math.toRadians(position.x * 2)));
            }
        } else {
            flyFromLeftToRight = carToAttack.getposition().x - this.position.x >= 100;
            status = Status.STANDART_LEFT;
            position.left(speedInPixel);
            if (position.y - Math.round(Math.sin(Math.toRadians(position.x * 2))) > 100) {
                position.y = position.y - Math.round(Math.sin(Math.toRadians(position.x * 2)));
            }
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
        jetPixel = status.pixelRepresentation;

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
