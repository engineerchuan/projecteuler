import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Item[] array;
    private int capacity = 1;
    private int first = 0;
    private int last = 0;
    
    public Deque() {
        array = (Item[]) new Object[capacity];
    }                           // construct an empty deque
    public boolean isEmpty() {
        return first == last;
    }                 // is the deque empty?
    public int size() {
        return last - first;
    }                        // return the number of items on the deque
    public void addFirst(Item item) {
        
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (0 == first) {
            changeCapacity(2 * capacity);
        }
        first -= 1;
        array[first] = item;
    }
    
    private void changeCapacity(int newCapacity) {
        // StdOut.println("changing capacity to " + newCapacity);
        Item[] newArray = (Item[]) new Object[newCapacity];
        int newFirst = (newCapacity - last + first + 1)/2;
        
        // StdOut.println("first changed from " + first + " to " + newFirst);
        // copy over all the entries
        for (int i = 0; i < last - first; ++i) {
            newArray[newFirst +i] = array[first+i];
        }
        array = newArray;
        int newLast = newFirst + last - first;
        first = newFirst;
        // StdOut.println("last changed from " + last + " to " + newLast);
        last = newLast;
        capacity = newCapacity;
    }
    
    public void addLast(Item item) {
        
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (capacity == last) {
            changeCapacity(2 * capacity);
        }
        
        array[last] = item;
        last += 1;
    }           // add the item to the end
    
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.size() <= capacity/4+1) {
            changeCapacity(capacity/2);
        }
       
        Item returnedFirst = array[first];
        array[first] = null;
        first += 1;
        
        return returnedFirst;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.size() <= capacity/4+1) {
            changeCapacity(capacity/2);
        }
        
        Item returnedLast = array[last-1];
        array[last-1] = null;
        last -= 1;
        return returnedLast;
    }                 // remove and return the item from the end
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }         // return an iterator over items in order from front to end
    
    private class DequeIterator implements Iterator<Item> {
        private int current = first;
        public boolean hasNext() {
            return (current < last);
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (hasNext()) {
                Item toReturn = array[current];
                current += 1;
                return toReturn;
            }
            else {
                throw new java.util.NoSuchElementException();
            }
            
        }
    }
    
    private void printArray() {
        StdOut.println("---");
        for (int i = 0; i < array.length; ++i) {
            StdOut.println(array[i]);
        }
        StdOut.println("---");
    }
    
    public static void main(String[] args) {
        Deque<String> a = new Deque<String>();
        a.addFirst("hi");
        a.addFirst("whoa");
        a.addLast("3");
        a.addFirst("bye");
        a.addLast("5");
        a.addLast("6");
        a.addLast("test");
        a.printArray();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        
        a.printArray();
        for (String s : a) {
            StdOut.println(s);
        }
    }   // unit testing (optional)
}