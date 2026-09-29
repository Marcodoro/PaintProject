import sum.kern.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
    private Bildschirm screen;
    private Buntstift pen;
    private Tastatur key;
    private Maus mouse;
    private Button[] buttons;
    private Button[] gridX1;
    private Button[] gridX2;
    private Button[] gridY1;
    private Button[] gridY2;
    private final int breite = 5;


    public Game() {
        screen = new Bildschirm(1280, 720);
        pen = new Buntstift();
        key = new Tastatur();
        mouse = new Maus();

        int[] farben = {
                Farbe.GELB, Farbe.BLAU,
                Farbe.GRUEN, Farbe.ORANGE,
                Farbe.PINK, Farbe.SCHWARZ
        };

        buttons = new Button[farben.length];
        gridX1 = new Button[40];
        gridX2 = new Button[40];
        gridY1 = new Button[23];
        gridY2 = new Button[23];

        pen.setzeLinienBreite(10);
        pen.setzeFarbe(Color.BLACK);

        for (int i = 0; i < buttons.length; i++) {
            pen.setzeLinienBreite(10);

            int xPosition = 100 + (i * 200);
            buttons[i] = new Button(xPosition, 100, 100, 100, farben[i], breite);
        }
        for (int i = 0; i < gridX1.length; i++) {
            pen.setzeLinienBreite(5);
            gridX1[i] = new Button(i*32 + pen.linienBreite(), 32, 32, 32, Farbe.SCHWARZ, breite);
        }
        for (int i = 0; i < gridX2.length; i++) {
            pen.setzeLinienBreite(5);
            gridX2[i] = new Button(i*32 + pen.linienBreite(), 668 + pen.linienBreite(), 32, 32, Farbe.SCHWARZ, breite);
        }
        for (int i = 0; i < gridY1.length; i++) {
            pen.setzeLinienBreite(5);
            gridY1[i] = new Button(pen.linienBreite(), 32 * i, 32, 32, Farbe.SCHWARZ, breite);
        }
        for (int i = 0; i < gridY2.length; i++) {
            pen.setzeLinienBreite(5);
            gridY2[i] = new Button(1227 - pen.linienBreite(), 32 * i, 32, 32, Farbe.SCHWARZ, breite);
        }
    }
    public void start() {
        erstelleButtons();
        malen();
    }
    public void erstelleButtons() {
        for (Button p: buttons) {
            pen.setzeLinienBreite(10);
            p.drawButton(pen);
        }
        for (Button p : gridX1) {
            pen.setzeLinienBreite(5);

            p.drawButton(pen);
        }
        for (Button easy : gridX2) {
            pen.setzeLinienBreite(5);

            easy.drawButton(pen);
        }
        for (Button yes : gridY1) {
            pen.setzeLinienBreite(5);

            yes.drawButton(pen);
        }
        for (Button p : gridY2) {
            pen.setzeLinienBreite(5);

            p.drawButton(pen);
        }
    }
    public void checkCollisionButtons(Button buttons) {
        int mouseX = mouse.hPosition();
        int mouseY = mouse.vPosition();

        if (mouseX >= buttons.getX() && mouseX <= buttons.getX() + buttons.getSizeX() && mouseY >= buttons.getY() && mouseY <= buttons.getY() + buttons.getSizeY()) {

            if (mouse.istGedrueckt()) {
                pen.hoch();
                if (buttons.getColor() == 0) {
                    pen.radiere();
                }
                else {
                    pen.normal();
                    pen.setzeFarbe(buttons.getColor());
                }
            }
        }


    }
    public void checkCollisionButtonsGrid(Button buttons) {
        int mouseX = mouse.hPosition();
        int mouseY = mouse.vPosition();

        if (mouseX >= buttons.getX() && mouseX <= buttons.getX() + buttons.getSizeX() && mouseY >= buttons.getY() && mouseY <= buttons.getY() + buttons.getSizeY()) {

            if (mouse.istGedrueckt()) {
                pen.hoch();
                buttons.setzeLinienBreite(10);
                buttons.setColor(Farbe.GRUEN);
                buttons.drawButton(pen);
                buttons.setzeLinienBreite(breite);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pen.hoch();

                        pen.radiere();
                        buttons.setzeLinienBreite(10);
                        buttons.drawButton(pen);
                        pen.normal();
                        buttons.setColor(Farbe.SCHWARZ);
                        buttons.setzeLinienBreite(breite);

                        buttons.drawButton(pen);
                    }
                }, 2000 );


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

            for (Button p : buttons) {
                checkCollisionButtons(p);
            }

            for (Button p : gridY1) {
                checkCollisionButtonsGrid(p);
            }

            for (Button p : gridY2) {
                checkCollisionButtonsGrid(p);
            }

            for (Button p : gridX1) {
                checkCollisionButtonsGrid(p);
            }

            for (Button p : gridX2) {
                checkCollisionButtonsGrid(p);
            }

            if (mouse.istGedrueckt()) {
                int mouseX = mouse.hPosition();
                int mouseY = mouse.vPosition();
                pen.bewegeBis(mouseX, mouseY);
                pen.setzeLinienBreite(breite);
                pen.runter();
            }
            else {
                pen.hoch();
            }
        }
    }
}
