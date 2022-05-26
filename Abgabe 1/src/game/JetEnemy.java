package game;

public class JetEnemy {
    Position position;
    double speedInPixel;
    GameView gameView;
    double size;
    double rotation;

    final String jetGrafic;

    JetEnemy(GameView gameView) {
        this.position = new Position(0,GameView.HEIGHT / 2);
        this.speedInPixel = 1;
        this.gameView = gameView;
        this.size = 0.1;
        this.rotation = 0;
        jetGrafic = "BW\n" +
                    "WB";
    }

    @Override
    public String toString() {
        return "Jet: " + position;
    }

    void updatePosition() {
        position.right(speedInPixel);
    }

    public void addToCanvas() {
        gameView.addBlockImageToCanvas(jetGrafic, position.x, position.y, size, rotation);
        gameView.addImageToCanvas("blueEnemyJet.png", position.x, position.y, size, rotation);
    }

}
