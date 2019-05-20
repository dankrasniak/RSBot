package rsbot.view;

import rsbot.MyKeyboardController;

import javax.swing.*;
import java.awt.*;

public class MainWindowView extends JFrame implements Runnable {

    private final int GAME_WIDTH = 740;
    private final int GAME_HEIGHT = 500;
    private final int OFFSET_TO_BE_SPECIFIED = -8;
    private final Rectangle CAPTURE_RECT = new Rectangle(OFFSET_TO_BE_SPECIFIED, 0, GAME_WIDTH, GAME_HEIGHT);
    private final Point WINDOW_POSITION = new Point(CAPTURE_RECT.x + CAPTURE_RECT.width + 40, CAPTURE_RECT.y);

    private final int LOOP_DELAY = 25;
    private boolean RUNNING = true;

    public void initialize() {
        this.add(new MirrorScreenPanel(CAPTURE_RECT));
        this.add(new DetailsScreenPanel());

        this.addKeyListener(new MyKeyboardController(this));

        this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setLocation(WINDOW_POSITION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        new Thread(this).start();
    }

    private void applicationLoop() {
        this.requestFocusInWindow();
        try { Thread.sleep(LOOP_DELAY); } catch (InterruptedException e) { e.printStackTrace(); }
        repaint();
    }

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
