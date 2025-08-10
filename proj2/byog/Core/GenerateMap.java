package byog.Core;

import byog.TileEngine.TETile;

public class GenerateMap {
    private TETile[][] worlds;
    private Room first;
    private int seed;

    public GenerateMap(TETile[][] worlds, int seed) {
        this.worlds = worlds;
        this.seed = seed;
    }

    private Room generateRoom(Room room) {
        if (room == null) {
            return new Room(new Position(10, 10), 5, 5, null);
        }

        return null;
    }

    private Room generateRoom(Room room, TETile[][] worlds) {
        if (room == null) {
            return new Room();
        }
        return null;
    }

    private void generateRooms() {
        first = generateRoom(first);
    }

    private void fillWorlds() {

    }

    public TETile[][] generateWorlds() {
        generateRooms();
        fillWorlds();
        return worlds;
    }

    public static void main(String[] args) {

    }

}
