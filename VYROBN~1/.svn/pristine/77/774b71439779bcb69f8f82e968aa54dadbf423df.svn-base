package knihovna;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import kolekce.IAbstrDoubleList;
import procesy.IProces;

public final class Serializace {

    private static final String FILE = "zaloha.bin";
    private Serializace() {

    }

    public static void serializuj(IAbstrDoubleList<IProces> seznam) {
        try {
            ObjectOutputStream out;
            try (FileOutputStream fileOut = new FileOutputStream(FILE)) {
                out = new ObjectOutputStream(fileOut);
                Iterator iterator = seznam.iterator();
                while (iterator.hasNext()) {
                    IProces proces = (IProces) iterator.next();
                    out.writeObject(proces);
                }
            }
            out.close();
            knihovna.Alerts.informationAlert("Uložení", "Proběhla serializace objektů");
        } catch (IOException ex) {
            knihovna.Alerts.showErrorAlert("Chyba!", "Nepodařilo se uložit soubory!");
        }
    }

    public static void deserializuj(IAbstrDoubleList<IProces> seznam) {
        seznam.zrus();
        try {
            ObjectInputStream in;
            try (FileInputStream fileIn = new FileInputStream(FILE)) {
                in = new ObjectInputStream(fileIn);
                
                while (fileIn.available() != 0) {
                    seznam.vlozPrvni((IProces) in.readObject());
                }
                knihovna.Alerts.informationAlert("Nahrání", "Proběhla deserializace objektů");
            }
            in.close();
        } catch (ClassNotFoundException | IOException ex) {
            knihovna.Alerts.showErrorAlert("Chyba!", "Nepodařilo se načíst soubory!");
        }
    }
}
