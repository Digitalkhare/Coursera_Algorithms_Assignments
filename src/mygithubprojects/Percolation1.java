package mygithubprojects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author osomo
 */
public class Percolation1 {

    // creates n-by-n grid, with all sites initially blocked
    int[][] percolationIntGrid;
    boolean[][] percolationbooleanGrid;
    int numberOfOpenSites;
    int size;

    public Percolation1(int n) {
        int number = 1;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        percolationIntGrid = new int[n][n];// initialise percolationIntGrid
        for (int[] percolationIntGrid1 : percolationIntGrid) {
            for (int column = 0; column < percolationIntGrid1.length; column++) {
                percolationIntGrid1[column] = number++; //assign unique numbers to grid
            }
            //initialise percolationbooleanGrid
            percolationbooleanGrid = new boolean[n][n];
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (size == 1 && row == 0 && col == 0) {
            percolationbooleanGrid[row][col] = true; //special case
            numberOfOpenSites++;
            return;
        }
        if (row >= 0 && row < size && col >= 0 && col < size && percolationbooleanGrid[row][col] == false) {
            percolationbooleanGrid[row][col] = true;
            numberOfOpenSites++;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return percolationbooleanGrid[row][col];
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        if (size == 1 && percolationbooleanGrid[0][0]) { //special case
            return true;
        }
        int numberOfRows = percolationIntGrid.length;
        for (int column = 0; column < percolationIntGrid.length; column++) {
            if (isFull(column, numberOfRows - 1)) {
                StdOut.println("number of open sites "+numberOfOpenSites);
                return true;
            }
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        for (int noOfColumns = 0; noOfColumns < percolationIntGrid[0].length; noOfColumns++) {
            if (row != 0 && percolationIntGrid[0][noOfColumns] == percolationIntGrid[row][col]) {  // confirm connection to top row
                return true;
            }
        }
        return false;
    }

    public void union(int row, int column) {
        int root = 0;
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;
        int size = percolationIntGrid.length;
        if (row < 0 || row > size || column < 0 || column > size) {
            //  System.out.println("index value is invalid " + "Row " + row + "Column " + column);
            return;
        }
        if (row >= 0 && row < size && column >= 0 && column < size) {
            root = percolationIntGrid[row][column];
        }
        if (row - 1 >= 0 && row - 1 < size && column >= 0 && column < size) {
            top = percolationIntGrid[row - 1][column];
        }
        if (row + 1 >= 0 && row + 1 < size && column >= 0 && column < size) {
            bottom = percolationIntGrid[row + 1][column];
        }
        if (row >= 0 && row < size && column - 1 >= 0 && column - 1 < size) {
            left = percolationIntGrid[row][column - 1];
        }
        if (row >= 0 && row < size && column + 1 >= 0 && column + 1 < size) {
            right = percolationIntGrid[row][column + 1];
        }
        for (int i = 0; i < percolationIntGrid.length; i++) {
            for (int j = 0; j < percolationIntGrid[i].length; j++) {
                if (isOpen(i, j) && (percolationIntGrid[i][j] == top || percolationIntGrid[i][j] == bottom || percolationIntGrid[i][j] == right || percolationIntGrid[i][j] == left)) {
                    percolationIntGrid[i][j] = root; // top
                }

            }

        }

    }

}
