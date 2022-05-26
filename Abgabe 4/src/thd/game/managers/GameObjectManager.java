package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameview.GameView;

import java.util.ArrayList;
import java.util.LinkedList;

class GameObjectManager {
    private final GameView gameView;
    private final LinkedList<GameObject> gameObjects;
    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    HelicopterPlayer helicopterPlayer;

    GameObjectManager(GameView gameView) {
        this.gameObjects = new LinkedList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.helicopterPlayer = new HelicopterPlayer(gameView);
        this.gameView = gameView;
        this.gameObjects.add(new JetEnemy(gameView));
        this.gameObjects.add(new HelicopterEnemy(gameView));
        this.gameObjects.add(new CarFriend(gameView));
        this.gameObjects.add(this.helicopterPlayer);
    }

    void updateGameObjects() {
        modifyGameObjectsList();
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
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
    }
}
