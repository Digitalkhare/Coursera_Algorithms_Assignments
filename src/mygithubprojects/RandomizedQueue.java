package mygithubprojects;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author osomokhare
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int front;
    private int rear;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return rear - front;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(2 * items.length);
        }
        items[rear % items.length] = item;
        rear++;

    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size() == items.length / 4) {
            resize(items.length / 2);
        }
        //Item item = items[front];
        int randomIndex = StdRandom.uniform(front, rear);
        Item frontItem = items[front % items.length];
        Item item = items[randomIndex % items.length];
        items[randomIndex % items.length] = frontItem;
        items[front % items.length] = null;
        front++;
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(front, rear);
        return items[randomIndex];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int current = front;

            @Override
            public boolean hasNext() {
                return rear - current > 0;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int uniform = StdRandom.uniform(current, rear);
                Item tempItem = items[current];
                Item item = items[uniform];
                items[current] = item;
                items[uniform] = tempItem;
                current++;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            copy[i] = items[(front + i) % items.length];

        }
        rear -= front; // ensures range remains the same from before
        front = 0;
        items = copy;
    }

    private boolean isFull() {
        return size() == items.length;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> deque = new RandomizedQueue<>();
        deque.enqueue("hello world1");
        deque.enqueue("hello world2");
        deque.enqueue("hello world3");
        deque.enqueue("hello world4");
        deque.enqueue("hello world5");
        StdOut.println(deque.size());
        StdOut.println(deque.dequeue());
        StdOut.println(deque.sample());
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());

        }
    }

}
