
package automobily;


public abstract class Naves {
    
    
    private int maxPocetPolozek;
    private boolean jede;
    
    Naves(int maxPocetPolozek, boolean jede){
        this.maxPocetPolozek = maxPocetPolozek;
        this.jede = jede;
    }

    public void setMaxPocetPolozek(int maxPocetPolozek) {
        this.maxPocetPolozek = maxPocetPolozek;
    }

    public void setJede(boolean jede) {
        this.jede = jede;
    }

    public int getMaxPocetPolozek() {
        return maxPocetPolozek;
    }

    public boolean isJede() {
        return jede;
    }

    @Override
    public String toString() {
        return "Návěs počet položek: " + maxPocetPolozek + ", Pojízdný návěs: " + 
                (jede ? "Ano" : "Ne")+ ',';
    }
    
    
}
