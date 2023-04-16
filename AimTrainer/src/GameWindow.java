import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GameWindow extends JFrame {
    private JPanel container;

    JButton quitBtn;

    JLabel winTitle;
    JLabel scoreTxt;
    Target[] targets;
    Color[] targetsColors;

    Random r = new Random();
    Semaphore semaforo = new Semaphore(1);

    GameWindow() {
        Storage.points = 0;

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

        quitBtn = new JButton("QUIT");
        quitBtn.setBorderPainted(false);
        quitBtn.setBackground(Color.decode("#0CF574"));
        quitBtn.setForeground(Color.decode("#231F20"));
        quitBtn.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        quitBtn.setFocusPainted(false);
        quitBtn.addActionListener(gameMenu);
        quitBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                quitBtn.setBackground(Color.decode("#E9D758"));
                quitBtn.setForeground(Color.decode("#231F20"));
                quitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent evt) {
                //startButton.setBackground(UIManager.getColor("control"));
                quitBtn.setBackground(Color.decode("#0CF574"));
                quitBtn.setForeground(Color.decode("#231F20"));
                quitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        quitBtn.setSize(120, 50);
        quitBtn.setLocation(1000-140, 5);

        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(dim, dim, targetsColors[i], 50*(targets.length-i), scoreTxt, semaforo);

            //System.out.println();
        }
        // place target non-colliding
        for (int i=0; i<targets.length; i++) {
            targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
            add(targets[i]);
            targets[i].start();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setResizable(false);
        setTitle("AimTrainer");

        add(winTitle);
        add(scoreTxt);
        add(quitBtn);

        setVisible(true);
    }

    ActionListener gameMenu = new ActionListener() {
        public void actionPerformed(ActionEvent action) {
            dispose();
            MenuWindow w = new MenuWindow();
            w.setVisible(true);
        }
    };
}