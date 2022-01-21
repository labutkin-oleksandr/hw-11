public class Main extends Thread {
    public static void main(String[] args) {
        new MyClock().start();

        FunnyNumbers funnyNumbersNew = new FunnyNumbers();
        funnyNumbersNew.start();
    }
}
