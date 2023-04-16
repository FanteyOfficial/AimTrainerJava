import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Target extends Thread{
    int pointValue;

    int x, y;
    int width, height;
    public JButton bersaglio;
    Color color;
    String colore;
    SemaphoreManager gestione;
    JLabel scoreTxt;
    JFrame gw;

    Target(int width, int height,String colore, Color color, int pointValue, JLabel scoreTxt, SemaphoreManager gestione, JFrame win) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.colore = colore;
        this.gestione = gestione;
        this.pointValue = pointValue;
        this.scoreTxt = scoreTxt;
        bersaglio = new JButton();
        this.gw = win;
        init();
    }
    @Override
    public void run() {
        while(true) {
            gestione.appari(colore);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        }
    }
    public void init() {
        bersaglio.setBounds(x, y, width, height);
        bersaglio.setBackground(color);
        bersaglio.addActionListener(clicked);
    }

    public void changeLocation(int x, int y) {
        bersaglio.setLocation(x, y);
    }

    // da sistemare
    static boolean isColliding(JButton[] targets, JButton currentTarget) {
        Rectangle rect = new Rectangle();
        Rectangle[] rects = new Rectangle[targets.length];

        rect = currentTarget.getBounds();
        int temp = 0;
        for (int i=0; i<targets.length; i++) {
            if (rect != targets[i].getBounds()) {
                rects[temp] = targets[i].getBounds();
                temp++;
            }
            System.out.println(rect == targets[i].getBounds());
        }

        //if (rects[0].intersects(rects[1]) || rects[0].intersects(rects[2]) || rects[1].intersects(rects[2])) {
        if (rect.intersects(rects[0]) || rect.intersects(rects[1])) {
            return true;
        } else {
            return false;
        }
    }
    
    ActionListener clicked = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            Storage.points += pointValue;
            scoreTxt.setText("Score: " + Storage.points);
            //System.out.println(Storage.points);
            gw.remove(bersaglio);
        }
    };


}
