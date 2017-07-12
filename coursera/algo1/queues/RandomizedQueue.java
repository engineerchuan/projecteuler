import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] array;
    private int capacity = 1;
    private int last = 0;
    
    public RandomizedQueue() {
        array = (Item[]) new Object[capacity];
    }                           // construct an empty deque
    public boolean isEmpty() {
        return 0 == last;
    }                 // is the deque empty?
    public int size() {
        return last;
    }                        // return the number of items on the deque
    
    private void changeCapacity(int newCapacity) {
        // StdOut.println("changing capacity to " + newCapacity);
        Item[] newArray = (Item[]) new Object[newCapacity];
        
        // StdOut.println("first changed from " + first + " to " + newFirst);
        // copy over all the entries
        for (int i = 0; i < last; ++i) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity = newCapacity;
    }
    
    public void enqueue(Item item) {
        
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (capacity < (last+1)) {
            changeCapacity(2 * capacity);
        }
        
        array[last] = item;
        last += 1;
    }           // add the item to the end
    
    private Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.size() <= capacity/4+1 && capacity >= 2) {
            changeCapacity(capacity/2);
        }
        
        Item returnedLast = array[last-1];
        array[last-1] = null;
        last -= 1;
        return returnedLast;
    }
    
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int randIndex = StdRandom.uniform(0, last);
        return array[randIndex];
    }
    
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        // select a random item, swap it, then dequeue
        int randIndex = StdRandom.uniform(0, last);
        Item tmp = array[randIndex];
        array[randIndex] = array[last-1];
        array[last-1] = tmp;
        return this.removeLast();
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }         // return an iterator over items in order from front to end
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] randomized;
        private int current = 0;
        
        public RandomizedQueueIterator() {
            randomized = (Item[]) new Object[last];
            for (int i = 0; i < last; ++i) {
                randomized[i] = array[i];
            }
            StdRandom.shuffle(randomized);
        }
        public boolean hasNext() {
            return (current < randomized.length);
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (hasNext()) {
                Item toReturn = randomized[current];
                current += 1;
                return toReturn;
            }
            else {
                throw new NoSuchElementException();
            }
            
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<String> a = new RandomizedQueue<String>();
        a.enqueue("1");
        a.enqueue("2");
        a.enqueue("3");
        a.enqueue("4");
        
        for (String s : a) {
            StdOut.println(s);
        }
    }   // unit testing (optional)
}