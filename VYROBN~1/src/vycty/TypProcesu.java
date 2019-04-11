package vycty;

public enum TypProcesu {
    MANUALNI("Manuální"), ROBOT("Robotický");

    private String name;

    private TypProcesu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "" + name;
    }

    
}
