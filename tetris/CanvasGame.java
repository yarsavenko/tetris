package tetris;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import static tetris.Constants.CELL_SIZE;
import static tetris.Constants.WIN_HEIGHT;
import static tetris.Constants.WIN_WIDTH;

public class CanvasGame extends Canvas implements Game, Runnable {
    private Thread gameThread;
    private AtomicBoolean running;
    private Input input;
    private Scene scene;
    private Collection<Figure> figures;
    private Graphics2D g;
    private Figure curFigure;
    private Field field;
    public boolean gameOver = false;
    public boolean games = false;
    private MainScene mainScene;


    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }

    public boolean isFigure(){
        if (curFigure == null)
            return false;
        return true;
    }

    public Figure getRandFigure()
    {
        double b = (int)(Math.random()*figures.size());
        if (b > 5.5)
            b = 6;
        int a = (int)b;
        int d[] = {0,1};
        Figure ff = (Figure)((ArrayList) figures).get(a);
        Figure gg = null;
        try {
             gg =(Figure)ff.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gg;
    }

    public CanvasGame(Dimension screenSize, Collection<Figure> figures) {
        running = new AtomicBoolean(false);
        setSize(screenSize);
        initInput();
        initFocusListener();
        this.figures = figures;
    }
    private void initInput() {
        input = new Input();
        addKeyListener(input);
    }
    private void initFocusListener() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                start();
            }
            @Override
            public void focusLost(FocusEvent event) {
                pause();
            }
        });
    }
    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    @Override
    public void pause() {
        if (running.compareAndSet(true, false)) {
            try {
                gameThread.join();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
    @Override
    public Dimension getScreenSize() {
        return getSize();
    }
    @Override
    public Input getInput() {
        return input;
    }
    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void run() {
        long previousIterationTime = System.nanoTime();
            while (running.get()) {
                if (scene == null) {
                    continue;
                }
                long now = System.nanoTime();
                long nanosPassed = now - previousIterationTime;
                previousIterationTime = now;
                if(!this.gameThread.isInterrupted()) {
                    g = (Graphics2D) getBufferStrategy().getDrawGraphics();
                    if (scene instanceof StartScene || scene instanceof  ControlsScene)
                        scene.draw(g);
                    if (scene instanceof MainScene && this.curFigure == null && this.field == null)
                        scene.draw(g);
                    scene.update(nanosPassed, g);
                    if (field != null && field.isOver()) {
                        gameOver = true;
                       // break;
                    }
                    getBufferStrategy().show();
                        procesInput(gameOver);
                }
            }

                DesktopGameBuilder.close();
                this.gameThread.interrupt();




    }
    public void procesInput(boolean gameOver){

        Collection<KeyEvent> keyEvent;
        keyEvent = this.getInput().getKeyPressedEvents();
            for (KeyEvent event : keyEvent) {
                if (event != null) {
                    System.out.println(event.getKeyChar() + " " + event.getKeyCode());
                }
                if (event.getKeyCode() == 27) {
                  //  this.interrupt();
                    running.set(false);
                    System.out.println("end");
                }
                if (event.getKeyCode() == 83 && !games) {
                    if (mainScene == null){
                        this.field = new Field(CELL_SIZE *WIN_WIDTH, CELL_SIZE * WIN_HEIGHT);
                        this.mainScene = new MainScene(this);
                        this.setScene(mainScene);
                        games = true;
                        this.setField(field);
                        field.setX((WIN_WIDTH / 2) * 25);
                        field.setY(100);}
                    this.setScene(mainScene);
                    games= true;
                }
                if (event.getKeyCode() == 78 && this.gameOver){

                    this.field = new Field(CELL_SIZE *WIN_WIDTH, CELL_SIZE * WIN_HEIGHT);
                    this.mainScene = new MainScene(this);
                    games = true;
                    field.setX((WIN_WIDTH / 2) * 25);
                    field.setY(100);
                    this.gameOver = false;
                }

                if (games && (event.getKeyCode() == 39 || event.getKeyCode() == 68)) {
                    this.getField().setX(25);
                }
                if (games && (event.getKeyCode() == 37 || event.getKeyCode() == 65)) {
                    this.getField().setX(-25);
                }
                if (games && event.getKeyCode() == 32)
                    this.getField().getCurFigure().rotateOne();
                if (games && event.getKeyCode() == 45) {
                    long old = this.getScene().getMoveDelay().getDelayNanos();
                    if (old + 100 <= 900)
                        this.getScene().setMoveDelay(new Delay(old + 100));
                }
                if (games && event.getKeyCode() == 61) {
                    long old = this.getScene().getMoveDelay().getDelayNanos();
                    if (old - 100 >= 100)
                        this.getScene().setMoveDelay(new Delay(old - 100));
                }
                if (games && event.getKeyCode() == 80)
                    this.pause();
                if ( event.getKeyCode() == 73){
                    this.setScene(new ControlsScene(this));
                    games = false;
                }
                if (event.getKeyCode() == 77){
                    this.setScene(new StartScene(this));
                    games = false;
                }

            }
    }
}
