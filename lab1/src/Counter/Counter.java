package Counter;

public class Counter {
    private final Object lock = new Object();
    private int counter;

    public Counter(int counter) {
        this.counter = counter;
    }

    public void syncObjectIncrement() {
        synchronized (lock) {
            increment();
        }
    }

    public void syncObjectDecrement() {
        synchronized (lock) {
            decrement();
        }
    }

    public void syncBlockIncrement() {
        synchronized (this) {
            increment();
        }
    }

    public void syncBlockDecrement() {
        synchronized (this) {
            decrement();
        }
    }

    public synchronized void syncMethodIncrement() {
        increment();
    }

    public synchronized void syncMethodDecrement() {
        decrement();
    }

    public void increment() {
        counter++;
    }

    public void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}
