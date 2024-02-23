import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class QuickUnion {

    int[] arr = {};
    int count;

    static long NANO_TO_SECONDS = 1000000000L;

    public static void main(String[] args) {
        int size = StdIn.readInt();

        QuickUnion quickUnion = new QuickUnion(size);

        final long t_start = System.nanoTime();
        while (!StdIn.isEmpty()) {
            int a = StdIn.readInt();
            int b = StdIn.readInt();

            quickUnion.union(a, b);
        }
        final long t_end = System.nanoTime();

        System.out.println(
            "Summary of Quick Union\n" + 
            "Number of components: " + quickUnion.count + "\n" +
            "Connections (array format): " + Arrays.toString(quickUnion.arr)        
        );

        double t_delta = (double)(t_end - t_start)/NANO_TO_SECONDS;
        System.out.println("Time elapsed: " + 
            Double.toString(t_delta) + 
            " seconds"
        );
    }

    public QuickUnion(int size) {
        arr = new int[size];

        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = i;
        }

        count = size;
        return;
    }

    // Create a union between two items to make them connected and part of the same component.
    // For quick-union, the values indicates the index of their parent connection.
    public void union(int a, int b) {
        if (connected(a,b)) 
            return;
        
        arr[find(a)] = b;
        count--;
    }

    // Find the component that an item belongs to.
    // For quick-union, the component representative is the idx that matches its own value. 
    public int find(int a) {
        int val = arr[a];
        
        while (val != arr[val]) {
            val = arr[val];
        }
        
        return val;
    }

    // Check if two items are connected, meaning they are part of the same component.
    // For quick-union, two items are connected if their component representative, or 'root', match.
    public boolean connected(int a, int b) {        
        int root_a = find(a);
        int root_b = find(b);
        if (root_a == root_b) 
            return true;
            
        return false;
    }
}
