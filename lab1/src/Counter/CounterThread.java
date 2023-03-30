package Counter;

public class CounterThread extends Thread {
    Counter counter;
    boolean increment;

    public CounterThread(Counter counter, boolean increment) {
        this.counter = counter;
        this.increment = increment;
    }

    @Override
    public void run() {
        if (increment) {
            for (int i = 0; i < 100000; i++) {
//                counter.syncObjectIncrement();
//                counter.syncBlockIncrement();
                counter.syncMethodIncrement();
            }
        } else {
            for (int i = 0; i < 100000; i++) {
//                counter.syncObjectDecrement();
//                counter.syncBlockDecrement();
                counter.syncMethodDecrement();
            }
        }
    }
}
