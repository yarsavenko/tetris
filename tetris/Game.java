package tetris;

import java.awt.*;


public interface Game {
    void start();
    void pause();
    Dimension getScreenSize();
    Input getInput();
    void setScene(Scene scene);
    Figure getRandFigure();
    boolean isFigure();
    Scene getScene();
    void setField(Field field);
    Field getField();
}