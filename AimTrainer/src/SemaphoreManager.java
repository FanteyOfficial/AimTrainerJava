import javax.swing.*;
import java.util.concurrent.Semaphore;

public class SemaphoreManager implements Runnable {
    Semaphore s = new Semaphore(1);

    SemaphoreManager(Window win, Target[] targets) {

    }

    @Override
    public void run() {

    }
}
