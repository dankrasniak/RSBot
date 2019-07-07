package rsbot.view.drawable;

import java.awt.*;

public class SquareFrame extends Drawable {

    private int fieldSize;

    public SquareFrame(Color color, int posX, int posY, int fieldSize)  {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.fieldSize = fieldSize;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(posX-fieldSize/2-1, posY-fieldSize/2-1,
                fieldSize+1, fieldSize+1);
    }
}
