public class ClassGetDeclaredFieldsTest {
    public int i;
    public boolean b;
    public char c;
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(java.util.Arrays.toString(ClassGetDeclaredFieldsTest.class.getDeclaredFields()));
        }
    }
}
