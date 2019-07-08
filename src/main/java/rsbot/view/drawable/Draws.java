package rsbot.view.drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Draws {
    /**
     * Make the object draw the drawable element on the screen.
     *
     * @param drawable
     */
    void draw(Drawable drawable);

    /**
     * Note: Events are being handled as *last* so consider using {@link Draws#getNewSubImage}
     *
     * @param point location of the subimage
     * @return a subimage of a picture that is currently being held in the Mirror object.
     */
    BufferedImage getSubImage(Point point);

    /**
     * @param point location of the subimage
     * @return a subimage of a new screen capture
     */
    BufferedImage getNewSubImage(Point point);
}
