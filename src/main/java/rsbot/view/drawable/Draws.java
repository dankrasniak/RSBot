package rsbot.view.drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Draws {
    void draw(Drawable drawable);
    BufferedImage getSubImage(Point point);
}
