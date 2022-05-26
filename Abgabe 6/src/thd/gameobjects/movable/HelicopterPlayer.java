package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Represents the main game object - helicopter contolled by the player.
 */
public class HelicopterPlayer extends CollidableGameObject implements AutoMovable {
    private enum Status {
        STANDART, DESTROYED
    }
    private boolean shooting;
    private String[] heliGrafics;
    private String heliCurrentRepresentation;
    private int lifes;
    private double shotsPerSecond;
    private Status status;
    //TODO remove om the next assignment
    private CarFriend carFriend;

    /**
     * Constructor of {@link HelicopterPlayer}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param carFriend a temporary parameter to be removed in the next assignment
     */
    public HelicopterPlayer(GameView gameView, GamePlayManager gamePlayManager, CarFriend carFriend) {
        super(gameView, gamePlayManager);
        this.carFriend = carFriend;
        this.position.x = GameView.WIDTH / 2.0;
        this.position.y = GameView.HEIGHT / 2.0;
        this.height = 9;
        this.width = 26;
        this.size = 3;
        this.rotation = 0;
        this.speedInPixel = 5;
        this.shooting = false;
        this.lifes = 3;
        this.shotsPerSecond = 3;
        this.hitBoxOffsetX = 0;
        this.hitBoxOffsetY = 0;
        this.hitBoxHeight = height * size;
        this.hitBoxWidth = width * size;
        this.status = Status.STANDART;
        this.heliGrafics = new String[2];
        this.heliGrafics[0] = "         OOOOOOOOOO       \n" +
                              "                OOOOOOOOOO\n" +
                              "                OOO       \n" +
                              "OOOOOO        OOOOOOOO    \n" +
                              "   OOOOOOOOOOOOOOOOOOOOOOO\n" +
                              "          OOOOOOOOOOOOOOOO\n" +
                              "              OOOOOOOO    \n" +
                              "                 OO       \n" +
                              "              OOOOOOOO    \n";
        this.heliGrafics[1] = "   o   \n" +
                              "    o  \n" +
                              "  o   o\n" +
                              "   o   \n" +
                              "o      \n" +
                              "  o   o\n" +
                              "     o \n";
        this.heliCurrentRepresentation = heliGrafics[0];
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas(){
        gameView.addBlockImageToCanvas(heliCurrentRepresentation, position.x, position.y, size, rotation);
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
            case STANDART: heliCurrentRepresentation = heliGrafics[0]; break;

            case DESTROYED: heliCurrentRepresentation = heliGrafics[1]; break;

            default: break;
        }
    }

    /**
     * moves the helicopter to the right.
     */
    public void right() {
        this.position.right(speedInPixel);
    }

    /**
     * moves the helicopter to the left.
     */
    public void left() {
        this.position.left(speedInPixel);
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
        if (collidesWith(carFriend)) {
            this.position.up(speedInPixel);
        }
    }

    /**
     * allows shooting.
     */
    public void shoot() {
        if (!gameView.timerIsActive("shootingAllowed", this)) {
            gameView.activateTimer("shootingAllowed", this, (long) (1000 / shotsPerSecond));
            ShotPlayer shotPlayer = new ShotPlayer(gameView, gamePlayManager, position.x + width * size, position.y + (height * size ) / 2);
            this.gamePlayManager.spawn(shotPlayer);
            this.shooting = true;
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == HelicopterEnemy.class || other.getClass() == JetEnemy.class) {
            status = Status.DESTROYED;
            gamePlayManager.destroy(this);
        }
    }
}
