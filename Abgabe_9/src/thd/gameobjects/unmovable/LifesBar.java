package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * Represents players lifes.
 */
class LifesBar extends GameObject {

    /**
     * Constructor of the {@link LifesBar}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     */
    LifesBar(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void addToCanvas() {

    }

    @Override
    public void updateStatus() {

    }
}
