public class Main {

    public static void main(String[] args) {
        CountingComponents cc = new CountingComponents(args[0]);
        System.out.println("MATRIX: ");
        cc.printMatrix(0);
        System.out.print("\nCOMPONENT COUNT: ");
        System.out.println(cc.getComponentCount());
        System.out.println("\nCOMPONENTS: ");
        cc.printMatrix(1);
    }
}
