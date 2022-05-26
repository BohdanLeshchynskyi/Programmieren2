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
    private String helicopterPixel;
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
     */
    public HelicopterEnemy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.speedInPixel = 3;
        this.height = 9;
        this.width = 12;
        this.rotation = 0;
        this.size = 3;
        this.position.x = 0;
        this.position.y = 400;
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
        if (flyFromLeftToRight) {
            status = Status.STANDART_RIGHT;
            position.right(speedInPixel);
            flyFromLeftToRight = position.x + width * size + speedInPixel <= GameView.WIDTH ;
        } else {
            status = Status.STANDART_LEFT;
            position.left(speedInPixel);
            flyFromLeftToRight = position.x - width * size - speedInPixel <= 0;
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
        switch (status) {
            case STANDART_RIGHT: helicopterPixel = status.pixelRepresentation; break;

            case STANDART_LEFT: helicopterPixel = status.pixelRepresentation; break;

            case DESTROYED: helicopterPixel = status.pixelRepresentation; break;

            default: break;
        }
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == ShotPlayer.class) {
            gamePlayManager.destroy(this);
        }
    }
}
