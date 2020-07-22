package mygithubprojects;


import edu.princeton.cs.algs4.StdOut;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author osomo
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    // creates n-by-n grid, with all sites initially blocked
    private int[][] percolationIntGrid;
    private int[] percolationIntGridSize;
    private boolean[][] percolationbooleanGrid;
    private int numberOfOpenSites;
    private final int size;

    public Percolation(int n) {
        int number = 0;
        int index = 0;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n + 1;
        percolationIntGrid = new int[n + 1][n + 1];// initialise percolationIntGrid
        percolationIntGridSize = new int[(n + 1) * (n + 1)];// initialise percolationIntGrid
        for (int[] percolationIntGrid1 : percolationIntGrid) {
            for (int column = 0; column < percolationIntGrid1.length; column++) {
                percolationIntGrid1[column] = number++; //assign unique numbers to grid
                percolationIntGridSize[index++] = 1;
                // System.out.println(Arrays.toString(percolationIntGrid1));
            }
            //initialise percolationbooleanGrid
            // System.out.println(Arrays.toString(percolationIntGrid1));
        }

        percolationbooleanGrid = new boolean[n + 1][n + 1];
    }

    private int root(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IllegalArgumentException();
        }
        int value;
        while (percolationIntGrid[row][col] != (size * row + col)) {
            value = percolationIntGrid[row][col];
            row = value / size;
            col = value % size;

        }
        return percolationIntGrid[row][col];
    }

    public void open(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IllegalArgumentException();
        }
        if (!percolationbooleanGrid[row][col]) {
            percolationbooleanGrid[row][col] = true;
            union(row, col);
            //  percolationbooleanGrid[row][col] = true;
            numberOfOpenSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IllegalArgumentException();
        }
        return percolationbooleanGrid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IllegalArgumentException();
        }
//        if (percolationIntGrid[1].length == 2) {
//            return true;
//        }
        for (int columnIndex = 1; columnIndex < percolationIntGrid[1].length; columnIndex++) {
            if (isOpen(1, columnIndex) && isOpen(row, col) && root(1, columnIndex) == root(row, col)) {  // confirm connection to top row
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    private void union(int row, int col) {
        int currentRoot;
        int topRoot;
        int bottomRoot;
        int rightRoot;
        int leftRoot;
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IllegalArgumentException();
        }
        if (row - 1 > 0 && row - 1 < size && col < size && isOpen(row - 1, col)) {
            topRoot = root(row - 1, col);
            currentRoot = root(row, col);
            if (topRoot != currentRoot) {
                if (percolationIntGridSize[topRoot] < percolationIntGridSize[currentRoot]) {
                    percolationIntGrid[topRoot / size][topRoot % size] = percolationIntGrid[currentRoot / size][currentRoot % size];
                    percolationIntGridSize[currentRoot] += percolationIntGridSize[topRoot];
                } else {
                    percolationIntGrid[currentRoot / size][currentRoot % size] = percolationIntGrid[topRoot / size][topRoot % size];
                    percolationIntGridSize[topRoot] += percolationIntGridSize[currentRoot];
                }
            }
        }
        if (row + 1 > 0 && row + 1 < size && col > 0 && col < size && isOpen(row + 1, col)) {
            bottomRoot = root(row + 1, col);
            currentRoot = root(row, col);
            if (bottomRoot != currentRoot) {
                if (percolationIntGridSize[bottomRoot] < percolationIntGridSize[currentRoot]) {
                    percolationIntGrid[bottomRoot / size][bottomRoot % size] = percolationIntGrid[currentRoot / size][currentRoot % size];
                    percolationIntGridSize[currentRoot] += percolationIntGridSize[bottomRoot];
                } else {
                    percolationIntGrid[currentRoot / size][currentRoot % size] = percolationIntGrid[bottomRoot / size][bottomRoot % size];
                    percolationIntGridSize[bottomRoot] += percolationIntGridSize[currentRoot];
                }
            }
        }
        if (row < size && col - 1 > 0 && col - 1 < size && isOpen(row, col - 1)) {
            leftRoot = root(row, col - 1);
            currentRoot = root(row, col);
            if (leftRoot != currentRoot) {
                if (percolationIntGridSize[leftRoot] < percolationIntGridSize[currentRoot]) {
                    percolationIntGrid[leftRoot / size][leftRoot % size] = percolationIntGrid[currentRoot / size][currentRoot % size];
                    percolationIntGridSize[currentRoot] += percolationIntGridSize[leftRoot];
                } else {
                    percolationIntGrid[currentRoot / size][currentRoot % size] = percolationIntGrid[leftRoot / size][leftRoot % size];
                    percolationIntGridSize[leftRoot] += percolationIntGridSize[currentRoot];
                }
            }
        }
        if (row > 0 && row < size && col + 1 > 0 && col + 1 < size && isOpen(row, col + 1)) {
            rightRoot = root(row, col + 1);
            currentRoot = root(row, col);
            if (rightRoot != currentRoot) {
                if (percolationIntGridSize[rightRoot] < percolationIntGridSize[currentRoot]) {
                    percolationIntGrid[rightRoot / size][rightRoot % size] = percolationIntGrid[currentRoot / size][currentRoot % size];
                    percolationIntGridSize[currentRoot] += percolationIntGridSize[rightRoot];
                } else {
                    percolationIntGrid[currentRoot / size][currentRoot % size] = percolationIntGrid[rightRoot / size][rightRoot % size];
                    percolationIntGridSize[rightRoot] += percolationIntGridSize[currentRoot];
                }
            }
        }

    }

    public boolean percolates() {
        int lastRowIndex = percolationIntGrid.length - 1;
        for (int column = 1; column < percolationIntGrid[1].length; column++) {
            if (isFull(lastRowIndex, column)) {
                StdOut.println(" opened Sites "+numberOfOpenSites);
                return true;
            }
        }
        return false;
    }

}
