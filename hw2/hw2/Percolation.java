package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private WeightedQuickUnionUF setWithTopnBottom;
    private WeightedQuickUnionUF withoutBottom;
    private int top;
    private int bottom;
    private int size;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        setWithTopnBottom = new WeightedQuickUnionUF(N * N + 2);
        withoutBottom = new WeightedQuickUnionUF(N * N + 1);
        top = 0;
        bottom = N * N + 1;
        grid = new boolean[N][N];
        this.N = N;
        size = 0;
    }

    private int xyTo1D(int row, int col) {
        return row * N + col + 1;
    }

    private void validate(int row, int col) {
        if (N <= row || row < 0 || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            size++;
        }
        if (row == 0) {
            setWithTopnBottom.union(xyTo1D(row, col), top);
            withoutBottom.union(xyTo1D(row, col), top);
        }

        if (row == N - 1) {
            setWithTopnBottom.union(xyTo1D(row, col), bottom);
        }

        for (int[] d : dirs) {
            int x = d[0] + row;
            int y = d[1] + col;
            if (x >= 0 && x < N && y >= 0 && y < N) {
                if (isOpen(x, y)) {
                    setWithTopnBottom.union(xyTo1D(row, col), xyTo1D(x, y));
                    withoutBottom.union(xyTo1D(row, col), xyTo1D(x, y));
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return withoutBottom.connected(xyTo1D(row, col), top);

    }

    public int numberOfOpenSites() {
        return size;
    }

    public boolean percolates() {
        return setWithTopnBottom.connected(top, bottom);
    }

}
