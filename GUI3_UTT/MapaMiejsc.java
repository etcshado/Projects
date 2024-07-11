import java.util.HashMap;

public class MapaMiejsc {

    public static HashMap<Integer, String> mapaMiejsc = new HashMap<>();
    public static HashMap<Integer, Integer> NumericToIndex = new HashMap<>();

    static {

        // konwertowanie indeksow na odpowiednik Stringowy (LG - Lewo gora)
        mapaMiejsc.put(0, "LG");
        mapaMiejsc.put(1, "SG");
        mapaMiejsc.put(2, "PG");
        mapaMiejsc.put(3, "LS");
        mapaMiejsc.put(4, "SS");
        mapaMiejsc.put(5, "PS");
        mapaMiejsc.put(6, "LD");
        mapaMiejsc.put(7, "SD");
        mapaMiejsc.put(8, "PD");

        // konwetrowanie indeksow na odpowiedni przycisk na klawiaturze numerycznej
        NumericToIndex.put(7, 0);
        NumericToIndex.put(8, 1);
        NumericToIndex.put(9, 2);
        NumericToIndex.put(4, 3);
        NumericToIndex.put(5, 4);
        NumericToIndex.put(6, 5);
        NumericToIndex.put(1, 6);
        NumericToIndex.put(2, 7);
        NumericToIndex.put(3, 8);
    }
}
