import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Target extends JButton {
    int pointValue;

    int x, y;
    int width, height;

    Color color;

    JLabel scoreTxt;

    Target(int width, int height, Color color, int pointValue, JLabel scoreTxt) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.pointValue = pointValue;
        this.scoreTxt = scoreTxt;
        init();
    }

    public void init() {
        this.setBounds(x, y, width, height);
        this.setBackground(color);
        this.addActionListener(clicked);
    }

    public void changeLocation(int x, int y) {
        this.setLocation(x, y);
    }

    // da sistemare
    static boolean isColliding(Target[] targets, Target currentTarget) {
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
        }
    };
}
