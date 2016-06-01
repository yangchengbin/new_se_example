import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class MyTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 2000, 2000);
        while (true) {
            try {
                int ch = System.in.read();
                if (ch - 'c' == 0) {
                    timer.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }
}
