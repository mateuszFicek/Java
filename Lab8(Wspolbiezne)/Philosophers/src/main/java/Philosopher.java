public class Philosopher implements Runnable {
    private Object rightFork;
    private Object leftFork;

    public Philosopher(Object leftFork, Object rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void action(String typeOfAction) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + typeOfAction);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // thinking
                action(System.nanoTime() + ": Thinking");
                synchronized (leftFork) {
                    action(System.nanoTime() + ": Picked up left fork");
                    synchronized (rightFork) {
                        // eating
                        action(System.nanoTime() + ": Picked up right fork - eating");
                        action(System.nanoTime() + ": Put down right fork");
                    }
                    // Back to thinking
                    action(System.nanoTime() + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
