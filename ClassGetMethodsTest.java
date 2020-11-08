class ClassGetMethodsTest {
    public int getA() {
        return a;
    }

    public boolean getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    private int a;
    private boolean b;
    private String c;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(java.util.Arrays.toString(ClassGetDeclaredFieldsTest.class.getMethods()));
            System.out.println();
        }
    }
}