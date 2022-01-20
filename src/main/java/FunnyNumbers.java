public class FunnyNumbers extends Thread {
    public static volatile int number = 1;
    private final static Object monitor = new Object();

    private static Thread fizz = new Thread(() -> {
        do {
            synchronized (monitor)  {
                try {
                    if(number % 3 == 0 && number % 5 != 0) {
                        System.out.println("fizz");
                        increment();
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    });

    private static Thread buzz = new Thread(() -> {
        do {
            synchronized (monitor) {
                try {
                    if(number % 5 == 0) {
                        System.out.println("buzz");
                        increment();
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    });

    private static Thread fizzbuzz = new Thread(() -> {
        do {
            synchronized (monitor) {
                try {
                    if(number % 5 == 0 && number % 3 == 0) {
                        System.out.println("fizzbuzz");
                        increment();
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } while (true);
    });

    private static Thread numbers = new Thread(() -> {
            do {
                synchronized (monitor) {
                    try {
                        if (number % 5 != 0 && number % 3 != 0) {
                            System.out.println(number);
                            increment();
                        }
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (true);
    });

    @Override
    public void run() {
        fizz.start();
        buzz.start();
        fizzbuzz.start();
        numbers.start();

        do {
                try {
                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        } while (true);
    }

    private static synchronized void increment() {
        number++;
    }
}
