package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Score;
import thd.gameview.GameView;

/**
 * Represents the main game object - helicopter contolled by the player.
 */
public class HelicopterPlayer extends CollidableGameObject implements AutoMovable {
    private enum Status {
        STANDART_RIGHT("         OOOOOOOOOO       \n" +
                                       "                OOOOOOOOOO\n" +
                                       "                OOO       \n" +
                                       "OOOOOO        OOOOOOOO    \n" +
                                       "   OOOOOOOOOOOOOOOOOOOOOOO\n" +
                                       "          OOOOOOOOOOOOOOOO\n" +
                                       "              OOOOOOOO    \n" +
                                       "                 OO       \n" +
                                       "              OOOOOOOO    \n"),

        STANDART_LEFT("       OOOOOOOOOO         \n" +
                                      "OOOOOOOOOO                \n" +
                                      "       OOO                \n" +
                                      "     OOOOOO        OOOOOOOO\n" +
                                      "OOOOOOOOOOOOOOOOOOOOOOO   \n" +
                                      "OOOOOOOOOOOOOOOO          \n" +
                                      "    OOOOOOOO              \n" +
                                      "       OO                 \n" +
                                      "    OOOOOOOO              \n"),
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
    private String heliGrafics;
    private int lifeCounter;
    private double shotsPerSecond;
    private Status status;
    private Score score;

    /**
     * Constructor of {@link HelicopterPlayer}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public HelicopterPlayer(GameView gameView, GamePlayManager gamePlayManager, Score score) {
        super(gameView, gamePlayManager);
        this.position.x = GameView.WIDTH / 2.0;
        this.position.y = GameView.HEIGHT / 2.0;
        this.height = 9;
        this.width = 26;
        this.size = 3;
        this.rotation = 0;
        this.speedInPixel = 5;
        this.lifeCounter = 2;
        this.shotsPerSecond = 3;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.status = Status.STANDART_RIGHT;
        this.heliGrafics = status.pixelRepresentation;
        this.score = score;
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas(){
        gameView.addBlockImageToCanvas(heliGrafics, position.x, position.y, size, rotation);
    }

    /**
     * updates current position of the object.
     */
    @Override
    public void updatePosition(){

    }

    /**
     * updates the current state of the game object.
     */
    @Override
    public void updateStatus(){
        switch (status) {
            case STANDART_RIGHT: heliGrafics = status.pixelRepresentation; break;

            case STANDART_LEFT: heliGrafics = status.pixelRepresentation; break;

            case DESTROYED: heliGrafics = status.pixelRepresentation; break;

            default: break;
        }
    }

    /**
     * moves the helicopter to the right.
     */
    public void right() {
        //this.position.right(speedInPixel);
        status = Status.STANDART_RIGHT;
        if (position.x < 280) {
            position.right(speedInPixel);
        } else {
            gamePlayManager.moveWorldToLeft(speedInPixel);
        }
    }

    /**
     * moves the helicopter to the left.
     */
    public void left() {
        status = Status.STANDART_LEFT;
        if (position.x > 50) {
            position.left(speedInPixel);
        } else {
            gamePlayManager.moveWorldToRight(speedInPixel);
        }
    }

    /**
     * moves the helicopter upwards.
     */
    public void up() {
        this.position.up(speedInPixel);
    }

    /**
     * moves the helicopter downwards.
     */
    public void down() {
        this.position.down(speedInPixel);
    }

    /**
     * allows shooting.
     */
    public void shoot() {
        if (!gameView.timerIsActive("shootingAllowed", this)) {
            gameView.activateTimer("shootingAllowed", this, (long) (1000 / shotsPerSecond));
            ShotPlayer shotPlayer;
            if (status == Status.STANDART_RIGHT) {
                shotPlayer = new ShotPlayer(gameView, gamePlayManager, position.x + width * size, position.y + (height * size ) / 2, true, score);
            } else {
                shotPlayer = new ShotPlayer(gameView, gamePlayManager, position.x - width * size, position.y + (height * size ) / 2, false, score);
            }
            this.gamePlayManager.spawn(shotPlayer);
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == HelicopterEnemy.class || other.getClass() == JetEnemy.class
                || other.getClass() == ShotEnemy.class || other.getClass() == ShotFractionEnemy.class) {
            status = Status.DESTROYED;
            heliGrafics = status.pixelRepresentation;
            gamePlayManager.destroy(this);
        }
    }

    public Position getPosition() {
        return this.position;
    }
}