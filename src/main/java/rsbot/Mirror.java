package rsbot;

import rsbot.observer.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mirror implements Observer {
    private static Mirror ourInstance = new Mirror();
    private BufferedImage _gameMirror;

    public static Mirror getInstance() {
        return ourInstance;
    }

    private Mirror() {
    }

    public BufferedImage getSubImage(Point point) {
        
    }

    public void update(Object arg) {
        _gameMirror = (BufferedImage) arg;
    }
}
