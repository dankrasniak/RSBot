package rsbot.view;

import rsbot.Mirror;
import rsbot.controllers.MyMouseController;
import rsbot.observer.Observable;
import rsbot.observer.Observer;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.SquareFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MirrorScreenPanel extends JPanel {

    private BufferedImage gameMirror;
    public static final int CAPTURE_FIELD_SIZE = 32;

    public MirrorScreenPanel() {
        Drawable defaultCursor = new SquareFrame(Color.RED, 0,0, CAPTURE_FIELD_SIZE);

        MyMouseController myMouseController = new MyMouseController(Mirror.getInstance(), defaultCursor);
        addMouseListener(myMouseController);
        addMouseMotionListener(myMouseController);
        Mirror.getInstance().addObserver(myMouseController);
    }

    public void paint(Graphics g) {
        gameMirror = Mirror.getInstance().getGameMirror();
        Mirror.getInstance().notifyObservers();
        g.drawImage(gameMirror, 0, 0, null);
    }
}
