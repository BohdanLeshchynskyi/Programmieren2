package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;
import java.awt.*;
import java.util.Random;

public class  RandomBall extends GameObject {
    private final Position targetPosition;

    private Random random;

    public RandomBall(GameView gameView) {
        super(gameView);
        this.targetPosition = new Position(800, 200);
        this.speedInPixel = 4;
        this.size = 50;
        this.random = new Random();
    }

    private void calculateRandomTargetPosition() {
        targetPosition.x = random.nextDouble() * GameView.WIDTH % (GameView.WIDTH - size);
        targetPosition.y = random.nextDouble() * GameView.HEIGHT % (GameView.HEIGHT - size);
    }

    @Override
    public void updatePosition() {
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel){
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
        } else {
            calculateRandomTargetPosition();
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.YELLOW);
        gameView.addOvalToCanvas(targetPosition.x, targetPosition.y, size, size, 2, false, Color.WHITE);
    }
}
