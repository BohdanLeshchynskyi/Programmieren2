package thd.game.managers;

import thd.gameobjects.movable.HelicopterPlayer;
import thd.gameview.GameView;
import java.awt.event.KeyEvent;

class InputManager {
    public static final boolean DIAGONAL_MOVEMENT_ALLOWED = false;

    private GameView gameView;
    private HelicopterPlayer helicopterPlayer;

    InputManager(GameView gameView, HelicopterPlayer helicopterPlayer) {
        this.gameView = gameView;
        this.helicopterPlayer = helicopterPlayer;
    }

    void updateUserInputs() {
        Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();
        if (DIAGONAL_MOVEMENT_ALLOWED) {
            for (int keyCode : pressedKeys) {
                evalKey(keyCode);
            }
        } else {
            for (int keyCode = 0; keyCode < Math.min(1, pressedKeys.length); keyCode++) {
                evalKey(pressedKeys[keyCode]);
            }
        }
    }

    private void evalKey(int key) {
        if (key == KeyEvent.VK_UP) {
            helicopterPlayer.up();
        } else if (key == KeyEvent.VK_DOWN) {
            helicopterPlayer.down();
        } else if (key == KeyEvent.VK_RIGHT) {
            helicopterPlayer.right();
        } else if (key == KeyEvent.VK_LEFT) {
            helicopterPlayer.left();
        } else if (key == KeyEvent.VK_SPACE) {
            helicopterPlayer.shoot();
        }
    }
}