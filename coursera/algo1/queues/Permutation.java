import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            String c = StdIn.readString();
            r.enqueue(c);
        }
        
        Iterator<String> i = r.iterator();
        while (k > 0) {
            StdOut.println(i.next());
            k -= 1;
        }
    }
}