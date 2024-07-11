import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DzialPracownikow {

    public static ArrayList<String> UniqueNames = new ArrayList<>();
    public static HashMap<Integer, DzialPracownikow> listaWszystkichDzialow = new HashMap<>();

    public String nazwa;

    private static int iden = 1;
    public int id;

    private DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
        UniqueNames.add(nazwa);
        this.id = iden++;

        listaWszystkichDzialow.put(id, this);
    }
    public static DzialPracownikow createDzial(String nazwa) {
        if (!UniqueNames.contains(nazwa)) {
            return new DzialPracownikow(nazwa);
        } else {
            throw new IllegalArgumentException("not a unique name of Dzial");
        }
    }
    public List<Pracownik> listaPracownikowDzialu() {
        List<Pracownik> listaPracownikow = new ArrayList<>();
        for (Pracownik pr : Pracownik.mapaWszystkichPracownikow.values()) {
            if (pr.dzialPracownikow == this) {
                    listaPracownikow.add(pr);
            }
        }
        return listaPracownikow;
    }

    public void deleteDzial() {
        UniqueNames.remove(this.nazwa);
        listaWszystkichDzialow.remove(this.id, this);
    }
}
