
package procesy;

import vycty.TypProcesu;



public abstract class Proces implements IProces{
    
    private String id;
    private int casProcesu;
    private TypProcesu typProcesu;
    
    public Proces(String id, int casProcesu, TypProcesu typProcesu){
        this.id = id;
        this.casProcesu = casProcesu;
        this.typProcesu = typProcesu;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setCasProcesu(int casProcesu) {
        this.casProcesu = casProcesu;
    }

    @Override
    public void setTypProcesu(TypProcesu typProcesu) {
        this.typProcesu = typProcesu;
    }

    @Override
    public int getCasProcesu() {
        return casProcesu;
    }

    @Override
    public TypProcesu getTypProcesu() {
        return typProcesu;
    }
    
    @Override
    public String toString() {
        return "Proces " + " id: " + id + ", čas procesu: " + casProcesu + 
                ", druh procesu: " + typProcesu.name();
    }

    
}
