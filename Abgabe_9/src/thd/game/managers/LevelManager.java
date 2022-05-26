package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level.Difficulty;
import thd.game.level.Level1;
import thd.game.level.Level2;

import java.util.LinkedList;


class LevelManager {
    private Difficulty difficulty;
    private final LinkedList<Level> levels;

    private int levelCounter;

    LevelManager(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.levelCounter = 0;
        this.levels = new LinkedList<>();
        this.levels.add(new Level1(difficulty));
        this.levels.add(new Level2(difficulty));
    }

    boolean hasNextLevel() {
        return levelCounter < 2;
    }

    Level nextLevel() {
        if (hasNextLevel()){
            return levels.get(levelCounter++);
        } else {
            throw new NoMoreLevelsAvailableException("All available levels has already been played!");
        }
    }

    void resetLevelCounter() {
        levelCounter = 0;
    }
}
