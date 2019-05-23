package rsbot.view;

import rsbot.MyKeyboardController;
import rsbot.observer.Observable;
import rsbot.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MainWindowView extends JFrame implements Runnable, Observer, Observable {

    private final int GAME_WIDTH = 740;
    private final int GAME_HEIGHT = 500;
    private final int OFFSET_TO_BE_SPECIFIED = -8; // TODO
    private final Rectangle CAPTURE_RECT = new Rectangle(OFFSET_TO_BE_SPECIFIED, 0, GAME_WIDTH, GAME_HEIGHT);
    private final Point WINDOW_POSITION = new Point(CAPTURE_RECT.x + CAPTURE_RECT.width + 40, CAPTURE_RECT.y);

    private final int LOOP_DELAY = 25;
    private boolean RUNNING = true;

    private BufferedImage _gameMirror;

    public void initialize() {
        MirrorScreenPanel mirrorScreen = new MirrorScreenPanel(CAPTURE_RECT);
        mirrorScreen.addObserver(this);
        this.add(mirrorScreen);

        // Add Logic Controller
//        this.addObserver(LogicController);

        this.addKeyListener(new MyKeyboardController(this));

        // region window related
        this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setLocation(WINDOW_POSITION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        // endregion window related

        new Thread(this).start();
    }

    private void applicationLoop() {
        this.requestFocusInWindow();
        try { Thread.sleep(LOOP_DELAY); } catch (InterruptedException e) { e.printStackTrace(); }
        repaint();
    }

    // region Observer
    private List<Observer> _observers = new LinkedList<>();
    public void addObserver(Observer o) {
        _observers.add(o);
    }

    public void removeObserver(Observer o) {
        _observers.remove(o);
    }

    public void update(Object arg) {
        _gameMirror = (BufferedImage) arg;
        notifyObservers();
    }

    public void notifyObservers() {
        _observers.forEach(o -> o.update(_gameMirror));
    }
    // endregion Observer

    //region Runnable implementations - run, close
    public void run() {
        while (RUNNING)
            applicationLoop();
    }

    public void close() {
        RUNNING = false;
        System.exit(0);
    }
    //endregion
}
