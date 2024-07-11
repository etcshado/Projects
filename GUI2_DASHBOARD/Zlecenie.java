import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Zlecenie {
    public static HashMap<Integer, Zlecenie> mapaWszystkichZlecen = new HashMap<>();
    public enum stanZlecenia { PLANOWANE, NIEPLANOWANE, REALIZOWANE, ZAKONCZONE }

    public List<Praca> prace;
    public Brygada brygada;
    public stanZlecenia stanZlecenia;
    public LocalDateTime dataUtworzenia;
    public LocalDateTime dataRealizacji;
    public LocalDateTime dataZakonczenia;
    public int id;
    private static int iden = 1;

    public Zlecenie(Brygada brygada, boolean planowane) {
        this.prace = new ArrayList<>();
        this.brygada = brygada;
        this.stanZlecenia = planowane ? stanZlecenia.PLANOWANE : stanZlecenia.NIEPLANOWANE;
        this.dataUtworzenia = LocalDateTime.now();
        this.dataRealizacji = null;
        this.dataZakonczenia = null;
        this.id = iden++;
        addZleceniaBrygadzistom(brygada);

        mapaWszystkichZlecen.put(id, this);
    }

    public void addZleceniaBrygadzistom(Brygada bryg) {

        for (Pracownik pracownik : bryg.listaPracownikow) {
            if (pracownik instanceof Brygadzista) {
                if (!(((Brygadzista) pracownik).listaZlecen.containsValue(this))) {
                    ((Brygadzista) pracownik).listaZlecen.put(id, this);
                }
            }
        }
    }

    public void addPraca(Praca praca) {
        if (!prace.contains(praca)) {
            prace.add(praca);
        }
    }

    public void deleteZlecenie() {
        for (Pracownik pracownik : brygada.listaPracownikow) {
            if (pracownik instanceof Brygadzista) {
                ((Brygadzista) pracownik).listaZlecen.remove(this.id);
            }
        }

        Zlecenie.mapaWszystkichZlecen.remove(this.id);
    }
}
