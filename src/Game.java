import sum.kern.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Game {
    private Bildschirm screen;
    private Buntstift pen;
    private Tastatur key;
    private Maus mouse;
    private Button[] buttons;
    private int scrollRichtung = 0;


    public Game() {
        screen = new Bildschirm(1280, 720);
        pen = new Buntstift();
        key = new Tastatur();
        mouse = new Maus();

        int[] farben = {
            Farbe.GELB, Farbe.BLAU, Farbe.GRUEN, Farbe.ORANGE, Farbe.PINK, Farbe.SCHWARZ
        };

        buttons = new Button[farben.length];

        pen.setzeLinienBreite(10);
        pen.setzeFarbe(Color.BLACK);


        for (int i = 0; i < buttons.length; i++) {
            int xPosition = 100 + (i * 200);
            buttons[i] = new Button(xPosition, 100, 100, 100, farben[i]);
        }
    }
    public void start() {
        erstelleButtons();
        malen();
    }
    public void erstelleButtons() {
        for (Button p: buttons) {
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

            if (mouse.istGedrueckt()) {
                int mouseX = mouse.hPosition();
                int mouseY = mouse.vPosition();
                pen.bewegeBis(mouseX, mouseY);
                pen.runter();
            }
            else {
                pen.hoch();
            } /* TO BE FIXED SOON
            screen.addMouseWheelListener(new MouseWheelListener() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    scrollRichtung = e.getWheelRotation();
                    pen.setzeLinienBreite(pen.linienBreite() - scrollRichtung);
                }
            });
            */
        }
    }
}
