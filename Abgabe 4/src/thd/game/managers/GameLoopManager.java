package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.HelicopterEnemy;
import thd.gameview.GameView;
import java.util.LinkedList;

/**
 * Manages the game course of the game.
 */
public class GameLoopManager {
    private final GameView gameView;
    private GameObjectManager gameObjectManager;
    private InputManager inputManager;
    private LinkedList<GameObject> createdGameObjects;

    /**
     * Constructor of {@link GameLoopManager}.
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setStatusText("Bohdan Leshchynskyi - Java Programmierung SS 2022");
        this.gameView.setWindowTitle("Chopper Command");
        this.gameView.setWindowIcon("ChopperCommandIcon.png");
        this.gameObjectManager = new GameObjectManager(gameView);
        this.inputManager = new InputManager(gameView, gameObjectManager.helicopterPlayer);
        this.createdGameObjects = new LinkedList<>();
    }

    /**
     * Starts the game course.
     */
    public void startGame() {
        while(true) { // Der "Game-Loop"
            updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas(); // Es werden maximal 120 Bilder pro Sekunde angezeigt.
        }
    }

    private void updateGamePlay() {
        HelicopterEnemy objectToAdd;
        if ((gameView.getGameTimeInMilliseconds() / 1000 == 5)) {
            objectToAdd = new HelicopterEnemy(gameView);
            createdGameObjects.add(objectToAdd);
            gameObjectManager.addGameObject(objectToAdd);
        }

        if ((gameView.getGameTimeInMilliseconds() / 1000 == 7)) {
            for (GameObject gameObject: createdGameObjects){
                gameObjectManager.removeGameObject(gameObject);
            }
        }
    }
}
