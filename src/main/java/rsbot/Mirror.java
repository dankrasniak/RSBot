package rsbot;

import rsbot.observer.Observable;
import rsbot.observer.Observer;
import rsbot.view.MirrorScreenPanel;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.Draws;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Mirror implements Observable, Observer, Draws {
    private static Mirror ourInstance = new Mirror();

    private BufferedImage _gameMirror;

    private final int GAME_WIDTH = 740;
    private final int GAME_HEIGHT = 500;
    private final int OFFSET_TO_BE_SPECIFIED = -8; // TODO
    private final Rectangle CAPTURE_RECT = new Rectangle(OFFSET_TO_BE_SPECIFIED, 0, GAME_WIDTH, GAME_HEIGHT);
    private Robot robot;

    public static Mirror getInstance() {
        return ourInstance;
    }

    private Mirror() {
        try { this.robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}
        this.addObserver(this);
    }

    public BufferedImage getGameMirror() {
        _gameMirror = robot.createScreenCapture(CAPTURE_RECT);
        return _gameMirror;
    }

    // region Draws
    private List<Drawable> _toDraw = new LinkedList<>();
    public void draw(Drawable drawable) {
        _toDraw.add(drawable);
    }

    public BufferedImage getSubImage(Point point) {
        int offset = MirrorScreenPanel.CAPTURE_FIELD_SIZE;
        return _gameMirror.getSubimage(point.x-offset/2, point.y-offset/2,
                offset, offset);
    }

    public BufferedImage getNewSubImage(Point point) {
        _gameMirror = robot.createScreenCapture(CAPTURE_RECT);
        int offset = MirrorScreenPanel.CAPTURE_FIELD_SIZE;
        return _gameMirror.getSubimage(point.x-offset/2, point.y-offset/2,
                offset, offset);
    }
    // endregion Draws

    public void update(Object arg) {
        while (!_toDraw.isEmpty())
            _toDraw.remove(0).draw(_gameMirror.getGraphics());
    }

    // region Observer
    private List<Observer> _observers = new LinkedList<>();

    public void addObserver(Observer o) {
        _observers.add(0, o);
    }

    public void removeObserver(Observer o) {
        _observers.remove(o);
    }

    public void notifyObservers() {
        _observers.forEach(o -> o.update(_gameMirror));
    }
    // endregion Observer
}
