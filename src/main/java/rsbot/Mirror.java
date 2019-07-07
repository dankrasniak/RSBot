package rsbot;

import rsbot.observer.Observer;
import rsbot.view.MirrorScreenPanel;
import rsbot.view.drawable.Drawable;
import rsbot.view.drawable.Draws;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mirror implements Observer, Draws {
    private static Mirror ourInstance = new Mirror();
    private BufferedImage _gameMirror;

    public static Mirror getInstance() {
        return ourInstance;
    }

    private Mirror() {
    }

    public BufferedImage getSubImage(Point point) {
        int offset = MirrorScreenPanel.CAPTURE_FIELD_SIZE;
        return _gameMirror.getSubimage(point.x-offset/2, point.y-offset/2,
                offset, offset);
    }

    public void draw(Drawable drawable) {
        drawable.draw(_gameMirror.getGraphics());
    }

    public void update(Object arg) {
        _gameMirror = (BufferedImage) arg;
    }
}
