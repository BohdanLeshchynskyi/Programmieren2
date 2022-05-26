package thd.game.managers;

import thd.game.utilities.TooManyGameObjectsException;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
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
    //TODO delete in the next assignment
    private CarFriend carFriend;

    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameObjects = new LinkedList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.carFriend = new CarFriend(gameView, gamePlayManager);
        this.helicopterPlayer = new HelicopterPlayer(gameView, gamePlayManager, carFriend);
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        this.gameObjects.add(new JetEnemy(gameView, gamePlayManager));
        this.gameObjects.add(new HelicopterEnemy(gameView, gamePlayManager));
        //TODO change in teh next assignment
        //this.gameObjects.add(new CarFriend(gameView,gamePlayManager));
        this.gameObjects.add(this.carFriend);
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
            detectCollisionsAndNotifyGameObjects(collidables);
        }
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
}
