package rsbot.view;

import rsbot.MyKeyboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindowView extends JFrame implements Runnable {

    private static final int GAME_WIDTH = 740;
    private static final int GAME_HEIGHT = 500;
    private static final int OFFSET_TO_BE_SPECIFIED = -8;
    private final Rectangle CAPTURE_RECT = new Rectangle(OFFSET_TO_BE_SPECIFIED, 0, GAME_WIDTH, GAME_HEIGHT);
    private final Point WINDOW_POSITION = new Point(CAPTURE_RECT.x + CAPTURE_RECT.width + 40, CAPTURE_RECT.y);

    private static final int WAIT_TIME = 25;
    private boolean RUNNING = true;

    private BufferedImage gameMirror;
    private Robot robot;

    public void initialize() {
        this.addKeyListener(new MyKeyboardController(this));

        try { this.robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}

        this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setLocation(WINDOW_POSITION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        new Thread(this).start();
    }

    public void paint(Graphics g) {
        gameMirror = robot.createScreenCapture(CAPTURE_RECT);
        g.drawImage(gameMirror, 0, 0, null);
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
