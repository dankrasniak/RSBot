package rsbot.view;

import rsbot.MyMouseController;
import rsbot.observer.Observable;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.Draws;
import rsbot.view.drawable.SquareFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MirrorScreenPanel extends JPanel implements Observable, Draws {

    private BufferedImage gameMirror;
    private final Rectangle CAPTURE_RECT;
    private Robot robot;

    public MirrorScreenPanel(Rectangle capture_rect) {
        this.CAPTURE_RECT = capture_rect;

        Drawable defaultCursor = new SquareFrame(Color.RED, 0,0, 32);

        MyMouseController myMouseController = new MyMouseController(this, defaultCursor);
        addMouseListener(myMouseController);
        addMouseMotionListener(myMouseController);
        addObserver(myMouseController);

        try { this.robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}
    }

    public void paint(Graphics g) {
        gameMirror = robot.createScreenCapture(CAPTURE_RECT);
        notifyObservers();
        g.drawImage(gameMirror, 0, 0, null);
    }

    public void draw(Drawable drawable) {
        Graphics g = gameMirror.getGraphics();
        drawable.draw(g);
    }

    // region Observer
    public void notifyObservers() {
        observers.forEach(o -> o.update(gameMirror));
    }
    // endregion Observer
}
