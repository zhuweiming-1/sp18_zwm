
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.WhiteFilter;

import java.awt.*;

public class SeamCarver {
    private Picture picture = null;
    private int width;
    private int height;
    private boolean isVertical = true;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        this.width = picture.width();
        this.height = picture.height();
    }


    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        Color up, down, left, right;
        if (isVertical) {
            up = y > 0 ? picture.get(x, y - 1) : picture.get(x, height - 1);
            down = y < height - 1 ? picture.get(x, y + 1) : picture.get(x, 0);
            left = x > 0 ? picture.get(x - 1, y) : picture.get(width - 1, y);
            right = x < width - 1 ? picture.get(x + 1, y) : picture.get(0, y);
        } else {
            up = x > 0 ? picture.get(x - 1, y) : picture.get(height - 1, y);
            down = x < height - 1 ? picture.get(x + 1, y) : picture.get(0, y);
            left = y > 0 ? picture.get(x, y - 1) : picture.get(x, width - 1);
            right = y < width - 1 ? picture.get(x, y + 1) : picture.get(x, 0);
        }

        int drx = right.getRed() - left.getRed();
        int dgx = right.getGreen() - left.getGreen();
        int dbx = right.getBlue() - left.getBlue();

        int dry = up.getRed() - down.getRed();
        int dgy = up.getGreen() - down.getGreen();
        int dby = up.getBlue() - down.getBlue();

        return drx * drx + dgx * dgx + dbx * dbx + dry * dry + dgy * dgy + dby * dby;
    }


    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        isVertical = false;
        swap();
        int[] res = findVerticalSeam();
        swap();
        isVertical = true;
        return res;
    }

    private void swap() {
        int temp = width;
        width = height;
        height = temp;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[][] path = new int[width][height];
        double[][] cost = new double[width][height];
        for (int i = 0; i < width; i++) {
            double e = isVertical ? energy(i, 0) : energy(0, i);
            cost[i][0] = e;
            path[i][height - 1] = i;
        }

        for (int j = 1; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double e = isVertical ? energy(i, j) : energy(j, i);
                cost[i][j] = e + getMinCost(i, j, path, cost);
            }
        }

        int[] res = new int[height];
        double min = Double.MAX_VALUE;
        int minPos = 0;
        for (int i = 0; i < width; i++) {
            if (cost[i][height - 1] < min) {
                min = cost[i][height - 1];
                minPos = i;
            }
        }

        for (int j = height - 1; j >= 0; j--) {
            res[j] = path[minPos][j];
            minPos = res[j];
        }
        return res;
    }

    private double getMinCost(int i, int j, int[][] path, double[][] cost) {
        double[] v = new double[3];
        v[1] = cost[i][j - 1];
        if (i > 0) {
            v[0] = cost[i - 1][j - 1];
        } else {
            v[0] = Double.MAX_VALUE;
        }
        if (i < width - 1) {
            v[2] = cost[i + 1][j - 1];
        } else {
            v[2] = Double.MAX_VALUE;
        }
        double res = Double.MAX_VALUE;
        int pos = 0;
        for (int m = 0; m < 3; m++) {
            if (v[m] < res) {
                res = v[m];
                pos = m;
            }
        }
        path[i][j - 1] = pos + i - 1;
        return res;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(picture, seam);
    }   // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height || !isValidSeam(seam)) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeVerticalSeam(picture, seam);
    }   // remove vertical seam from picture

    private boolean isValidSeam(int[] seam) {
        for (int i = 0, j = 1; j < seam.length; i++, j++) {
            if (Math.abs(seam[i] - seam[j]) > 1) {
                return false;
            }
        }
        return true;
    }
}
