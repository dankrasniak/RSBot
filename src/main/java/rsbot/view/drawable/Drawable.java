package rsbot.view.drawable;

import java.awt.*;

public abstract class Drawable {
    Color color;
    protected int posX;
    protected int posY;

    public void draw(Graphics g) {}

    public void setPosition(Point newPosition) {
        this.posX = newPosition.x;
        this.posY = newPosition.y;
    }
}
