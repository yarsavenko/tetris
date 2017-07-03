package tetris;

import java.awt.*;
import java.util.Collection;

import static tetris.Constants.CELL_SIZE;
import static tetris.Constants.WIN_HEIGHT;
import static tetris.Constants.WIN_WIDTH;


public class DesktopLauncher {
    public static void main(String[] args) {
        Collection<Figure> figures = InitFigures.initFig();
        Game game = DesktopGameBuilder.build(new Dimension(CELL_SIZE * WIN_WIDTH, CELL_SIZE * WIN_HEIGHT), figures );
        game.setScene(new StartScene(game));
        game.start();
    }
}
