package mygithubprojects;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author osomokhare
 */
public class Deque<Item> implements Iterable<Item> { //linkedlist implementation

    private Node first, last = null;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null || last == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldfirst = first;
        first = new Node();
        if (isEmpty()) {
            last = first;
        }
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        if (isEmpty()) {
            first = last;
        }
        last.item = item;
        last.previous = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        }
        size++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        size--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
        }
        size--;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        return iterator;
    }

    private class Node {

        Item item;
        Node next;
        Node previous;

    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("hello world1");
        deque.addFirst("hello world2");
        deque.addFirst("hello world3");
        deque.addFirst("hello world4");
        deque.addLast("hello world5");
        deque.addLast("hello world6");
        deque.addLast("hello world7");
        deque.addLast("hello world8");
        deque.addLast("hello world9");
        StdOut.println(deque.size());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());

        }
    }

}
