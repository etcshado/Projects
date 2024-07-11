import java.util.HashMap;

public class Praca {
    public static HashMap<Integer, Praca> mapaWszystkichPrac = new HashMap<>();
    public enum RodzajPracy { OGOLNA, MONTAZ, DEMONTAZ, WYMIANA }

    public RodzajPracy rodzajPracy;
    public int czasPracy;
    public boolean czyZrealizowane;
    public String opis;

    public int id;
    public static int iden = 1;

    public Praca(RodzajPracy rodzajPracy, int czasPracy, String opis) {
        this.rodzajPracy = rodzajPracy;
        this.czasPracy = czasPracy;

        this.czyZrealizowane = false;
        this.opis = opis;
        this.id = iden++;

        mapaWszystkichPrac.put(this.id, this);
    }

    public void deletePraca() {

        Praca.mapaWszystkichPrac.remove(this.id);
        for (Brygadzista brygadzista : Brygadzista.mapaBrygadzistow.values()) {
            for (Zlecenie zlec : brygadzista.listaZlecen.values()) {
                zlec.prace.remove(this);
            }
        }

    }
}
