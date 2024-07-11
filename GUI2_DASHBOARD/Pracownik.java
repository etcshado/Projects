import java.time.LocalDate;
import java.util.HashMap;

public class Pracownik {

    public static HashMap<Integer, Pracownik> mapaWszystkichPracownikow = new HashMap<>();

    public String imie;
    public String nazwisko;
    public LocalDate dataUrodzenia;
    public DzialPracownikow dzialPracownikow;
    public int id;
    private static int iden = 1;

    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzialPracownikow) {
        this.id = iden++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzialPracownikow = dzialPracownikow;

        mapaWszystkichPracownikow.put(id, this);
    }
    public void deletePracownik() {
        Pracownik.mapaWszystkichPracownikow.remove(this.id, this);
    }

    @Override
    public String toString() {
        return "id=" + id + ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", dzialPracownikow=" + dzialPracownikow;

    }
}
