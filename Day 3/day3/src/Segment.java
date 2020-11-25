public class Segment {
    public Point start;
    public Point end;
    public int dist;
    public SegmentDirection direction;

    /**
     * Constructor for line segment (wire) class
     * @param start Starting point of the wire
     * @param end Ending point of the wire
     * @param dist Length of the wire (short for distance)
     */
    public Segment(Point start, Point end, int dist) {
        this.start = start;
        this.end = end;
        this.dist = dist;

        // sets orientation of the segment based on the x and y values
        if (start.x == end.x) {
            /*  | (0, 3)
                | (0, 2)
                | (0, 1)
                | (0, 0)
                x values are all the same, meaning it's vertical
             */
            this.direction = SegmentDirection.VERTICAL;
        } else if (start.y == end.y) {
            /*
                -      -      -      -      -
                (0, 0) (1, 0) (2, 0) (3, 0) (4, 0)
                y values are all the same, meaning it's horizontal
             */
            this.direction = SegmentDirection.HORIZONTAL;
        }
    }

    /**
     * Calculates the point of intersection between this point and another
     * @param other Other segment
     * @return The point of intersection
     */
    public Point intersect(Segment other) {
        if (this.direction == other.direction) // parallel, likely more than one intersection
            return null;
        if (this.direction == SegmentDirection.VERTICAL) {
            // I'm not even going to try to explain this, but it's essentially testing if the points intersect
            // and based on this we can figure out which x and y values correspond with the intersection
            // for example,
            /*                other
                                |
                this -----------|--------
                                |
                intersection would be at point (other.x, this.y)
                                this
                                 |
                other -----------|--------
                                 |
                intersection would be at point (this.x, other.y)
             */
            if (((this.start.y > other.start.y && this.end.y < other.start.y)
                    || (this.start.y < other.start.y && this.end.y > other.start.y))
                    && ((other.start.x < this.start.x && this.start.x < other.end.x)
                            || (other.start.x > this.start.x && this.start.x > other.end.x))) {
                return new Point(this.start.x, other.start.y);
            }
        } else if (this.direction == SegmentDirection.HORIZONTAL) {
            if (((this.start.x < other.start.x && this.end.x > other.start.x)
                    || (this.start.x > other.start.x && this.end.x < other.start.x))
                    && ((other.start.y < this.start.y && this.start.y < other.end.y)
                            || (other.start.y > this.start.y && this.start.y > other.end.y))) {
                return new Point(other.start.x, this.start.y);
            }
        }
        // return null if there's no intersection
        return null;
    }

    public enum SegmentDirection {
        VERTICAL,
        HORIZONTAL
    }
}
