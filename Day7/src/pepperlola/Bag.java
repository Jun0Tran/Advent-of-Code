package pepperlola;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private List<Bag> parents = new ArrayList<>();
    private List<BagChild> children = new ArrayList<>();
    private String color;

    public Bag(String color) {
        this.color = color;
    }

    public void addParent(Bag target) {
        parents.add(target);
    }

    public void addChild(Bag satellite, int count) {
        children.add(new BagChild(satellite, count));
    }

    public List<Bag> getParents() {
        return parents;
    }

    public void setParents(List<Bag> parents) {
        this.parents = parents;
    }

    public List<BagChild> getChildren() {
        return children;
    }

    public void setChildren(List<BagChild> children) {
        this.children = children;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bag)) return false;
        Bag other = (Bag) obj;
        return getColor().equals(other.getColor());
    }

    @Override
    public String toString() {
        return "BAG{COLOR=" + getColor() + ",PARENTS=" + getParents().toString() + "}";
    }
}
