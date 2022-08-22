public class ArrayQueue<E> implements QueueInterface<E> {
  private E queue[];
  private int front, tail, numItems;
  private static final int DEFAULT_CAPACITY = 64;
  private final E ERROR = null;

  public ArrayQueue() {
    queue = (E[]) new Object[DEFAULT_CAPACITY];
    front = 0;
    tail = DEFAULT_CAPACITY - 1;
    numItems = 0;
  }

  public ArrayQueue(int n) {
    queue = (E[]) new Object[n];
    front = 0;
    tail = n - 1;
    numItems = 0;
  }

  public void enqueue(E newItems) {
    if (isFull()) {
      System.out.println("Queue Full!");
    } else {
      tail = (tail+1) % queue.length;
      queue[tail] = newItems;
      ++numItems;
    }
  }

  public boolean isFull() {
    return queue.length == numItems;
  }

  public E dequeue() {
    if (isEmpty()) {
      return ERROR;
    } else {
      E queueFront = queue[front];
      front = (front+1) % queue.length;
      --numItems;

      return queueFront;
    }
  }

  public E front() {
    if (isEmpty()) {
      return ERROR;
    } else {
      return queue[front];
    }
  }

  public boolean isEmpty() {
    return numItems == 0;
  }

  public void dequeueAll() {
    queue = (E[]) new Object[queue.length];
    front = 0;
    tail = queue.length - 1;
    numItems = 0;
  }
}
