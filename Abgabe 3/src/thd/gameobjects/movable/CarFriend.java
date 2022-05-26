package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * class representing a friendly car that must protected against air attacks.
 */
public class CarFriend extends GameObject {
    private boolean flyFromLeftToRight;
    private String carPixel;

    /**
     * CarFriend constructor.
     * @param gameView GameView reference
     */
    public CarFriend(GameView gameView){
        super(gameView);
        this.speedInPixel = 1;
        this.height = 7;
        this.width = 9;
        this.rotation = 0;
        this.size = 4;
        this.position.x = 0;
        this.position.y = GameView.HEIGHT - height * size;
        this.flyFromLeftToRight = true;
        this.carPixel = "    OOOOO\n" +
                               "   OOOOOO\n" +
                               "OOO OOOOO\n" +
                               "OOOOOOOOO\n" +
                               " O OOO O \n" +
                               "O OOOOO O\n" +
                               " O     O \n";
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
}
