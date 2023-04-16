import java.util.concurrent.Semaphore;

public class SemaphoreManager implements Runnable {
    Semaphore s = new Semaphore(1);

    SemaphoreManager(GameWindow win, Target[] targets) {

    }

    @Override
    public void run() {

    }
}
