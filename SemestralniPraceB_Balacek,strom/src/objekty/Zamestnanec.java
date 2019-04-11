package objekty;


public class Zamestnanec {
    private int id;
    private String jmeno;
    private String prijmeni;
    private String email;

    public Zamestnanec(int id, String jmeno, String prijmeni, String email) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Jmeno: " + jmeno + ", Přijemni: " + prijmeni + ", Email: " + email;
    }
    
    
}
