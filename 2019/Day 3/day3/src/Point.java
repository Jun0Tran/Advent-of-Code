public class Point {
    public int x;
    public int y;

    /**
     * Constructor for the point class
     * @param x X value of the point
     * @param y Y value of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets Manhattan distance between this point and another, essentially the change in x + the change in y
     * @param other Other point to get the distance from
     * @return Distance between the two points
     */
    public int getDistance(Point other) {
        int dX = Math.abs(other.x - this.x);
        int dY = Math.abs(other.y - this.y);
        return dX + dY;
    }
}
