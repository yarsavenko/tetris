package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class DesktopGameBuilder {
    public static JFrame frame;
    public static Game build(Dimension screenSize, Collection<Figure> figures) {
        final CanvasGame game = new CanvasGame(screenSize, figures);
        frame = new JFrame();
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.createBufferStrategy(2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
        game.requestFocus();
        return game;
    }

    public static void close()
    {
        frame.dispose();
    }
}
