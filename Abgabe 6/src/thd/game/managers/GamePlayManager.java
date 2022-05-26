package thd.game.managers;


import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.HelicopterEnemy;
import thd.gameobjects.movable.JetEnemy;
import thd.gameview.GameView;

import java.util.LinkedList;
import java.util.Random;

/**
 * Implements the logic of game play managment (spawning and destroying game objects).
 */
public class GamePlayManager {
    private GameView gameView;
    private GameObjectManager gameObjectManager;

    /**
     * Constuctor of {@link GamePlayManager}.
     * @param gameView shared GameView reference
     */
    public GamePlayManager(GameView gameView){
        this.gameView = gameView;
    }
    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    void updateGamePlay(){
        spawnAndDestroyEnemyJets();
        spawnAndDestroyEnemyHelis();
    }

    /**
     * Spawns a given game object in the game.
     * @param gameObject game object to be spawned
     */
    public void spawn(GameObject gameObject){
        gameObjectManager.addGameObject(gameObject);
    }

    /**
     * Destroys a given game object in the game.
     * @param gameObject game object to be destroyed
     */
    public void destroy(GameObject gameObject){
        gameObjectManager.removeGameObject(gameObject);
    }

    private void spawnAndDestroyEnemyJets() {
        LinkedList<JetEnemy> spawnedEnemyJets = new LinkedList<>();
        Random random = new Random();
        if (!gameView.timerIsActive("spawn", this)) {
            gameView.activateTimer("spawn", this, 5000);
            JetEnemy spawnedEnemyJet = new JetEnemy(gameView, this);
            spawnedEnemyJets.add(spawnedEnemyJet);
            spawn(spawnedEnemyJet);
        }
        if (!gameView.timerIsActive("destroy", this)) {
            gameView.activateTimer("destroy", this, 6000);
            if (spawnedEnemyJets.size() != 0) {
                int randIndexToDestroy = random.nextInt(spawnedEnemyJets.size());
                destroy(spawnedEnemyJets.get(randIndexToDestroy));
                spawnedEnemyJets.remove(randIndexToDestroy);
            }
        }
    }

    private void spawnAndDestroyEnemyHelis() {
        LinkedList<HelicopterEnemy> spawnedEnemyHelis = new LinkedList<>();
        Random random = new Random();
        if (!gameView.timerIsActive("spawnHelis", this)) {
            gameView.activateTimer("spawnHelis", this, 1000);
            HelicopterEnemy spawnedEnemyHeli = new HelicopterEnemy(gameView, this);
            spawnedEnemyHelis.add(spawnedEnemyHeli);
            spawn(spawnedEnemyHeli);
        }
        if (!gameView.timerIsActive("destroyHelis", this)) {
            gameView.activateTimer("destroyHelis", this, 1500);
            if (spawnedEnemyHelis.size() > 0) {
                int randIndexToDestroy = random.nextInt(spawnedEnemyHelis.size());
                destroy(spawnedEnemyHelis.get(randIndexToDestroy));
                spawnedEnemyHelis.remove(randIndexToDestroy);
            }
        }
    }
}
