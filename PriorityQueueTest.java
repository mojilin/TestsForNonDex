import java.util.Queue;
import java.util.PriorityQueue;

class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<String> pq = new PriorityQueue<>();

        pq.offer("one");
        pq.offer("two");
        pq.offer("three");
    
        for (int i = 0; i < 5; ++i) {
            System.out.println(pq.toString());
            System.out.println();
        }
    }
}