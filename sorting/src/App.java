import java.util.Arrays;

public class App {
    static final int NUM_SCALE = 10000;
    static final int SIZE = 10000;

    public static void prepare(int A[]) {
        for (int i = 0; i < A.length; i++) {
            A[i]= (int)(NUM_SCALE * Math.random());
        }
    }

    public static void main(String[] args) throws Exception {
        int[] A = new int[SIZE];
        prepare(A);

        Sorting s = new Sorting(A);

        s.quickSort();

        System.out.println(Arrays.toString(A));
    }
}
