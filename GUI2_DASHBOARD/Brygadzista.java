import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Brygadzista extends Uzytkownik {

    public HashMap<Integer, Brygada> brygady;
    public HashMap<Integer, Zlecenie> listaZlecen;
    public static HashMap<Integer, Brygadzista> mapaBrygadzistow = new HashMap<>();

    public int id;
    private static int iden = 1;

    public Brygadzista(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzialPracownikow, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzialPracownikow, login, haslo);
        this.brygady = new HashMap<>();
        this.id = iden++;
        this.listaZlecen = new HashMap<>();
        mapaBrygadzistow.put(id, this);
    }

    public void deleteBrygadzista() {
        Uzytkownik.mapaWszystkichUzytkownikow.remove(((Uzytkownik)this).id);
        Pracownik.mapaWszystkichPracownikow.remove(((Pracownik)this).id);
        Brygadzista.mapaBrygadzistow.remove(this.id);

        for (Brygada brygada : brygady.values()) {
            brygada.brygadzista = null;
            Brygada.mapaBrygad.remove(brygada.id);
        }
    }
}
