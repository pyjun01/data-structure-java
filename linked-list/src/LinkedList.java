public class LinkedList<E> implements ListInterface<E> {
  private Node<E> head;
  private int numItems;

  public LinkedList() {
    numItems = 0;
    head = new Node<>(null, null);
  }

  public void add(int index, E item) {
    if (index >= 0 && index <= numItems) {
      Node<E> prevNode = getNode(index - 1);
      Node<E> newNode = new Node<>(item, prevNode.next);
      prevNode.next = newNode;
      numItems++;
    }
  }

  public void append(E item) {
    Node<E> prevNode = head;
    while (prevNode.next != null) {
      prevNode = prevNode.next;
    }
    Node<E> newNode = new Node<>(item, null);
    prevNode.next = newNode;
    numItems++;
  }

  public E remove(int index) {
    if (index >= 0 && index <= numItems - 1) {
      Node<E> prevNode= getNode(index - 1);
      Node<E> currNode = prevNode.next;
      prevNode.next = currNode.next;
      numItems--;
      return currNode.item;
    } else {
      return null;
    }
  }

  public boolean removeItem(E x) {
    Node<E> prevNode, currNode = head;
    for (int i = 0; i < numItems - 1; i++) {
      prevNode = currNode;
      currNode = currNode.next;
      if (currNode.item.equals(x)) {
        prevNode.next = currNode.next;
        numItems--;
        return true;
      }
    }
    return false;
  }

  public E get(int index) {
    if (index >= 0 && index <= numItems - 1) {
      return getNode(index).item;
    } else {
      return null;
    }
  }

  public int len() {
    return numItems;
  }

  public boolean isEmpty() {
    return numItems == 0;
  }

  public void clear() {
    numItems = 0;

    head = new Node<>(null, null);
  }

  public Node<E> getNode(int index) {
    if (index >= -1 && index <= numItems - 1) {
      Node<E> currNode = head;
      for (int i = 0; i < index; i++) {
        currNode = currNode.next;
      }

      return currNode;
    } else {
      return null;
    }
  }

  public void set(int index, E x) {
    if (index >= 0 && index <= numItems - 1) {
      getNode(index).item = x;
    }
  }

  public final int NOT_FOUND = -12345;
  public int indexOf(E x) {
    Node<E> currNode = head;
    int i;
    for (i = 0; i < numItems; i++) {
      currNode = currNode.next;
      if (currNode.item.equals(x)) {
        return i;
      }
    }

    return NOT_FOUND;
  }
}
