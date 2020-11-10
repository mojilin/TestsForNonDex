
import java.util.Map;
import java.util.HashMap;

public class ClassHashMapTest {

    public static void main(String[] args) {
        Map<Integer, String> myMap = new HashMap<Integer, String>(); 
        for (int i = 0; i < 5; ++i) {
            myMap.put(i, String.valueOf(i));    
        }
        for (int i = 0; i < 5; ++i) {
            System.out.println(myMap.entrySet());
            System.out.println();
        }
    }
}
