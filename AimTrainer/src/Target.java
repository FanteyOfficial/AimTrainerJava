import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Target extends JButton {
    int pointValue;

    int x, y;
    int width, height;

    Color color;

    Target(int x, int y, int width, int height, Color color, int pointValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.pointValue = pointValue;
        init();
    }

    public void init() {
        this.setBounds(x, y, width, height);
        this.setBackground(color);
        this.addActionListener(clicked);
    }

    ActionListener clicked = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            Storage.points += pointValue;
            //System.out.println(Storage.points);
        }
    };
}
