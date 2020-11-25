public class Segment {
    public Point start;
    public Point end;
    public int dist;
    public SegmentDirection direction;

    public Segment(Point start, Point end, int dist) {
        this.start = start;
        this.end = end;
        this.dist = dist;

        if (start.x == end.x) {
            this.direction = SegmentDirection.VERTICAL;
        } else if (start.y == end.y) {
            this.direction = SegmentDirection.HORIZONTAL;
        }
    }

    public Point intersect(Segment other) {
        if (this.direction == other.direction)
            return null;
        if (this.direction == SegmentDirection.VERTICAL) {
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
        return null;
    }

    public enum SegmentDirection {
        VERTICAL,
        HORIZONTAL
    }
}
