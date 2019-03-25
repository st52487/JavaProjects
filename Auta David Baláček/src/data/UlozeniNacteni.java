package data;

import alert.ChybovaHlaska;
import automobily.Automobil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import kolekce.ISeznam;
import kolekce.KolekceException;

public final class UlozeniNacteni {

    private static final String FILE = "zaloha.bin";

    private UlozeniNacteni() {
    }

    public static void serializuj(ISeznam<Automobil> seznam) {
        try {
            ObjectOutputStream out;
            try (FileOutputStream fileOut = new FileOutputStream(FILE)) {
                out = new ObjectOutputStream(fileOut);
                out.writeInt(seznam.getPocet());
                Iterator iterator = seznam.iterator();
                while (iterator.hasNext()) {
                    out.writeObject(iterator.next());
                }
            }
            out.close();
        } catch (IOException ex) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se uložit soubory!");
        }
    }

    public static void deserializuj(ISeznam<Automobil> seznam) {
        seznam.zrus();
        try {
            ObjectInputStream in;
            try (FileInputStream fileIn = new FileInputStream(FILE)) {
                in = new ObjectInputStream(fileIn);
                int pocet = in.readInt();
                for (int i = 0; i < pocet; i++) {
                    seznam.pridej((Automobil) in.readObject());
                }
            }
            in.close();
        } catch (KolekceException | ClassNotFoundException | IOException ex) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se načíst soubory!");
        }
    }
}