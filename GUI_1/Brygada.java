import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Brygada {

    public String nazwa;
    public Brygadzista brygadzista;
    public List<Pracownik> listaPracownikow;
    public static HashMap<Integer, Brygada> mapaBrygad = new HashMap<>();

    public int id;
    private static int iden = 1;

    Brygada(String nazwa, Brygadzista brygadzista) {
        this.nazwa = nazwa;
        this.brygadzista = brygadzista;
        this.id = iden++;
        listaPracownikow = new ArrayList<>();
        listaPracownikow.add(brygadzista);

        brygadzista.brygady.put(id, this);
        mapaBrygad.put(id, this);
    }

    public void addPracownik(Pracownik pracownik) throws PracownikisUzytkownik {
       if (pracownik instanceof Brygadzista) {
           listaPracownikow.add(pracownik);
           ((Brygadzista) pracownik).brygady.put(id, this);
       } else if (pracownik instanceof Uzytkownik) {
           throw new PracownikisUzytkownik();
       } else {
           listaPracownikow.add(pracownik);
       }

    }

    public void deleteBrygada() {
        brygadzista.brygady.remove(this.id);
        mapaBrygad.remove(this.id);

        for (Pracownik pracownik : listaPracownikow) {
            pracownik.dzialPracownikow = null;
        }
    }
}
