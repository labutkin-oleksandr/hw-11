public class Main extends Thread {
    public static void main(String[] args) throws InterruptedException {
        new MyClock().start();

        FunnyNumbers funnyNumbersNew = new FunnyNumbers();
        funnyNumbersNew.start();
    }
}
