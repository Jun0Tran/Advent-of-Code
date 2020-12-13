package pepperlola;

public class Tuple<A, B> {
    private A x;
    private B y;

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
    public String toString() {
        return "Tuple(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple)) return false;
        Tuple<?, ?> other = ((Tuple<?, ?>) obj);
        return this.getX().equals(other.getX()) && this.getY().equals(other.getY());
    }
}
