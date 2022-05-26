package thd.game.managers;

import thd.game.utilities.TooManyGameObjectsException;
import thd.gameobjects.base.AutoMovable;
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

    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameObjects = new LinkedList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.helicopterPlayer = new HelicopterPlayer(gameView, gamePlayManager);
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        this.gameObjects.add(new JetEnemy(gameView, gamePlayManager));
        this.gameObjects.add(new HelicopterEnemy(gameView, gamePlayManager));
        this.gameObjects.add(new CarFriend(gameView,gamePlayManager));
        this.gameObjects.add(this.helicopterPlayer);
    }

    void updateGameObjects() {
        modifyGameObjectsList();
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            if (gameObject instanceof AutoMovable) {
                ((AutoMovable) gameObject).updatePosition();
            }
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
        if (gameObjects.size() > 300) {
            throw new TooManyGameObjectsException("The maximal number of GameObjects in game: " + 300 + " is exceeded");
        }
    }
}
