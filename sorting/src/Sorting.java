public class Sorting {
  int A[];

  public Sorting(int B[]) {
    A = B;
  }

  private int theLargest(int last) {
    int largest = 0;

    for (int i = 0; i <= last; i++) {
      if (A[i] > A[largest]) largest = i;
    }

    return largest;
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
}
