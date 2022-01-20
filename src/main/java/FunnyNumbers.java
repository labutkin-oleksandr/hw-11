import java.util.ArrayList;

public class FunnyNumbers extends Thread  {
    private static volatile ArrayList<String> numbers = new ArrayList<>();
    private final static Object monitor = new Object();
    private static volatile int count;

    public static void main(String[] args) throws InterruptedException {
        Thread fizz = new Thread(() -> {
            synchronized (monitor)  {
                try {
                    if(count % 3 == 0 && count != 0) {
                        addAmount("fizz");
                        count++;
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread buzz = new Thread(() -> {
            synchronized (monitor) {
                try {
                    if(count % 5 == 0 && count != 0) {
                        addAmount("buzz");
                        count++;
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread fizzbuzz = new Thread(() -> {
            synchronized (monitor) {
                try {
                    if(count % 5 == 0 && count % 3 == 0 && count != 0) {
                        addAmount("fizzbuzz");
                        count++;
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread number = new Thread(() -> {
            synchronized (monitor) {
                try {
                    System.out.println((count % 5 != 0 || count % 3 != 0) || count == 0);
                    if((count % 5 != 0 || count % 3 != 0) || count == 0) {
                        numbers.add(Integer.toString(count));
                        count++;
                        System.out.println(count);
                    }
                    System.out.println(numbers);
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        fizz.start();
        buzz.start();
        fizzbuzz.start();
        number.start();

        for(int i = 0; i < 15; i++) {
            synchronized(monitor) {

                while(count == i) {
                    synchronized(number) {
                        monitor.wait();
                        number.notify();
                    }

                    synchronized(fizzbuzz) {
                        monitor.wait();
                        fizzbuzz.notify();
                    }

                    synchronized(fizz) {
                        monitor.wait();
                        fizz.notify();
                    }

                    synchronized(buzz) {
                        monitor.wait();
                        buzz.notify();
                    }
                }
                System.out.println(count + " count");

            }
        }

        System.out.println(numbers);
    }

    private static synchronized void addAmount(String amount) throws InterruptedException  {
        numbers.add(amount);
    }
}
