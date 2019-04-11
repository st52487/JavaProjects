package procesy;

import vycty.TypProcesu;
import java.io.Serializable;


public interface IProces extends Serializable {

    int getCasProcesu();

    String getId();

    TypProcesu getTypProcesu();

    void setCasProcesu(int casProcesu);

    void setId(String id);

    void setTypProcesu(TypProcesu typProcesu);

    String toString();
    
}
