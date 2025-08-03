package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF antiBackwash;
    private int N;
    private int count;
    private int topSite;
    private int bottomSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        topSite = N * N;
        bottomSite = topSite + 1;
        wqu = new WeightedQuickUnionUF(bottomSite + 1);
        antiBackwash = new WeightedQuickUnionUF(topSite + 1);
        this.N = N;
    }

    private void verify(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        verify(row, col);
        if (grid[row][col]) {
            return;
        }
        count++;
        grid[row][col] = true;
        if (row == 0) {
            wqu.union(xyTo1D(row, col), topSite);
            antiBackwash.union(xyTo1D(row, col), topSite);
        }
        if (row == N - 1) {
            wqu.union(xyTo1D(row, col), bottomSite);
        }

        // Upper
        if (row > 0 && grid[row - 1][col]) {
            wqu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            antiBackwash.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        // Lower
        if (row < N - 1 && grid[row + 1][col]) {
            wqu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            antiBackwash.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        // Left
        if (col > 0 && grid[row][col - 1]) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            antiBackwash.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        // Right
        if (col < N - 1 && grid[row][col + 1]) {
            wqu.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            antiBackwash.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        verify(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        verify(row, col);
        if (isOpen(row, col) && antiBackwash.connected(xyTo1D(row, col), topSite)) {
            return true;
        }
        return false;
    }

    private int xyTo1D(int row, int col) {
        verify(row, col);
        return row * N + col;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return wqu.connected(topSite, bottomSite);
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(1);
        perc.open(0, 0);
//        perc.open(1, 0);
//        perc.open(2, 0);
//        perc.open(3, 0);
//        perc.open(4, 0);
        perc.percolates();
    }

}
