package objekty;


public enum TypPozice {
    REDITEL("Reditel"), VEDOUCIUSEKU("Vedouci useku"), 
    VEDOUCIPOBOCKY("vedouci pobocky"), PRACOVNIK("Pracovnik");
    
    private String text;
    
    private TypPozice(String text){
        this.text = text;
    }
    
    @Override
    public String toString(){
        return text;
    }
}
