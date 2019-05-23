package rsbot;

import rsbot.observer.Observer;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.Draws;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseController implements MouseListener, MouseMotionListener, Observer {

    private Draws _view;
    private Drawable _defaultCursor;

    public MyMouseController(Draws view, Drawable defaultCursor) {
        this._view = view;
        this._defaultCursor = defaultCursor;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        _defaultCursor.setPosition(e.getLocationOnScreen());
    }

    public void update(Object arg) {
        _view.draw(_defaultCursor);
    }
}
