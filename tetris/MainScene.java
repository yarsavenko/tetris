package tetris;

import java.awt.*;

public class MainScene extends Scene {
    private Delay moveDelay;
    private Delay drawDelay;
    public MainScene(Game game){
        super(game);
        moveDelay = new Delay(300);
        drawDelay = new Delay(100);
    }
    public void setMoveDelay(Delay update){
        this.moveDelay = update;
    }
    public Delay getMoveDelay(){
        return moveDelay;
    }

    @Override
    public void update(long nanosPassed, Graphics2D g) {
        drawField(g);
        if (!game.getField().isFigure())
            game.getField().setCurFigure(game.getRandFigure(), game.getRandFigure());
        if (drawDelay.updateAndCheck(nanosPassed))
        {
            draw(g);
            drawField(g);
            drawFigure(g, game.getField().getNextFigure(), 270, 10);
            drawFigure(g,game.getField().getCurFigure(), ((CanvasGame) this.game).getField().getX(), ((CanvasGame) this.game).getField().getY());
        }
        if (moveDelay.updateAndCheck(nanosPassed) && !((CanvasGame)game).gameOver)
        {

                if(!game.getField().saveField())
                    game.getField().setY(game.getField().getY()+25);
                else
                    game.getField().tryRemove();
        }
        if (((CanvasGame)game).gameOver) {
            g.setColor(Color.black);
            g.fillRect(25, game.getScreenSize().height / 2 - 40, game.getScreenSize().width-50, 80);
            g.setColor(Color.red);
            g.setFont(new Font("Over", Font.ITALIC, 30));
            g.drawString("Game over", 50, game.getScreenSize().height / 2);
            g.drawString("Press 'n' for restart" , 50, game.getScreenSize().height/2+30);
        }
    }

    @Override
    public void draw(Graphics2D g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, game.getScreenSize().width, game.getScreenSize().height);
    }

    @Override
    public void drawCell(Graphics2D g, int x, int y, Color col) {
        g.setColor(col);
        g.fillRect(x + 1, y + 1, 23, 23);
    }

    @Override
    public void drawField(Graphics2D g) {
        g.setColor(Color.red);
            g.drawString("Score "+game.getField().getScore(), 30, 30);
            g.drawString("Speed "+(11 - game.getScene().getMoveDelay().getDelayNanos()/100), 80, 30);
            g.drawLine(0,125,game.getScreenSize().width,125);
            int x = 0;
            int y = 0;
            for (int f[]: game.getField().getField()){
                x = 0;
                for(int a: f){
                    if (a == 1)
                        drawCell(g, x, y, Color.GREEN);
                    x+=25;
                }
                y+=25;
            }
    }

    @Override
    public void drawFigure(Graphics2D g, Figure figure, int x, int y) {
        int a = x;
        for(int f[] : figure.getFig())
        {
            for(int d : f){
             if(d == 1)
                 drawCell(g, x, y, Color.cyan);
                    x+=25;
            }
            y+=25;
            x = a;
        }

    }
}
