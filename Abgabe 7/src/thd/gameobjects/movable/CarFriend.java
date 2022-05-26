package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

/**
 * class representing a friendly car that must protected against air attacks.
 */
public class CarFriend extends CollidableGameObject implements AutoMovable {
    private boolean flyFromLeftToRight;
    private String carPixel;

    /**
     * CarFriend constructor.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public CarFriend(GameView gameView, GamePlayManager gamePlayManager){
        super(gameView, gamePlayManager);
        this.speedInPixel = 1;
        this.height = 7;
        this.width = 11;
        this.rotation = 0;
        this.size = 3.5;
        this.position.x = 0;
        this.position.y = GameView.HEIGHT - height * size;
        this.flyFromLeftToRight = true;
        this.hitBoxOffsetX = width * size * 0.05;
        this.hitBoxOffsetY = 1.5;
        this.hitBoxHeight = height * size ;
        this.hitBoxWidth = width * size;
        this.carPixel = "    OOOOOOO\n" +
                        "   OOOOOOOO\n" +
                        "OOO OOOOOOO\n" +
                        "OOOOOOOOOOO\n" +
                        " O OOOOO O \n" +
                        "O OOOOOOO O\n" +
                        " O       O \n";
    }

    @Override
    public void updatePosition() {
        if (flyFromLeftToRight) {
            position.right(speedInPixel);
            flyFromLeftToRight = position.x + width * size + speedInPixel >= GameView.WIDTH ? false : true;
        } else {
            position.left(speedInPixel);
            flyFromLeftToRight = position.x - width * size - speedInPixel <= 0 ? true : false;
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(carPixel, position.x, position.y, size, rotation);
    }

    @Override
    public void updateStatus() {}

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }
}
