import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window extends JFrame {
    JLabel winTitle;
    Target[] targets;
    Random r = new Random();

    Window() {
        winTitle = new JLabel("Aim Trainer");
        winTitle.setLocation((1000/2)-(300/2)+70, 5);
        winTitle.setSize(300,100);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        targets = new Target[3];
        for (int i=0; i<targets.length; i++) {
            targets[i] = new Target(r.nextInt()*1000+1, r.nextInt()*1000+1, r.nextInt()*i, r.nextInt()*i,
                    Color.BLACK, 50*i);
        }

        init();
    }

    public void init() {
        setLayout(null);
        setSize(1000, 800);
        setResizable(false);
        setTitle("AimTrainer");

        add(winTitle);

        setVisible(true);
    }
}
