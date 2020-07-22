package mygithubprojects;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int readInt = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < readInt; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
    
}
