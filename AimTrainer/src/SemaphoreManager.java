import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreManager{
    Semaphore verde = new Semaphore(1);
    Semaphore giallo = new Semaphore(1);
    Semaphore rosso = new Semaphore(1);   
    GameWindow gw;
    Random r = new Random();
    Target[] targets = new Target[3];
    Color[] targetsColors;
    String[] targetsColori;
    SemaphoreManager(GameWindow gw) throws Exception{
        this.gw = gw;
        giallo.acquire();
        rosso.acquire();
        targetsColors = new Color[] {Color.decode("#bb4430"), Color.decode("#E9D758"), Color.decode("#0CF574")};
        targetsColori = new String[]{"rosso", "giallo", "verde"};
        for (int i=0; i<targets.length; i++) {
            int dim = (i+1)*100;
            if (i == 2) dim -= 50;
            targets[i] = new Target(dim, dim,targetsColori[i], targetsColors[i], 50*(targets.length-i), gw.scoreTxt, this);

            //System.out.println();
        }
        for (int i=0; i<targets.length; i++) {
            targets[i].changeLocation(r.nextInt(10, 800), r.nextInt(70, 600));
            targets[i].start();
        }
        /* targets[0].bersaglio.addActionListener(clickedRosso);
        targets[1].bersaglio.addActionListener(clickedGiallo);
        targets[2].bersaglio.addActionListener(clickedVerde); */
    }
    /* ActionListener clickedVerde = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            giallo.release();

        }
    };
    ActionListener clickedGiallo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            rosso.release();
        }
    };
    ActionListener clickedRosso = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            verde.release();
        }
    }; */
    public void appari(String colore, Target t){
        try {
            if (colore.equals("verde")){ 
                verde.acquire();
                gw.add(targets[2].bersaglio);
                gw.remove(targets[0].bersaglio);
                System.out.println("verde");
                Thread.sleep(3000);
                giallo.release();
            }
            else{ 
                if (colore.equals("giallo")){ 
                    giallo.acquire();
                    gw.add(targets[1].bersaglio);
                    gw.remove(targets[2].bersaglio);
                    System.out.println("giallo");
                    Thread.sleep(3000);
                    rosso.release();
                }
                else
                {  
                rosso.acquire();
                gw.add(targets[0].bersaglio);
                System.out.println("rosso");
                gw.remove(targets[1].bersaglio);
                Thread.sleep(3000);
                verde.release();
                }
            }
        } catch (Exception e) {
            
        }
    }
}
