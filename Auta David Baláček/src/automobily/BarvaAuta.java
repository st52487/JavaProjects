package automobily;

public enum BarvaAuta {
    BÍLA ("Bílá"), ČERVENÁ("Červená"), ČERNÁ("Černá"), MODRÁ("Modrá"), 
    ZELENÁ("Zelená"), RŮŽOVÁ("Růžová"), FIALOVÁ("Fialová"), ŽLUTÁ("Žlutá"), 
    ORANŽOVÁ("Oranžová");

    private final String barva;
    
    private BarvaAuta(String barva) {
        this.barva = barva;
    }

    public String getBarva() {
        return barva;
    }

    @Override
    public String toString() {
        return barva;
    }
}
