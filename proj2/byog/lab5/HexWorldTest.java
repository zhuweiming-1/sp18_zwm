package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Assert;
import org.junit.Test;

public class HexWorldTest {

    @Test
    public void testHexRowWidth() {
        Assert.assertEquals(2, HexWorld.hexRowWidth(2, 0));
        Assert.assertEquals(4, HexWorld.hexRowWidth(2, 1));
        Assert.assertEquals(4, HexWorld.hexRowWidth(2, 2));
        Assert.assertEquals(2, HexWorld.hexRowWidth(2, 3));
        Assert.assertEquals(-1, HexWorld.hexRowWidth(2, 4));
        Assert.assertEquals(-1, HexWorld.hexRowWidth(2, -1));

        Assert.assertEquals(-1, HexWorld.hexRowWidth(5, -1));
        Assert.assertEquals(5, HexWorld.hexRowWidth(5, 0));
        Assert.assertEquals(7, HexWorld.hexRowWidth(5, 1));
        Assert.assertEquals(9, HexWorld.hexRowWidth(5, 2));
        Assert.assertEquals(11, HexWorld.hexRowWidth(5, 3));
        Assert.assertEquals(13, HexWorld.hexRowWidth(5, 4));
        Assert.assertEquals(13, HexWorld.hexRowWidth(5, 5));
        Assert.assertEquals(11, HexWorld.hexRowWidth(5, 6));
        Assert.assertEquals(9, HexWorld.hexRowWidth(5, 7));
        Assert.assertEquals(7, HexWorld.hexRowWidth(5, 8));
        Assert.assertEquals(5, HexWorld.hexRowWidth(5, 9));
        Assert.assertEquals(-1, HexWorld.hexRowWidth(5, 10));
    }

    @Test
    public void testHexRowOffset() {
        Assert.assertEquals(0, HexWorld.hexRowOffset(2, 0));
        Assert.assertEquals(-1, HexWorld.hexRowOffset(2, 1));
        Assert.assertEquals(-1, HexWorld.hexRowOffset(2, 2));
        Assert.assertEquals(0, HexWorld.hexRowOffset(2, 3));
        Assert.assertEquals(-888888, HexWorld.hexRowOffset(2, 4));
        Assert.assertEquals(-888888, HexWorld.hexRowOffset(2, -1));

        Assert.assertEquals(-888888, HexWorld.hexRowOffset(5, -1));
        Assert.assertEquals(0, HexWorld.hexRowOffset(5, 0));
        Assert.assertEquals(-1, HexWorld.hexRowOffset(5, 1));
        Assert.assertEquals(-2, HexWorld.hexRowOffset(5, 2));
        Assert.assertEquals(-3, HexWorld.hexRowOffset(5, 3));
        Assert.assertEquals(-4, HexWorld.hexRowOffset(5, 4));
        Assert.assertEquals(-4, HexWorld.hexRowOffset(5, 5));
        Assert.assertEquals(-3, HexWorld.hexRowOffset(5, 6));
        Assert.assertEquals(-2, HexWorld.hexRowOffset(5, 7));
        Assert.assertEquals(-1, HexWorld.hexRowOffset(5, 8));
        Assert.assertEquals(0, HexWorld.hexRowOffset(5, 9));
        Assert.assertEquals(-888888, HexWorld.hexRowOffset(5, 10));
    }


}
