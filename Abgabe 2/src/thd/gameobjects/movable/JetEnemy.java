package thd.gameobjects.movable;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * represents an enemy jet.
 */
public class JetEnemy {
    private final Position position;
    private double speedInPixel;
    private final GameView gameView;
    private double size;
    private double rotation;

    private final String jetGrafic;

    /**
     * Constructor of the {@link JetEnemy}.
     * @param gameView shared reference of a {@link GameView} object for displaing the game
     */
    public JetEnemy(GameView gameView) {
        this.position = new Position(0,GameView.HEIGHT / 2);
        this.speedInPixel = 1;
        this.gameView = gameView;
        this.size = 0.1;
        this.rotation = 0;
        jetGrafic = "BW\n" +
                    "WB";
    }

    @Override
    public String toString() {
        return "Jet: " + position;
    }

    /**
     * updates current position of the object.
     */
    public void updatePosition() {
        position.right(speedInPixel);
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("blueEnemyJet.png", position.getX(), position.getY(), size, rotation);
    }

}
