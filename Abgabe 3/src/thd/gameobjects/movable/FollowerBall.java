package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;
import java.awt.*;

public class FollowerBall extends GameObject {
    private final Position targetPosition;
    private RandomBall followMe;


    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        this.position.x = GameView.WIDTH - size;
        this.position.y = GameView.HEIGHT - size;
        this.targetPosition = new Position(followMe.getPosition().x, followMe.getPosition().y);
        this.speedInPixel = 3;
        this.size = 50;
        this.followMe = followMe;
    }

    @Override
    public void updatePosition() {
        targetPosition.x = followMe.getPosition().x;
        targetPosition.y = followMe.getPosition().y;
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel){
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.GREEN);
    }
}
