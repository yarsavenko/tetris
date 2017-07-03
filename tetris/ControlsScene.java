package tetris;

import java.awt.*;


public class ControlsScene extends Scene {
    public ControlsScene(Game game) {
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
        g.drawString("Main page (m)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 - 100);
        g.drawString("Controls (i)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 - 50);
        g.drawString("Move left (a, <-)", game.getScreenSize().width/2- 125, game.getScreenSize().height/2);
        g.drawString("Move right (d, ->)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 + 50);
        g.drawString("Rotate (Space)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 + 100);
        g.drawString("Speed increese (+)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 + 150);
        g.drawString("Speed decreese (-)", game.getScreenSize().width/2 - 125, game.getScreenSize().height/2 + 200);
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
