import java.time.LocalDate;
import java.util.HashMap;

public class Uzytkownik extends Pracownik {

    public static HashMap<Integer, Uzytkownik> mapaWszystkichUzytkownikow = new HashMap<>();
    public String login;
    public String haslo;
    public String inicial;
    public int id;
    private static int iden = 1;
    public Uzytkownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.inicial = getInicial(imie, nazwisko);
        this.id = iden++;
        mapaWszystkichUzytkownikow.put(id, this);
    }

    public String getInicial(String imie, String nazwisko) {
        return (imie.charAt(0) + "" + nazwisko.charAt(0)).toUpperCase();
    }

    public void deleteUzytkownik() {
        Uzytkownik.mapaWszystkichUzytkownikow.remove(this.id);
        Pracownik.mapaWszystkichPracownikow.remove(((Pracownik)this).id);
    }
}
