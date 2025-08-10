package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void addHexagon(TETile[][] worlds, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int i = 0; i < s * 2; i++) {
            Position newP = new Position(p.x + hexRowOffset(s, i), p.y + i);
            addRow(worlds, newP, hexRowWidth(s, i), t);
        }
    }

    /* 计算大小为 s 的六边形第 i 行的宽度。
     * @param s 六边形的大小。
     * @param i 行号，其中 i = 0 表示最底部的行。
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        if (i < s && i >= 0) {
            return s + 2 * i;
        } else if (s <= i && i < 2 * s) {
            return s * 5 - 2 * i - 2;
        } else {
            return -1;
        }
    }

    /**
     * 计算第 i 行最左侧方块的相对 x 坐标
     * 六边形的一行，假设底部那一行的 x 坐标为零。
     * 例如，如果 s = 3，且 i = 2，这个函数
     * 返回 -2，因为从底部往上数第 2 行的起始位置比起始点向左偏移了 2 个单位，
     * 例如。
     * xxxx
     * xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2，从六边形底部向左数第二个位置开始
     * xxxxxx
     * xxxx
     *
     * @param s 六边形的大小
     * @param i 六边形的行号，其中 i = 0 表示底部
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        if (i >= 0 && i < s) {
            return -i;
        } else if (i >= s && i < 2 * s) {
            return -(s * 2 - 1 - i);
        } else {
            return -888888;
        }
    }

    /**
     * 添加一行相同的方块。
     *
     * @param world 要绘制的世界
     * @param p     行最左侧的位置
     * @param width 要绘制的瓷砖宽度
     * @param t     要绘制的瓷砖
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i++) {
            world[p.x + i][p.y] = t;
        }
    }

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] worlds = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                worlds[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(10, 10);
        addHexagon(worlds, p, 20, Tileset.WALL);

        ter.renderFrame(worlds);
    }
}
