public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance(Point other) {
        int dX = Math.abs(other.x - this.x);
        int dY = Math.abs(other.y - this.y);
        return dX + dY;
    }
}
