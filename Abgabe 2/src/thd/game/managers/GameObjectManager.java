package thd.game.managers;

import thd.gameobjects.movable.HelicopterEnemy;
import thd.gameobjects.movable.JetEnemy;
import thd.gameview.GameView;

class GameObjectManager {
    private final GameView gameView;
    private JetEnemy jetEnemy;
    private HelicopterEnemy helicopterEnemy;

    GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.jetEnemy = new JetEnemy(gameView);
        this.helicopterEnemy = new HelicopterEnemy(gameView);
    }

    void updateGameObjects() {
        jetEnemy.updatePosition();
        jetEnemy.addToCanvas();
        helicopterEnemy.updatePosition();
        helicopterEnemy.addToCanvas();
    }
}
