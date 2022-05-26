package thd.game.managers;


import thd.game.level.Level;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.HelicopterEnemy;
import thd.gameobjects.movable.JetEnemy;
import thd.gameview.GameView;

/**
 * Implements the logic of game play managment (spawning and destroying game objects).
 */
public class GamePlayManager {
    private final GameView gameView;
    private GameObjectManager gameObjectManager;
    private final LevelManager levelManager;
    private Level currentLevel;

    /**
     * Constuctor of {@link GamePlayManager}.
     * @param gameView shared GameView reference
     */
    public GamePlayManager(GameView gameView){
        this.gameView = gameView;
        this.levelManager = new LevelManager(Level.Difficulty.STANDARD);
        this.currentLevel = levelManager.nextLevel();
    }
    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
        initializeLevel();
    }

    void updateGamePlay() {
        if (currentLevel.levelNumber == 1) {
            if(!gameView.alarmIsSet("10sTimer1stLevel", this)) {
                gameView.setAlarm("10sTimer1stLevel", this, 10000);
            }
        }
        if(gameObjectManager.areAllEnemiesDestroyed() || gameView.alarm("10sTimer1stLevel", this)) {
            if(levelManager.hasNextLevel()){
                currentLevel = levelManager.nextLevel();
            } else {
                levelManager.resetLevelCounter();
                currentLevel = levelManager.nextLevel();
            }
            initializeLevel();
        }
    }

    private void initializeLevel() {
        gameObjectManager.removeAllEnemies();
        comeBack();

        gameObjectManager.leadingCar1.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car12.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car13.setspeedinpixel(currentLevel.speedCarFriend);
        spawn(new JetEnemy(gameView, this, gameObjectManager.helicopterPlayer,  gameObjectManager.leadingCar1, gameObjectManager.leadingCar1.getposition().x + 10 * 11 * 3.5, 150, currentLevel.speedJetEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar1, gameObjectManager.leadingCar1.getposition().x, 275, currentLevel.speedHelicopterEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar1, gameObjectManager.leadingCar1.getposition().x + 10 * 11 * 3.5, 275, currentLevel.speedHelicopterEnemy));

        gameObjectManager.leadingCar2.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car22.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car23.setspeedinpixel(currentLevel.speedCarFriend);
        spawn(new JetEnemy(gameView, this, gameObjectManager.helicopterPlayer,  gameObjectManager.leadingCar2, gameObjectManager.leadingCar2.getposition().x + 10 * 11 * 3.5, 150, currentLevel.speedJetEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar2, gameObjectManager.leadingCar2.getposition().x, 275, currentLevel.speedHelicopterEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar2, gameObjectManager.leadingCar2.getposition().x + 10 * 11 * 3.5, 275, currentLevel.speedHelicopterEnemy));

        gameObjectManager.leadingCar3.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car32.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car33.setspeedinpixel(currentLevel.speedCarFriend);
        spawn(new JetEnemy(gameView, this, gameObjectManager.helicopterPlayer,  gameObjectManager.leadingCar3, gameObjectManager.leadingCar3.getposition().x + 10 * 11 * 3.5, 150, currentLevel.speedJetEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar3, gameObjectManager.leadingCar3.getposition().x, 275, currentLevel.speedHelicopterEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar3, gameObjectManager.leadingCar3.getposition().x + 10 * 11 * 3.5, 275, currentLevel.speedHelicopterEnemy));

        gameObjectManager.leadingCar4.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car42.setspeedinpixel(currentLevel.speedCarFriend);
        gameObjectManager.car43.setspeedinpixel(currentLevel.speedCarFriend);
        spawn(new JetEnemy(gameView, this, gameObjectManager.helicopterPlayer,  gameObjectManager.leadingCar4, gameObjectManager.leadingCar4.getposition().x + 10 * 11 * 3.5, 150, currentLevel.speedJetEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar4, gameObjectManager.leadingCar4.getposition().x, 275, currentLevel.speedHelicopterEnemy));
        spawn(new HelicopterEnemy(gameView, this, gameObjectManager.helicopterPlayer, gameObjectManager.leadingCar4, gameObjectManager.leadingCar4.getposition().x + 10 * 11 * 3.5, 275, currentLevel.speedHelicopterEnemy));
    }

    private void comeBack() {
        gameObjectManager.helicopterPlayer.getposition().x = GameView.WIDTH / 2d;
        gameObjectManager.helicopterPlayer.getposition().y = GameView.HEIGHT / 2d;
        gameObjectManager.background.resetBackgroundsPositions();

        gameObjectManager.leadingCar1.getposition().x = GameView.WIDTH + 300;
        gameObjectManager.car12.getposition().x = gameObjectManager.leadingCar1.getposition().x + 5 * 11 * 3.5;
        gameObjectManager.car13.getposition().x = gameObjectManager.leadingCar1.getposition().x + 10 * 11 * 3.5;

        gameObjectManager.leadingCar2.getposition().x = GameView.WIDTH * 3 + 300;
        gameObjectManager.car22.getposition().x = gameObjectManager.leadingCar2.getposition().x + 5 * 11 * 3.5;
        gameObjectManager.car23.getposition().x = gameObjectManager.leadingCar2.getposition().x + 10 * 11 * 3.5;

        gameObjectManager.leadingCar3.getposition().x = -GameView.WIDTH * 3 + 300;
        gameObjectManager.car32.getposition().x = gameObjectManager.leadingCar3.getposition().x + 5 * 11 * 3.5;
        gameObjectManager.car33.getposition().x = gameObjectManager.leadingCar3.getposition().x + 10 * 11 * 3.5;

        gameObjectManager.leadingCar4.getposition().x = -GameView.WIDTH + 300;
        gameObjectManager.car42.getposition().x = gameObjectManager.leadingCar4.getposition().x + 5 * 11 * 3.5;
        gameObjectManager.car43.getposition().x = gameObjectManager.leadingCar4.getposition().x + 10 * 11 * 3.5;
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

    /**
     * Moves the game world to the left.
     * @param pixels numbers of pixels the game world is shifted
     */
    public void moveWorldToLeft(double pixels) {
        gameObjectManager.moveWorld(pixels, 0);
    }

    /**
     * Moves the game world to the left.
     * @param pixels numbers of pixels the game world is shifted
     */
    public void moveWorldToRight(double pixels) {
        gameObjectManager.moveWorld(-pixels, 0);
    }
}