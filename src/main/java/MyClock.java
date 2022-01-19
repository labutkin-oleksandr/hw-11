public class MyClock extends Thread {
    private static int counterSec;

    @Override
    public void run() {
        Thread printMyClock = new Thread(() -> {
            try {
                do {
                    System.out.println("Прошло " + counterSec + " секунд со времени старта");
                    counterSec++;
                    Thread.sleep(1000);
                } while (true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printMyClock.start();

        Thread printInterval = new Thread(() -> {
            try {
                do {
                    if(counterSec != 0 && counterSec % 5 == 0) {
                        System.out.println("Прошло 5 секунд");
                        Thread.sleep(5000);
                    } else {
                        Thread.sleep(5000);
                    }
                } while (true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printInterval.start();
    }
}
