package tetris;

import java.awt.*;


public abstract class Scene {
    protected final Game game;
    public Scene(Game game) {
        this.game = game;
    }
    public abstract void update(long nanosPassed, Graphics2D g);
    public abstract void draw(Graphics2D g);
    public abstract void drawCell(Graphics2D g, int x, int y, Color col);
    public abstract void drawField(Graphics2D g);
    public abstract void drawFigure(Graphics2D g, Figure figure, int x, int y);
    public abstract void setMoveDelay(Delay update);
    public abstract Delay getMoveDelay();

}
