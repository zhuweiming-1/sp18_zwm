package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private static final int BLANK = 0;
    private final int[][] board;
    private final int[][] goal;
    private final int N;

    /**
     * Constructs a board from an N-by-N array of tiles where tiles[i][j] = tile at row i, column j
     * 从一个 N×N 的方块数组构建棋盘，其中 tiles[i][j] 表示第 i 行第 j 列的方块
     *
     * @param tiles
     */
    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
        goal = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                goal[i][j] = i * N + j + 1;
            }
        }
        goal[N - 1][N - 1] = 0;
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * 返回第 i 行、第 j 列的方块值（如果为空则返回 0）
     *
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        return board[i][j];
    }

    /**
     * 返回棋盘大小 N
     * Returns the board size N
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board
     * 返回当前棋盘的相邻状态
     *
     * @return
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * Hamming estimate described below
     * 如下所述的汉明估计
     *
     * @return
     */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != goal[i][j]) {
                    count += 1;
                }
            }
        }
        for (int j = 0; j < size() - 1; j++) {
            if (board[size() - 1][j] != goal[size() - 1][j]) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Manhattan estimate described below
     * 如下所述的曼哈顿估计
     *
     * @return
     */
    public int manhattan() {
        int total = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != BLANK) {
                    total += Math.abs(board[i][j] - xyToSeq(i, j));
                }
            }
        }
        return total;
    }

    private int xyToSeq(int i, int j) {
        return i * size() + j + 1;
    }

    /**
     * Estimated distance to goal. This method should simply return the results of manhattan() when submitted to Gradescope.
     * 到目标的估计距离。当提交到 Gradescope 时，此方法应直接返回 manhattan() 的结果。
     *
     * @return
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * 如果此棋盘的方块值与 y 的位置相同，则返回 true
     *
     * @param y
     * @return
     */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board o = (Board) y;
        if (o.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != o.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Returns the string representation of the board.
     * 返回棋盘的字符串表示
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
