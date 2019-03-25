package automobily;


public enum TypAutomobilu {
    DODAVKA("Dodávka"), OSOBNI("Osobní"), NAKLADNI("Nákladní"), TAHAC("Tahač"),
    VALNIK("Valník");
    
    private final String name;
    
    private TypAutomobilu(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
