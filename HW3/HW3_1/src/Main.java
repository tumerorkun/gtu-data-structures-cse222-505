public class Main {

    public static void main(String[] args) {
        CountingComponents cc = new CountingComponents(args[0]);
        System.out.println("MATRIX: ");
        cc.printMatrix();
        System.out.print("COMPONENT COUNT: ");
        System.out.println(cc.getComponentCount());
    }
}
