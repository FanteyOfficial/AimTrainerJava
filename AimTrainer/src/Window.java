import javax.swing.*;
import java.awt.*;
import java.awt.image.ColorConvertOp;
import java.util.Random;

public class Window extends JFrame {
    JLabel winTitle;
    Target[] targets;
    Color[] targetsColors;
    Random r = new Random();

    Window() {
        winTitle = new JLabel("Aim Trainer");
        winTitle.setLocation((1000/2)-(300/2)+70, 5);
        winTitle.setSize(300,100);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        targets = new Target[3];
        targetsColors = new Color[] {Color.RED, Color.ORANGE, Color.GREEN};

        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(r.nextInt(10, 800), r.nextInt(10, 600), dim, dim,
                    targetsColors[i], 50*(i+1));
            add(targets[i]);

            //System.out.println();
        }

        init();
    }

    public void init() {
        setLayout(null);
        setSize(1000, 800);
        //setResizable(false);
        setTitle("AimTrainer");

        add(winTitle);

        setVisible(true);
    }
}
