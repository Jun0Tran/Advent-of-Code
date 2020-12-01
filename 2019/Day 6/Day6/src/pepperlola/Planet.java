package pepperlola;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private List<Planet> targets = new ArrayList<>();
    private List<Planet> satellites = new ArrayList<>();
    private String name;

    public Planet(String name) {
        this.name = name;
    }

    public void addTarget(Planet target) {
        targets.add(target);
    }

    public void addSatellite(Planet satellite) {
        satellites.add(satellite);
    }

    public void replaceTarget(Planet target) {
        getTargets().remove(target);
        getTargets().add(target);
    }

    public void replaceSatellite(Planet satellite) {
        getSatellites().remove(satellite);
        getSatellites().add(satellite);
    }

    public List<Planet> getTargets() {
        return targets;
    }

    public void setTargets(List<Planet> targets) {
        this.targets = targets;
    }

    public List<Planet> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Planet> satellites) {
        this.satellites = satellites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Planet)) return false;
        Planet other = (Planet) obj;
        return getName().equals(other.getName());
    }

    @Override
    public String toString() {
        return "PLANET{NAME=" + getName() + ",TARGETS=" + getTargets().toString() + "}";
    }
}
