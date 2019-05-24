package rsbot.view;

import rsbot.Mirror;
import rsbot.controllers.MyMouseController;
import rsbot.observer.Observable;
import rsbot.observer.Observer;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.Draws;
import rsbot.view.drawable.SquareFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MirrorScreenPanel extends JPanel implements Observable, Draws {

    private BufferedImage gameMirror;
    private final Rectangle CAPTURE_RECT;
    private Robot robot;
    private final int CAPTURE_FIELD_SIZE = 32;

    public MirrorScreenPanel(Rectangle capture_rect) {
        this.CAPTURE_RECT = capture_rect;

        Drawable defaultCursor = new SquareFrame(Color.RED, 0,0, CAPTURE_FIELD_SIZE);

        MyMouseController myMouseController = new MyMouseController(this, defaultCursor);
        addMouseListener(myMouseController);
        addMouseMotionListener(myMouseController);
        addObserver(myMouseController);
        addObserver(Mirror.getInstance());

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

    private List<Observer> _observers = new LinkedList<>();

    public void addObserver(Observer o) {
        _observers.add(o);
    }

    public void removeObserver(Observer o) {
        _observers.remove(o);
    }

    public void notifyObservers() {
        _observers.forEach(o -> o.update(gameMirror));
    }
    // endregion Observer
}
