import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    public Deque() {}                           // construct an empty deque
    public boolean isEmpty() {
      return false;
    }                 // is the deque empty?
    public int size() {
      return 0;
    }                        // return the number of items on the deque
    public void addFirst(Item item) {}          // add the item to the front
    public void addLast(Item item) {}           // add the item to the end
    public Item removeFirst() {
      return null;
    }                // remove and return the item from the front
    public Item removeLast() {
      return null;
    }                 // remove and return the item from the end
    public Iterator<Item> iterator() {
      return null;
    }         // return an iterator over items in order from front to end
    public static void main(String[] args) {}   // unit testing (optional)
}