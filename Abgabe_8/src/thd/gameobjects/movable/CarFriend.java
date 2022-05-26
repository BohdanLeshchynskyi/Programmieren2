package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * class representing a friendly car that must protected against air attacks.
 */
public class CarFriend extends CollidableGameObject implements AutoMovable {
    private String carPixel;
    private HelicopterPlayer player;

    private enum Status {
        STANDART("    OOOOOOO\n" +
                                 "   OOOOOOOO\n" +
                                 "OOO OOOOOOO\n" +
                                 "OOOOOOOOOOO\n" +
                                 " O OOOOO O \n" +
                                 "O OOOOOOO O\n" +
                                 " O       O \n"),
        DESTORYED(" ");

        private String pixelRepresentation;
        Status(String pixelRepresentation) {
            this.pixelRepresentation = pixelRepresentation;
        }
    }

    private Status status;

    /**
     * CarFriend constructor.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param player reference on the player for tracking
     * @param startX start x coordinate
     */
    public CarFriend(GameView gameView, GamePlayManager gamePlayManager, HelicopterPlayer player, double startX){
        super(gameView, gamePlayManager);
        this.player = player;
        this.speedInPixel = 1;
        this.height = 7;
        this.width = 11;
        this.rotation = 0;
        this.size = 3.5;
        this.position.x = startX;
        this.position.y = 417;
        this.hitBoxOffsetX = width * size * 0.05;
        this.hitBoxOffsetY = 1.5;
        this.hitBoxHeight = height * size ;
        this.hitBoxWidth = width * size;
        this.status = Status.STANDART;
        this.carPixel = status.pixelRepresentation;
    }


    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(carPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {
        carPixel = status.pixelRepresentation;
        if (Math.abs(player.getposition().x - this.position.x) >= GameView.WIDTH * 3) {
            if (this.position.x < 0) {
                this.position.x += GameView.WIDTH * 9;
            } else {
                this.position.x -= GameView.WIDTH * 9;
            }
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == ShotEnemy.class || other.getClass() == ShotFractionEnemy.class) {
            status = Status.DESTORYED;
        }
    }

    /**
     * getter method fpr car`s position.
     * @return car`s position
     */
    public Position getposition() {
        return this.position;
    }

    /**
     * setter method fpr car`s speed in pixels.
     * @param speedInPixel speed in pixels to be set
     */
    public void setspeedinpixel(double speedInPixel) {
        this.speedInPixel = speedInPixel;
    }
}
