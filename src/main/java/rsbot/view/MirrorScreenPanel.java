package rsbot.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MirrorScreenPanel extends JPanel {

    private BufferedImage gameMirror;
    private final Rectangle CAPTURE_RECT;

    private Robot robot;

    public MirrorScreenPanel(Rectangle capture_rect) {
        this.CAPTURE_RECT = capture_rect;

        try { this.robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}
    }

    public void paint(Graphics g) {
        gameMirror = robot.createScreenCapture(CAPTURE_RECT);
        g.drawImage(gameMirror, 0, 0, null);
    }
}
