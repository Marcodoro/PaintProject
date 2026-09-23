import sum.kern.*;
import java.awt.*;


public class Button {
    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private int color;

    public Button(int x, int y, int sizeX, int sizeY, int color) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
    }
    public void drawButton(Buntstift pen){

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
    public int getX() {
        return this.x;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public int getSizeX() {
        return this.sizeX;
    }
    public int getSizeY() {
        return this.sizeY;
    }
    public int getColor() {
        return this.color;
    }
    public void setColor(int newColor) {
        this.color = newColor;
    }
}
