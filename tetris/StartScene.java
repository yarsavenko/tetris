package tetris;

import java.awt.*;


public class StartScene extends Scene {
    public StartScene(Game game) {
        super(game);
    }

    @Override
    public void update(long nanosPassed, Graphics2D g) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);
        g.setFont(new Font("Menu", Font.ITALIC, 30));
        g.setColor(Color.red);
        g.drawString("Start game - s", game.getScreenSize().width/2- 75, game.getScreenSize().height/2);
        g.drawString("Exit  game - Esc", game.getScreenSize().width/2 - 75, game.getScreenSize().height/2 + 50);
        g.drawString("Controls - i", game.getScreenSize().width/2 - 75, game.getScreenSize().height/2 + 100);
    }

    @Override
    public void drawCell(Graphics2D g, int x, int y, Color col) {

    }

    @Override
    public void drawField(Graphics2D g) {

    }

    @Override
    public void drawFigure(Graphics2D g, Figure figure, int x, int y) {

    }

    @Override
    public void setMoveDelay(Delay update) {

    }

    @Override
    public Delay getMoveDelay() {
        return null;
    }
}
