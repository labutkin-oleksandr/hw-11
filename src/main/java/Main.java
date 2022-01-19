public class Main extends Thread {
    public static void main(String[] args) throws InterruptedException {
//        new MyClock().start();

        FunnyNumbersNew funnyNumbersNew = new FunnyNumbersNew();
        Thread fizz = new Thread(funnyNumbersNew);
        Thread buzz = new Thread(funnyNumbersNew);
        Thread fizzbuzz = new Thread(funnyNumbersNew);
        Thread number = new Thread(funnyNumbersNew);

        fizz.start();
        buzz.start();
        fizzbuzz.start();
        number.start();

        for(int i = 0; i < 15; i++) {

                while(funnyNumbersNew.count == i) {
                    synchronized(number) {
                        number.notify();
                        funnyNumbersNew.checkNumber();
                    }

                    synchronized(fizzbuzz) {
                        fizzbuzz.notify();
                        funnyNumbersNew.checkThreeFive();
                    }

                    synchronized(fizz) {
                        fizz.notify();
                        funnyNumbersNew.checkThree();
                    }

                    synchronized(buzz) {
                        buzz.notify();
                        funnyNumbersNew.checkFive();
                    }
                }
        }

        System.out.println(funnyNumbersNew.numbers);
    }
}
