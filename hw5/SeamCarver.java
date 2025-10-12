
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.WhiteFilter;

import java.awt.*;

public class SeamCarver {
    private Picture picture = null;
    private int width;
    private int height;
    private double[][] energies;
    private double[][] minSumEnergies;
    private int[][] path;
    private boolean isVertical = true;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        this.width = picture.width();
        this.height = picture.height();
        energies = new double[height][width];
        initEnergies();
        minSumEnergies = new double[height][width];
        path = new int[height][width];
        initMinSumEnergies();
    }

    private void initMinSumEnergies() {
        // 顶行，和energies一致
        for (int j = 0; j < width; j++) {
            minSumEnergies[0][j] = energies[0][j];
            path[0][j] = -1;
        }
        if (height > 1) {
            for (int i = 1; i < height; i++) {
                if (width == 1) {
                    minSumEnergies[i][0] = minSumEnergies[i - 1][0] + energies[i][0];
                    path[i][0] = 0;
                } else {
                    for (int j = 0; j < width; j++) {
                        if (j == 0) {
                            minSumEnergies[i][j] = energies[i][j] + Math.min(minSumEnergies[i - 1][j], minSumEnergies[i - 1][j + 1]);
                            path[i][j] = min(i - 1, j, j + 1);
                        } else if (j == width - 1) {
                            minSumEnergies[i][j] = energies[i][j] + Math.min(minSumEnergies[i - 1][j - 1], minSumEnergies[i - 1][j]);
                            path[i][j] = min(i - 1, j - 1, j);
                        } else {
                            minSumEnergies[i][j] = energies[i][j] + Math.min(Math.min(minSumEnergies[i - 1][j - 1], minSumEnergies[i - 1][j])
                                    , minSumEnergies[i - 1][j + 1]);
                            path[i][j] = min(i - 1, j - 1, min(i - 1, j, j + 1));
                        }
                    }
                }
            }
        }
    }

    private int min(int row, int col1, int col2) {
        if (minSumEnergies[row][col1] <= minSumEnergies[row][col2]) {
            return col1;
        } else {
            return col2;
        }
    }

    private void initEnergies() {
        if (width == 1 && height == 1) {
            return;
        } else if (width == 1) {
            for (int i = 0; i < height; i++) {
                energies[i][0] = yEnergy(i, 0);
            }
        } else if (height == 1) {
            for (int j = 0; j < width; j++) {
                energies[0][j] = xEnergy(0, j);
            }
        } else {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    energies[i][j] = xEnergy(i, j) + yEnergy(i, j);
                }
            }
        }

    }

    private double xEnergy(int y, int x) {
        Color cx1 = null;
        Color cx2 = null;
        if (x == 0) {
            cx1 = picture.get(width - 1, y);
            cx2 = picture.get(x + 1, y);
        } else if (x == width - 1) {
            cx1 = picture.get(x - 1, y);
            cx2 = picture.get(0, y);
        } else {
            cx1 = picture.get(x - 1, y);
            cx2 = picture.get(x + 1, y);
        }
        return squareEnergy(cx1, cx2);
    }

    private double yEnergy(int y, int x) {
        Color cy1 = null;
        Color cy2 = null;
        if (y == 0) {
            cy1 = picture.get(x, height - 1);
            cy2 = picture.get(x, y + 1);
        } else if (y == height - 1) {
            cy1 = picture.get(x, y - 1);
            cy2 = picture.get(x, 0);
        } else {
            cy1 = picture.get(x, y - 1);
            cy2 = picture.get(x, y + 1);
        }
        return squareEnergy(cy1, cy2);
    }

    private double squareEnergy(Color c1, Color c2) {
        int rdy = c2.getRed() - c1.getRed();
        int gdy = c2.getGreen() - c1.getGreen();
        int bdy = c2.getBlue() - c1.getBlue();
        return Math.pow(rdy, 2) + Math.pow(gdy, 2) + Math.pow(bdy, 2);
    }

    // current picture
    public Picture picture() {
        return picture;
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
    public double energy(int y, int x) {
        if (x < 0 || x >= height || y < 0 || y >= width) {
            throw new IndexOutOfBoundsException();
        }

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
        int[] result = new int[height];
        // 找到底行中能量和最小的位置
        Stack<Integer> stack = new Stack<>();
        int minIndex = indexOfRows(minSumEnergies[height - 1]);
        stack.push(minIndex);
        for (int i = height - 1; i > 0; i--) {

        }

        return null;
    }

    private int indexOfRows(double[] rows) {
        int minIndex = 0;
        for (int i = 1; i < rows.length; i++) {
            if (rows[i] < rows[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    public void removeHorizontalSeam(int[] seam) {
    }   // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {
    }   // remove vertical seam from picture
}
