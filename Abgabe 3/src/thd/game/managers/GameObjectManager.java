package thd.game.managers;

import thd.gameobjects.movable.*;
import thd.gameview.GameView;

class GameObjectManager {
    private final GameView gameView;
    private JetEnemy jetEnemy;
    private HelicopterEnemy helicopterEnemy;
    private RandomBall randomBall;
    private FollowerBall followerBall;
    private CarFriend carFriend;

    GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.jetEnemy = new JetEnemy(gameView);
        this.helicopterEnemy = new HelicopterEnemy(gameView);
        this.randomBall = new RandomBall(gameView);
        this.followerBall = new FollowerBall(gameView, randomBall);
        this.carFriend = new CarFriend(gameView);
    }

    void updateGameObjects() {
        jetEnemy.updatePosition();
        jetEnemy.addToCanvas();
        helicopterEnemy.updatePosition();
        helicopterEnemy.addToCanvas();
        randomBall.updatePosition();
        randomBall.addToCanvas();
        followerBall.updatePosition();
        followerBall.addToCanvas();
        carFriend.updatePosition();
        carFriend.addToCanvas();
    }
}
