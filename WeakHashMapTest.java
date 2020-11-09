import java.util.Map;
import java.util.WeakHashMap;

class WeakHashMapTest {
    private Map<Integer, String> myMap = new WeakHashMap<Integer, String>(); 

    private void setMyMap() {
        for (int i = 0; i < 5; ++i) {
            myMap.put(i, String.valueOf(i));    
        }
    }

    public static void main(String[] args) {
        WeakHashMapTest myTest = new WeakHashMapTest();

        myTest.setMyMap();
        for (int i = 0; i < 5; ++i) {
            System.out.println(myTest.myMap.entrySet());
            System.out.println();
        }
    }
}