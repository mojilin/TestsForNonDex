import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> myMap = new ConcurrentHashMap<Integer, String>(); 

        for (int i = 0; i < 5; ++i) {
            myMap.put(i, String.valueOf(i));    
        }

        for (int i = 0; i < 5; ++i) {
            System.out.println(myMap.entrySet());
            System.out.println();
        }
    }    
}
