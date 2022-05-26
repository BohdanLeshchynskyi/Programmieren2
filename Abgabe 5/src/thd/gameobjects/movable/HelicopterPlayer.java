package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Represents the main game object - helicopter contolled by the player.
 */
public class HelicopterPlayer extends GameObject implements AutoMovable {
    private boolean shooting;
    private String heliGrafic;
    private int lifes;
    private double shotsPerSecond;

    /**
     * Constructor of {@link HelicopterPlayer}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public HelicopterPlayer(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.position.x = GameView.WIDTH / 2;
        this.position.y = GameView.HEIGHT / 2;
        this.size = 50;
        this.rotation = 0;
        this.speedInPixel = 5;
        this.heliGrafic = "X";
        this.shooting = false;
        this.lifes = 3;
        this.shotsPerSecond = 3;
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas(){
        if (shooting){
            heliGrafic = "O";
            gameView.addTextToCanvas(heliGrafic, position.x, position.y, size, Color.YELLOW, rotation);
            shooting = false;
            heliGrafic = "X";
        } else {
            gameView.addTextToCanvas(heliGrafic, position.x, position.y, size, Color.YELLOW, rotation);
        }
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
        if (position.y >= GameView.HEIGHT - size) {
            heliGrafic = " ";
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
    }

    /**
     * allows shooting.
     */
    public void shoot() {
        if (!gameView.timerIsActive("shootingAllowed", this)) {
            gameView.activateTimer("shootingAllowed", this, (long) (1000 / shotsPerSecond));
            Shot shot = new Shot(gameView, gamePlayManager, position.x, position.y);
            this.gamePlayManager.spawn(shot);
            this.shooting = true;
        }
    }
}
