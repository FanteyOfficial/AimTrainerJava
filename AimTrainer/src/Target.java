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

    ActionListener clicked = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            Storage.points += pointValue;
            scoreTxt.setText("Score: " + Storage.points);
            //System.out.println(Storage.points);
        }
    };
}
