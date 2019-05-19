package rsbot.view;

import rsbot.MyKeyboardController;

import javax.swing.*;
import java.awt.*;

public class MainWindowView extends JFrame implements Runnable {

    private static final int GAME_WIDTH = 740;
    private static final int GAME_HEIGHT = 500;
    private static final int WAIT_TIME = 25;
    private boolean RUNNING = true;

    public void initialize() {
        this.addKeyListener(new MyKeyboardController(this));

        this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        new Thread(this).start();
    }

    private void applicationLoop() {
        this.requestFocusInWindow();
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    //region mandatory
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
