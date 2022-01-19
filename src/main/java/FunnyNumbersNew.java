import java.util.ArrayList;

public class FunnyNumbersNew implements Runnable {
    public static volatile ArrayList<String> numbers = new ArrayList<>();
    private static final Object monitor = new Object();
    public static volatile int count;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkNumber() throws InterruptedException {
        synchronized (monitor) {
            System.out.println(count + " count");
            try {
                if((count % 5 != 0 || count % 3 != 0) || count == 0) {
                    numbers.add(Integer.toString(count));
                    count++;
                    System.out.println(count + " count");
                }
                System.out.println(Thread.currentThread().getName());
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkThree() throws InterruptedException {
        synchronized (monitor) {
            System.out.println(count + " count");
            try {
                if(count % 3 == 0 && count != 0) {
                    numbers.add("fizz");
                    count++;
                }
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkFive() throws InterruptedException {
        synchronized (monitor) {
            try {
                if(count % 5 == 0 && count != 0) {
                    numbers.add("buzz");
                    count++;
                }
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkThreeFive() throws InterruptedException {
        synchronized (monitor) {
            try {
                if(count % 5 == 0 && count % 3 == 0 && count != 0) {
                    numbers.add("fizzbuzz");
                    count++;
                }
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
