public class ArrayList<E> implements ListInterface<E> {
  private E item[];
  private int numItems;
  private static final int DEFAULT_CAPACITY = 64;

  public ArrayList() {
    item = (E[]) new Object[DEFAULT_CAPACITY];
    numItems = 0;
  }

  public ArrayList(int n) {
    item = (E[]) new Object[n];
    numItems = 0;
  }

  public void add(int idx, E x) {
    if (numItems >= item.length || idx < 0 || idx > numItems) {
      // throw error
    } else {
      for (int i = numItems - 1; i > idx; i--) {
        item[i + 1] = item[i];
      }

      item[idx] = x;
      numItems++;
    }
  }

  public void append(E x) {
    if (numItems >= item.length) {
      // throw error
    } else {
      item[numItems++] = x;
    }
  }

  public E remove(int idx) {
    if (isEmpty() || idx < 0 || idx > numItems - 1) {
      return null;
    } else {
      E tmp = item[idx];

      for (int i = idx; i <= numItems - 2; i++) {
        item[i] = item[i + 1];
      }

      numItems--;

      return tmp;
    }
  }

  public boolean removeItem(E x) {
    int k = 0;

    while (k < numItems && ((Comparable)item[k]).compareTo(x) != 0) {
      k++;
    }

    if (k == numItems) {
      return false;
    } else {
      for (int i = k; i <= numItems - 2; i++) {
        item[i] = item[i + 1];
      }

      numItems--;

      return true;
    }
  }

  public E get(int idx) {
    if (idx >= 0 && idx <= numItems - 1) {
      return item[idx];
    } else {
      return null;
    }
  }

  public void set(int idx, E x) {
    if (idx >= 0 && idx <= numItems - 1) {
      item[idx] = x;
    } else {
      // throw error
    }
  }

  public int indexOf(E x) {
    int i = 0;
    for (i = 0; i < numItems; i++) {
      if (((Comparable)item[i]).compareTo(x) == 0) {
        return i;
      }
    }

    return -12345;
  }

  public int len() {
    return numItems;
  }

  public boolean isEmpty() {
    return numItems == 0;
  }

  public void clear() {
    item = (E[]) new Object[item.length];

    numItems = 0;
  }
}
