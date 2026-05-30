package utility;

import javax.swing.Timer;

public class TableRefresherHelper {

    private static Timer timer;
    private static Runnable savedAction; // ← i-save ang action
    private static int savedDelay;       // ← i-save ang delay

    private TableRefresherHelper() {}
    
    public static void tblRefresher(int delay, Runnable refreshAction) {
        if (timer != null) {
            timer.stop();
        }
        savedAction = refreshAction; // ← i-store para magamit sa startRefresher
        savedDelay = delay;
        timer = new Timer(delay, e -> refreshAction.run());
        timer.start();
    }

    public static void stopRefresher() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // ← stop lang, HUWAG i-null
        }
    }

    public static void startRefresher() {
        if (timer != null && !timer.isRunning()) {
            timer.start(); // ← i-restart ang existing timer
        }
    }
}