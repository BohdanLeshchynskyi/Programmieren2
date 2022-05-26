package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.HelicopterPlayer;
import thd.gameview.GameView;

import java.util.ArrayList;
import static java.util.Collections.swap;

/**
 * game`s background.
 */
public class Background extends GameObject {
    private final Position positionSecondBackground;
    private final Position positionZeroBackground;
    private final ArrayList<Position> backgrounds;
    private HelicopterPlayer helicopterPlayer;
    /**
     * Constructor of {@link Background}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    public Background(GameView gameView, GamePlayManager gamePlayManager, HelicopterPlayer helicopterPlayer) {
        super(gameView, gamePlayManager);
        this.positionSecondBackground = new Position(position.x + GameView.WIDTH, position.y);
        this.positionZeroBackground = new Position(position.x - GameView.WIDTH , position.y);
        this.helicopterPlayer = helicopterPlayer;
        this.backgrounds = new ArrayList<>(3);
        this.backgrounds.add(this.positionZeroBackground);
        this.backgrounds.add(this.position);
        this.backgrounds.add(this.positionSecondBackground);
    }

    /**
     * adds object to the {@link GameView} canvas for displaying.
     */
    @Override
    public void addToCanvas() {
        for (Position background : backgrounds) {
            gameView.addImageToCanvas("background.png", background.x, background.y, 1, 0);
        }
    }

    /**
     * updates the current state of the game object.
     */
    @Override
    public void updateStatus() {
        if (backgrounds.get(1).x > helicopterPlayer.getPosition().x) {
            backgrounds.get(2).x -= GameView.WIDTH * 3;
            swap(backgrounds, 0, 1);
            swap(backgrounds,0, 2);
        } else if (backgrounds.get(1).x + GameView.WIDTH < helicopterPlayer.getPosition().x) {
            backgrounds.get(0).x += GameView.WIDTH * 3;
            swap(backgrounds, 1, 2);
            swap(backgrounds,0, 2);
        }
    }

    @Override
    public void worldHasMoved(double shiftX, double shiftY) {
        super.worldHasMoved(shiftX, shiftY);
        positionSecondBackground.x -= shiftX;
        positionSecondBackground.y -= shiftY;
        positionZeroBackground.x -= shiftX;
        positionZeroBackground.y -= shiftY;
    }
}