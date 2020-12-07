package pepperlola;

public class BagChild {
    private Bag bag;
    private int count;

    public BagChild(Bag bag, int count) {
        this.bag = bag;
        this.count = count;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
