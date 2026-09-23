import sum.kern.*;
import java.awt.*;


public class Game {
    private Bildschirm screen;
    private Buntstift pen;
    private Tastatur key;
    private Maus mouse;
    private Button yellow;
    private Button green;
    private Button blue;
    private Button orange;
    private Button pink;
    private Button radieren;


    public Game() {
        screen = new Bildschirm(1280, 720);
        pen = new Buntstift();
        key = new Tastatur();
        mouse = new Maus();
        yellow = new Button();
        green = new Button();
        blue = new Button();
        orange = new Button();
        pink = new Button();
        radieren = new Button();


        pen.setzeLinienBreite(10);
        pen.setzeFarbe(Color.BLACK);
    }
    public void start() {
        erstelleButtons();
        malen();
    }
    public void erstelleButtons() {

        yellow.drawButton(100, 100, 100, 100, pen, Farbe.GELB);
        green.drawButton(300, 100, 100, 100, pen, Farbe.GRUEN);
        blue.drawButton(500, 100, 100, 100, pen, Farbe.BLAU);
        orange.drawButton(700, 100, 100, 100, pen, Farbe.ORANGE);
        pink.drawButton(900, 100, 100, 100, pen, Farbe.PINK);
        radieren.drawButton(1100, 100, 100, 100, pen, Farbe.SCHWARZ);

    }
    public void checkCollisionButtons(Button buttons) {
        int mouseX = mouse.hPosition();
        int mouseY = mouse.vPosition();

        if (mouseX >= buttons.x && mouseX <= buttons.x + buttons.sizeX && mouseY >= buttons.y && mouseY <= buttons.y + buttons.sizeY) {

            if (mouse.istGedrueckt()) {
                pen.hoch();
                switch (buttons.color) {
                    case Farbe.GELB:
                        pen.normal();
                        pen.setzeLinienBreite(10);
                        pen.setzeFarbe(Farbe.GELB);
                        break;
                    case Farbe.GRUEN:
                        pen.normal();
                        pen.setzeLinienBreite(10);
                        pen.setzeFarbe(Farbe.GRUEN);
                        break;
                    case Farbe.BLAU:
                        pen.normal();
                        pen.setzeLinienBreite(10);
                        pen.setzeFarbe(Farbe.BLAU);
                        break;
                    case Farbe.ORANGE:
                        pen.normal();
                        pen.setzeLinienBreite(10);
                        pen.setzeFarbe(Farbe.ORANGE);
                        break;
                    case Farbe.PINK:
                        pen.normal();
                        pen.setzeLinienBreite(10);
                        pen.setzeFarbe(Farbe.PINK);
                        break;
                    case Farbe.SCHWARZ:
                        pen.setzeLinienBreite(30);
                        pen.radiere();
                    default:
                        break;
                }
            }
        }
    }
    public void malen() {
        while (!mouse.doppelKlick()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkCollisionButtons(yellow);
            checkCollisionButtons(green);
            checkCollisionButtons(blue);
            checkCollisionButtons(pink);
            checkCollisionButtons(orange);
            checkCollisionButtons(radieren);


            if (mouse.istGedrueckt()) {
                int mouseX = mouse.hPosition();
                int mouseY = mouse.vPosition();
                pen.bewegeBis(mouseX, mouseY);
                pen.runter();
            }
            else {
                pen.hoch();
            }
        }
    }
}
