public class App {
    public static void main(String[] args) throws Exception {
        ArrayQueue<String> s = new ArrayQueue<>();

        s.enqueue("test 1");
        s.enqueue("test 2");
        s.enqueue("test 3");
        s.dequeue();

        LinkedQueue<String> q = new LinkedQueue<>();

        q.enqueue("test 1");
        q.enqueue("test 2");
        q.enqueue("test 3");
        q.dequeue();
    }
}
