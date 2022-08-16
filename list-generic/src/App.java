public class App {
    public static void main(String[] args) throws Exception {
        IntegerArrayList list = new IntegerArrayList();

        list.add(0, 300);
        list.add(0, 200);
        list.add(0, 100);
        System.out.println(list);
        list.append(500);
        list.append(600);
        System.out.println(list);
        list.add(3, 250);
        list.add(1, 50);
        list.add(0, 10);
        list.append(700);
        list.remove(1);
        list.removeItem(600);
        System.out.println(list);
    }
}
