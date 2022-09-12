public class Sorting {
  int A[];

  public Sorting(int B[]) {
    A = B;
  }

  // 선택 정렬
  public void selectionSort() {
    int k; int tmp;

    for (int i = A.length - 1; i >= 1; i--) {
      k = theLargest(i);

      tmp = A[k];
      A[k] = A[i];
      A[i] = tmp;
    }
  }

  private int theLargest(int last) {
    int largest = 0;

    for (int i = 0; i <= last; i++) {
      if (A[i] > A[largest]) largest = i;
    }

    return largest;
  }

  // 버블 정렬
  public void bubbleSort() {
    int tmp = 0;
    boolean swapped;

    for (int last = A.length - 1; last > 1; last--) {
      swapped = false;

      for (int i = 0; i < last; i++) {
        if (A[i] > A[i + 1]) {
          tmp = A[i];
          A[i] = A[i + 1];
          A[i + 1] = tmp;

          swapped = true;
        }
      }

      if (swapped == false) {
        break;
      }
    }
  }

  // 삽입 정렬
  public void insertionSort() { 
    for (int i = 1; i < A.length - 1; i++) {
      int loc = i - 1;
      int newItem = A[i];

      while (loc >= 0 && newItem < A[loc]) {
        A[loc + 1] = A[loc];
        loc--;
      }

      A[loc + 1] = newItem;
    }
  }

  // 병합 정렬
  public void mergeSort() {
    int[] tmp = new int[A.length];

    mSort(0, A.length - 1, tmp);
  }

  private void mSort(int begin, int end, int[] B) {
    if (begin < end) {
      int middle = (begin + end) / 2;

      mSort(begin, middle, B);
      mSort(middle + 1, end, B);

      merge(begin, middle, end, B);
    }
  }

  private void merge(int begin, int middle, int end, int[] B) {
    int i = begin;
    int j = middle + 1;
    int bIdx = 0;

    while (i <= middle && j <= end) {
      B[bIdx++] = A[i] <= A[j] ? A[i++] : A[j++];
    }

    while (i <= middle) {
      B[bIdx++] = A[i++];
    }

    while (j <= end) {
      B[bIdx++] = A[j++];
    }

    i = begin;
    bIdx = 0;

    while (i <= end) {
      A[i++] = B[bIdx++]; // 정렬 결과를 A에 저장한다.
    }
  }

  // 퀵 정렬
  public void quickSort() {
    qSort(0, A.length - 1);
  }

  private void qSort(int begin, int end) {
    if (begin < end) {
      int q = partition(begin, end);

      qSort(begin, q - 1);
      qSort(q + 1, end);
    }
  }
  
  private int partition(int p, int r) {
    int x = A[r];
    int i = p - 1;
    int tmp;

    for (int j = p; j <= r - 1; j++) {
      if (A[j] <= x) { // 기준 아이템 보다 작으면 앞(i)으로 옮김
        i++;
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
      }
    }

    // 마지막 아이템을 i + 1로 옮김 (i 보다 큰 index의 원소들은 x 보다 큰 값이므로)
    tmp = A[i + 1];
    A[i + 1] = A[r];
    A[r] = tmp;

    return i + 1;
  }

  // 힙 정렬
  public void heapSort() {
    buildHeap();

    int tmp;

    for (int i = A.length - 1; i >= 1; i--) {
      tmp = A[0];
      A[0] = A[i];
      A[i] = tmp;

      percolateDown(0, i - 1);
    }
  }

  public void buildHeap() {
    if (A.length >= 2) {
      for (int i = (A.length - 2) / 2; i >= 0; i--) {
        percolateDown(i, A.length - 1);
      }
    }
  }

  private void percolateDown(int i, int n) {
    int leftChild = 2 * i + 1;
    int rightChild = 2 * i + 2;

    if (leftChild <= n) {
      if (rightChild <= n && A[leftChild] < A[rightChild]) {
        leftChild = rightChild;
      }

      if (A[i] < A[leftChild]) {
        int tmp = A[i];
        A[i] = A[leftChild];
        A[leftChild] = tmp;
        percolateDown(leftChild, n);
      }
    }
  }

  // 셸 정렬
  public void shellSort() {
    for (int h = A.length / 7; h > 5; h = h / 5 - 1) {
      for (int k = 0; k <= h - 1; k++) {
        stepInsertionSort(k, h);
      }
    }

    stepInsertionSort(0, 1);
  }

  private void stepInsertionSort(int k, int h) {
    int j, insItem;

    for (int i = k + h; i <= A.length - 1; i += h) {
      insItem = A[i];

      for (j = i - h; j >= 0 && A[j] > insItem; j -= h) {
        A[j + h] = A[j];
      }

      A[j + h] = insItem;
    }
  }
}
