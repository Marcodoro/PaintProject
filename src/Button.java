import sum.kern.*;
import java.awt.*;


public class Button {
    public int x;
    public int y;
    public int sizeX;
    public int sizeY;
    public int color;
    public Button() {
    }
    public void drawButton(int x, int y, int sizeX, int sizeY, Buntstift pen, int color){

        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;

        pen.bewegeBis(x, y);
        pen.runter();
        pen.setzeFarbe(color);
        pen.zeichneRechteck(sizeX, sizeY);
        pen.setzeFarbe(Farbe.SCHWARZ);
        pen.hoch();
    }
}
