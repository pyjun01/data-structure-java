public class App {
    public static void main(String[] args) throws Exception {
        BinarySearchTree bst1 = new BinarySearchTree();

        bst1.insert(10);
        bst1.insert(20);
        bst1.insert(5);
        bst1.insert(80);
        bst1.insert(90);
        bst1.insert(75);
        bst1.insert(30);
        bst1.insert(77);
        bst1.insert(15);
        bst1.insert(40);

        Integer del1 = 20;
        bst1.delete(del1);

        Integer del2 = 20;
        bst1.delete(del2);
    }
}
