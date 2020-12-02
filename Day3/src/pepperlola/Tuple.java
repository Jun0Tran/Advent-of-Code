package pepperlola;

public class Tuple<A, B> {
    A x;
    B y;

    public Tuple(A x, B y) {
        this.x = x;
        this.y = y;
    }

    public A getX() {
        return x;
    }

    public void setX(A x) {
        this.x = x;
    }

    public B getY() {
        return y;
    }

    public void setY(B y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple)) return false;
        Tuple<?, ?> other = (Tuple<?, ?>) obj;
        return this.x.equals(other.x) && this.y.equals(other.y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
