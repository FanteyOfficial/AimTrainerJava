import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ColorConvertOp;
import java.util.Random;

public class Window extends JFrame {
    private JPanel container;

    JLabel winTitle;
    JLabel scoreTxt;
    Target[] targets;
    Color[] targetsColors;
    Random r = new Random();

    Window() {
        container = new JPanel();
        container.setBackground(Color.decode("#3E92CC"));
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Storage.points -= 300;
                    scoreTxt.setText("Score: " + Storage.points);
                    //System.out.println(Storage.points);
                }
            }
        });
        setContentPane(container);
        setVisible(true);



        winTitle = new JLabel("Aim Trainer");
        winTitle.setLocation((1000/2)-(300/2)+70, 5);
        winTitle.setSize(300,50);
        winTitle.setFont(new Font("Sans Serif", Font.BOLD, 32));

        scoreTxt = new JLabel("Score: " + Storage.points);
        scoreTxt.setLocation(5, 5);
        scoreTxt.setSize(300,20);
        scoreTxt.setFont(new Font("Sans Serif", Font.BOLD, 22));

        targets = new Target[3];
        targetsColors = new Color[] {Color.decode("#bb4430"), Color.decode("#E9D758"), Color.decode("#0CF574")};

        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(dim, dim, targetsColors[i], 50*(targets.length-i), scoreTxt);

            targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
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
        add(scoreTxt);

        setVisible(true);
    }
}
