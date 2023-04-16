import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GameWindow extends JFrame implements KeyListener {
    private JPanel container;

    JLabel winTitle;
    JLabel scoreTxt;
    Target[] targets;
    Color[] targetsColors;
    Random r = new Random();
    Semaphore gottaAim = new Semaphore(1);

    GameWindow() {
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

        // create target
        targets = new Target[3];
        targetsColors = new Color[] {Color.decode("#bb4430"), Color.decode("#E9D758"), Color.decode("#0CF574")};

        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(dim, dim, targetsColors[i], 50*(targets.length-i), scoreTxt);

            //System.out.println();
        }

        // place target non-colliding
        for (int i=0; i<targets.length; i++) {
            targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
            add(targets[i]);

            /*while(Target.isColliding(targets, targets[i])) {
                this.remove(targets[i]);

                targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
                add(targets[i]);
                System.out.println("Fatto!");
            }*/

            //System.out.println();
        }

        /* va messo nel semaforo credo */
        /*
        while(Target.isColliding(targets)) {
            for (int i=0; i<targets.length; i++) {
                this.remove(targets[i]);

                targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
                add(targets[i]);

                //System.out.println();
            }
        }
        */

        init();
    }

    private void init() {
        setLayout(null);
        setSize(1000, 800);
        setResizable(false);
        setTitle("AimTrainer");
        addKeyListener(this);

        add(winTitle);
        add(scoreTxt);

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {System.out.println("cliccato"); }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
            MenuWindow w = new MenuWindow();
            w.setVisible(true);
            System.out.println("cliccato");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {System.out.println("cliccato"); }
}