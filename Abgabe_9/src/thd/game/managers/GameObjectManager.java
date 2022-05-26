package thd.game.managers;

import thd.game.utilities.TooManyGameObjectsException;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;
import thd.gameview.GameView;

import java.util.ArrayList;
import java.util.LinkedList;



class GameObjectManager {
    private final GameView gameView;
    private final GamePlayManager gamePlayManager;
    private final LinkedList<GameObject> gameObjects;
    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    HelicopterPlayer helicopterPlayer;

    Background background;
    CarFriend leadingCar1;
    CarFriend car12;
    CarFriend car13;
    //JetEnemy jetEnemy1;
    //HelicopterEnemy helicopterEnemy1_1;
    //HelicopterEnemy helicopterEnemy1_2;


    CarFriend leadingCar2;
    CarFriend car22;
    CarFriend car23;
    //JetEnemy jetEnemy2;
    //HelicopterEnemy helicopterEnemy2_1;
    //HelicopterEnemy helicopterEnemy2_2;

    CarFriend leadingCar3;
    CarFriend car32;
    CarFriend car33;
    //JetEnemy jetEnemy3;
    //HelicopterEnemy helicopterEnemy3_1;
    //HelicopterEnemy helicopterEnemy3_2;

    CarFriend leadingCar4;
    CarFriend car42;
    CarFriend car43;
    //JetEnemy jetEnemy4;
    //HelicopterEnemy helicopterEnemy4_1;
    //HelicopterEnemy helicopterEnemy4_2;

    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameObjects = new LinkedList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;

        Score score = new Score(gameView, gamePlayManager, 0);
        this.helicopterPlayer = new HelicopterPlayer(gameView, gamePlayManager, score);
        this.background = new Background(gameView, gamePlayManager, helicopterPlayer);
        this.gameObjects.add(background);
        this.gameObjects.add(score);

        //first group
        this.leadingCar1 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, GameView.WIDTH + 300);
        this.gameObjects.add(leadingCar1);
        this.car12 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar1.getposition().x + 5 * 11 * 3.5);
        this.gameObjects.add(car12);
        this.car13 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar1.getposition().x + 10 * 11 * 3.5);
        this.gameObjects.add(car13);;

        //second group
        this.leadingCar2 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, GameView.WIDTH * 3 + 300);
        this.gameObjects.add(leadingCar2);
        this.car22 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar2.getposition().x + 5 * 11 * 3.5);
        this.gameObjects.add(car22);
        this.car23 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar2.getposition().x + 300 + 10 * 11 * 3.5);
        this.gameObjects.add(car23);

        //third group
        this.leadingCar3 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, -GameView.WIDTH * 3 + 300);
        this.gameObjects.add(leadingCar3);
        this.car32 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar3.getposition().x + 5 * 11 * 3.5);
        this.gameObjects.add(car32);
        this.car33 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar3.getposition().x + 300 + 10 * 11 * 3.5);
        this.gameObjects.add(car33);

        //fourth group
        this.leadingCar4 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, -GameView.WIDTH + 300);
        this.gameObjects.add(leadingCar4);
        this.car42 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar4.getposition().x + 5 * 11 * 3.5);
        this.gameObjects.add(car42);
        this.car43 = new CarFriend(gameView,gamePlayManager, helicopterPlayer, leadingCar4.getposition().x + 10 * 11 * 3.5);
        this.gameObjects.add(car43);

        this.gameObjects.add(this.helicopterPlayer);
    }

    void updateGameObjects() {
        modifyGameObjectsList();
        ArrayList<CollidableGameObject> collidables = new ArrayList<>(gameObjects.size());
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            if (gameObject instanceof AutoMovable) {
                ((AutoMovable) gameObject).updatePosition();
            }
            gameObject.addToCanvas();
            if (gameObject instanceof CollidableGameObject) {
                collidables.add((CollidableGameObject) gameObject);
            }
        }
        detectCollisionsAndNotifyGameObjects(collidables);
    }

    void addGameObject(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    void removeGameObject(GameObject gameObject) {
        toRemove.add(gameObject);
    }

    private void modifyGameObjectsList() {
        gameObjects.addAll(gameObjects.size() / 2, toAdd);
        gameObjects.removeAll(toRemove);
        toAdd.clear();
        toRemove.clear();
        if (gameObjects.size() > 300) {
            throw new TooManyGameObjectsException("The maximal number of GameObjects in game: " + 300 + " is exceeded");
        }
    }

    private void detectCollisionsAndNotifyGameObjects(ArrayList<CollidableGameObject> collidables) {
        for (int index = 0; index < collidables.size(); index++) {
            for (int other = index + 1; other < collidables.size(); other++) {
                if (collidables.get(index).collidesWith(collidables.get(other))) {
                    collidables.get(index).reactToCollision(collidables.get(other));
                    collidables.get(other).reactToCollision(collidables.get(index));
                }
            }
        }
    }

    void moveWorld(double shiftX, double shiftY) {
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject instanceof  HelicopterPlayer)) {
                gameObject.worldHasMoved(shiftX, shiftY);
            }
        }
    }

    boolean areAllEnemiesDestroyed() {
        for (GameObject gameObject: gameObjects) {
            if(gameObject.getClass() == JetEnemy.class || gameObject.getClass() == HelicopterEnemy.class) {
                return false;
            }
        }
        return true;
    }

    void removeAllEnemies() {
        for (GameObject gameObject: gameObjects) {
            if(gameObject.getClass() == JetEnemy.class || gameObject.getClass() == HelicopterEnemy.class) {
                removeGameObject(gameObject);
            }
        }
    }
}