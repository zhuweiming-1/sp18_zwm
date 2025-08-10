package byog.Core;

public class Room {
    Position lowerLeftCornerPoint; // 左下角
    int wide;
    int high;
    Connected connectedPoint; // 两个房间的连接点
    Room eastRoom; // 东
    Room westRoom; // 西
    Room northRoom; // 北
    Room southRoom; // 南

    public Room() {
    }

    public Room(Position lowerLeftCornerPoint, int wide, int high, Connected connectedPoint) {
        this.lowerLeftCornerPoint = lowerLeftCornerPoint;
        this.wide = wide;
        this.high = high;
        this.connectedPoint = connectedPoint;
    }
}
