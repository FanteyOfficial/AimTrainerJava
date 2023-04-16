import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class SemaphoreManager{
    Semaphore verde = new Semaphore(1);
    Semaphore giallo = new Semaphore(1);
    Semaphore rosso = new Semaphore(1);   
    GameWindow gw;
    Random r = new Random();
    Target[] targets = new Target[3];
    Color[] targetsColors;
    String[] targetsColori;

    Thread tempo;
    SemaphoreManager(GameWindow gw) throws Exception{
        this.gw = gw;
        giallo.acquire();
        rosso.acquire();
        targetsColors = new Color[] {Color.decode("#bb4430"), Color.decode("#E9D758"), Color.decode("#0CF574")};
        targetsColori = new String[]{"rosso", "giallo", "verde"};
        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(dim, dim,targetsColori[i], targetsColors[i], 50*(targets.length-i), gw.scoreTxt, this, gw);

            //System.out.println();
        }
        for (int i=0; i<targets.length; i++) {
            targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
            targets[i].start();
        }
        targets[0].bersaglio.addActionListener(clickedRosso);
        targets[1].bersaglio.addActionListener(clickedGiallo);
        targets[2].bersaglio.addActionListener(clickedVerde);
    }
    ActionListener clickedVerde = evt -> {
        tempo.interrupt();
        gw.remove(targets[0].bersaglio);
        giallo.release();
        targets[2].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
        System.out.println("click verde");

    };
    ActionListener clickedGiallo = evt -> {
        tempo.interrupt();
        gw.remove(targets[2].bersaglio);
        rosso.release();
        targets[1].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
        System.out.println("click giallo");

    };
    ActionListener clickedRosso = evt -> {
        tempo.interrupt();
        gw.remove(targets[1].bersaglio);
        verde.release();
        targets[0].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
        System.out.println("click rosso");

    };
    public void appari(String colore){
        try {
            if (colore.equals("verde")){ 
                verde.acquire();
                gw.add(targets[2].bersaglio);
                //gw.remove(targets[0].bersaglio);
                System.out.println("verde");
                //Thread.sleep(3000);
                //giallo.release();
                tempo = new ScadenzaTarget(3000, giallo, gw, targets[2].bersaglio, targets[2]);
                tempo.start();
                System.out.println("tempo verde");
            }
            else{ 
                if (colore.equals("giallo")){ 
                    giallo.acquire();
                    gw.add(targets[1].bersaglio);
                    //gw.remove(targets[2].bersaglio);
                    System.out.println("giallo");
                    //Thread.sleep(3000);
                    //rosso.release();
                    tempo = new ScadenzaTarget(3000, rosso, gw, targets[1].bersaglio, targets[1]);
                    tempo.start();
                    System.out.println("tempo giallo");
                }
                else
                {  
                    rosso.acquire();
                    gw.add(targets[0].bersaglio);
                    System.out.println("rosso");
                    //gw.remove(targets[1].bersaglio);
                    //Thread.sleep(3000);
                    //verde.release();
                    tempo = new ScadenzaTarget(3000, verde, gw, targets[0].bersaglio, targets[0]);
                    tempo.start();
                    System.out.println("tempo rosso");
                }
            }
        } catch (Exception e) {
            System.out.println("Eccezione");
        }
    }
}

class ScadenzaTarget extends Thread {
    int tempo;
    Semaphore s;
    JButton t;
    JFrame win;
    Random r = new Random();
    Target target;
    ScadenzaTarget(int timeSec, Semaphore s, JFrame win, JButton t, Target target) {
        this.tempo = timeSec;
        this.s = s;
        this.t = t;
        this.win = win;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            sleep(tempo);
            win.remove(t);
            target.changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
            s.release();
        } catch (InterruptedException e) {

        }
    }
}
