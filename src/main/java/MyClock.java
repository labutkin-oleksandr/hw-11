public class MyClock extends Thread {
    private static int counterSec;
    private final static Object monitor = new Object();
    private final static Thread printMyClock = new Thread(() -> {
        try {
            synchronized (monitor) {
                while (true) {
                    System.out.println("Прошло " + counterSec + " секунд со времени старта");
                    monitor.wait(1000);
                    counterSec++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    private final static Thread printInterval = new Thread(() -> {
        try {
            while (true) {
                synchronized (monitor) {
                    if (counterSec != 0 && counterSec % 5 == 0) {
                        System.out.println("Прошло 5 секунд");
                        monitor.wait(5000);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    @Override
    public void run() {
        printMyClock.start();
        printInterval.start();

        synchronized (printMyClock) {
            printMyClock.notify();
        }

        synchronized (printInterval) {
            printInterval.notify();
        }
    }
}
