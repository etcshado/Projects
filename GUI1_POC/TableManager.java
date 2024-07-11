import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableManager {
    public static boolean refreshPending = true;
    public static HashMap<String, JTable> tablesCenter;

    public TableManager(Uzytkownik loggedInUser) {
        tablesCenter = new HashMap<>();
        createTables(loggedInUser);
    }

    // Początkowe tworzenie wszystkich tablic
    private void createTables(Uzytkownik loggedInUser) {
        if (!tablesCenter.containsKey("Dzial pracownikow")) {
            JTable table;
            if (loggedInUser.dzial != null) {
                int length = Dzial.UniqueNames.size();
                Object[] columnNames = {"Check", "Nazwa Dzialu"};
                Object[][] data = new Object[length][2];


                for (int i = 0; i < length; i++) {
                    String p = Dzial.UniqueNames.get(i);
                    data[i] = new Object[]{false, p};
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        if (columnIndex == 0) {
                            return Boolean.class;
                        }
                        return super.getColumnClass(columnIndex);
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "Nazwa Dzialu"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Dzial pracownikow", table);
        }

        if (!tablesCenter.containsKey("Pracownik")) {
            JTable table;
            if (loggedInUser instanceof Pracownik) {
                int size = loggedInUser.wszyscyPracownicy.size();
                Object[] columnNames = {"Check", "Imie", "Nazwisko", "DOB", "Dzial"};
                Object[][] data = new Object[size][5];
                for (int i = 0 ; i < size; i ++) {
                    Pracownik currPracownik = loggedInUser.wszyscyPracownicy.get(i);
                    data[i] = new Object[]{false, currPracownik.imie, currPracownik.nazwisko, currPracownik.DOB, currPracownik.dzial};
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "Imie", "Nazwisko", "DOB", "Dzial"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Pracownik", table);
        }

        if (!tablesCenter.containsKey("Uzytkownik")) {
            JTable table;
            if (loggedInUser instanceof Uzytkownik) {
                Object[] columnNames = {"Check", "Imie", "Nazwisko", "DOB", "Login", "Haslo", "Inicial"};
                Object[][] data = {{false, loggedInUser.imie, loggedInUser.nazwisko, String.valueOf(loggedInUser.DOB), ((Uzytkownik) loggedInUser).login, ((Uzytkownik) loggedInUser).haslo, ((Uzytkownik) loggedInUser).inicial}};
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "Imie", "Nazwisko", "DOB", "Login", "Haslo", "Inicial"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Uzytkownik", table);
        }

        if (!tablesCenter.containsKey("Brygadzista")) {
            JTable table;
            if (loggedInUser instanceof Brygadzista) {
                Brygadzista brygadzista = (Brygadzista) loggedInUser;
                Object[] columnNames = {"Check", "Imie", "Nazwisko", "DOB", "Login", "Haslo", "Inicial", "Brygady", "Zlecenia"};
                Object[][] data = {{false, brygadzista.imie, brygadzista.nazwisko, String.valueOf(brygadzista.DOB), brygadzista.login, brygadzista.haslo, brygadzista.inicial, brygadzista.getlistaBrygad(), brygadzista.getZlecenia()}};
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "Imie", "Nazwisko", "DOB", "Login", "Haslo", "Inicial", "Brygady", "Zlecenia"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Brygadzista", table);
        }

        if (!tablesCenter.containsKey("Brygada")) {
            JTable table;
            if (loggedInUser instanceof Brygadzista) {
                Brygadzista brygadzista = (Brygadzista) loggedInUser;
                int length = brygadzista.getlistaBrygad().size();
                Object[] columnNames = {"Check", "id", "Nazwa", "Pracownicy"};
                Object[][] data = new Object[length][4];

                for (int i = 0; i < length; i++) {
                    Brygada b = brygadzista.getlistaBrygad().get(i);
                    data[i] = new Object[]{false, b.getId(), b.nazwa, b.getpracownicyBrygady()};
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "id", "Nazwa", "Pracownicy"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Brygada", table);
        }

        if (!tablesCenter.containsKey("Praca")) {
            JTable table;
            if (loggedInUser instanceof Brygadzista) {
                Object[] columnNames = {"Check", "id", "Rodzaj Pracy", "Czas Pracy", "Opis", "Zrealizowane"};

                List<Praca> listaThisPrac = new ArrayList<>();

                for (Zlecenie zlec : ((Brygadzista) loggedInUser).getZlecenia()) {
                    for (Praca pr : zlec.listaPrac) {
                        for (Praca subpr : pr.praceDoZrealizowania) {
                            listaThisPrac.add(subpr);
                        }
                        listaThisPrac.add(pr);
                    }
                }
                int length = listaThisPrac.size();
                Object[][] data = new Object[length][6];

                int i = 0;
                for (Praca praca : listaThisPrac) {
                    data[i++] = new Object[]{false, praca.getthisId(), praca.rodzajPracy, praca.czasPracy, praca.opis, praca.zrealizowane};
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "id", "Rodzaj Pracy", "Czas Pracy", "Opis", "Zrealizowane"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Praca", table);
        }

        if (!tablesCenter.containsKey("Zlecenie")) {
            JTable table;
            if (loggedInUser instanceof Brygadzista) {

                Object[] columnNames = {"Check", "id", "Rodzaj Zlecenia", "Brygada", "Lista Prac", "Data Utworzenia", "Data Rozpoczecia", "Data Zakonczenia"};
                int length = ((Brygadzista) loggedInUser).getZlecenia().size();
                Object[][] data = new Object[length][8];

                // TODO: 06.06.2024 - dodać gettery do Zlecenie.java 
                
                for (int i = 0; i < length; i++) {
                    Zlecenie zlecenie = ((Brygadzista) loggedInUser).getZlecenia().get(i);
                    data[i] = new Object[]{false, zlecenie.getId(), zlecenie.rodzajZlecenia, zlecenie.brygada, zlecenie.listaPrac, zlecenie.dataUtworzenia, zlecenie.dataRozpoczecia, zlecenie.dataZakonczenia};
                }
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "id", "Rodzaj Zlecenia", "Brygada", "Lista Prac", "Data Utworzenia", "Data Rozpoczecia", "Data Zakonczenia"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("Zlecenie", table);


        }

        if (!tablesCenter.containsKey("PendingZlecenia")) {
            JTable table;
            if (loggedInUser instanceof Brygadzista) {

                Object[] columnNames = {"Check", "id", "Rodzaj Zlecenia", "Brygada", "Lista Prac", "Data Utworzenia", "Data Rozpoczecia", "Data Zakonczenia"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                };
                table = new JTable(model);

                for (Zlecenie zlecenie : ((Brygadzista) loggedInUser).getZlecenia()) {
                    if (zlecenie.dataZakonczenia == null) {
                        model.addRow(new Object[] {
                                false,
                                zlecenie.getId(),
                                zlecenie.rodzajZlecenia,
                                zlecenie.brygada,
                                zlecenie.listaPrac,
                                zlecenie.dataUtworzenia,
                                zlecenie.dataRozpoczecia,
                                zlecenie.dataZakonczenia
                        });
                    }
                }
            } else {
                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Check", "id", "Rodzaj Zlecenia", "Brygada", "Lista Prac", "Data Utworzenia", "Data Rozpoczecia", "Data Zakonczenia"}) {
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex == 0 ? Boolean.class : String.class;
                    }
                });
            }
            tablesCenter.put("PendingZlecenia", table);


//                int length = ((Brygadzista) loggedInUser).getZlecenia().size();
//                Object[][] data = new Object[length][7];
//
//                // TODO: 06.06.2024 - dodać gettery do Zlecenie.java
//
//                for (int i = 0; i < length; i++) {
//                    Zlecenie zlecenie = ((Brygadzista) loggedInUser).getZlecenia().get(i);
//                    if (zlecenie.dataRozpoczecia == null) {
//                        data[i] = new Object[]{zlecenie.getId(), zlecenie.rodzajZlecenia, zlecenie.brygada, zlecenie.listaPrac, zlecenie.dataUtworzenia, zlecenie.dataRozpoczecia, zlecenie.dataZakonczenia};
//                    }
//                }
//                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//                    public Class<?> getColumnClass(int columnIndex) {
//                        return String.class;
//                    }
//                };
//                table = new JTable(model);
//            } else {
//                table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"id", "Rodzaj Zlecenia", "Brygada", "Lista Prac", "Data Utworzenia", "Data Rozpoczecia", "Data Zakonczenia"}) {
//                    public Class<?> getColumnClass(int columnIndex) {
//                        return String.class;
//                    }
//                });
//            }
//            tablesCenter.put("PendingZlecenia", table);
        }
    }

    public static JTable getTable(String tableName) {
        return tablesCenter.get(tableName);
    }

    // Updatowanie wszystkich tablic i, lub usuwanie
    public static void updateTableData(Uzytkownik loggedInUser) {
        JTable zlecenieTable = tablesCenter.get("Zlecenie");
        JTable pracaTable = tablesCenter.get("Praca");
        JTable pendingTable = tablesCenter.get("PendingZlecenia");
        JTable dzialTable = tablesCenter.get("Dzial pracownikow");
        JTable pracownikTable = tablesCenter.get("Pracownik");
        JTable brygadaTable = tablesCenter.get("Brygada");
        JTable brygadzistaTable = tablesCenter.get("Brygadzista");

        if (brygadzistaTable != null && loggedInUser instanceof Brygadzista) {

            //updatowanie
            //{"Check", "Imie", "Nazwisko", "DOB", "Login", "Haslo", "Inicial", "Brygady", "Zlecenia"};
            DefaultTableModel model = (DefaultTableModel) brygadzistaTable.getModel();
            int lengthRow = model.getRowCount();

            for (int i = 0; i < lengthRow; i++) {
                model.setValueAt(loggedInUser.imie, i, 1);
                model.setValueAt(loggedInUser.nazwisko, i, 2);
                model.setValueAt(loggedInUser.DOB, i, 3);
                model.setValueAt(loggedInUser.login, i, 4);
                model.setValueAt(loggedInUser.haslo, i, 5);
                model.setValueAt(loggedInUser.inicial, i, 6);
                model.setValueAt(((Brygadzista) loggedInUser).getlistaBrygad(), i, 7);
                model.setValueAt(((Brygadzista) loggedInUser).getZlecenia(), i, 8);
            }
        }




        if (brygadaTable != null && loggedInUser instanceof Brygadzista) {
            DefaultTableModel model = (DefaultTableModel) brygadaTable.getModel();
            Brygadzista brygadzista = (Brygadzista) loggedInUser;

            int lengthRow = model.getRowCount();

            for (Brygada br : ((Brygadzista) loggedInUser).getlistaBrygad()) {
                if (getRowIndex(brygadaTable, br.getId()) == -1) {
                    Object[] data = {false, br.getId(), br.nazwa, br.getpracownicyBrygady()};
                    model.addRow(data);
                }
            }

//            for (int i=0; i < lengthRow; i++) {
//
//            }

        }

        if (pracownikTable != null) {
            DefaultTableModel model = (DefaultTableModel) pracownikTable.getModel();
            int lengthRows = model.getRowCount();

            ArrayList<String> used = new ArrayList<>();

            for (int i = 0; i < lengthRows; i++) {
                String s = "";

                s += (String)model.getValueAt(i, 1);
                s += " ";
                s += (String)model.getValueAt(i, 2);

                used.add(s);
            }

            for (Pracownik pr : Pracownik.wszyscyPracownicy) {
                String imie_nazwisko = pr.imie + " " + pr.nazwisko;
                if (!used.contains(imie_nazwisko)) {
                    used.add(imie_nazwisko);
                    Object[] data = {false, pr.imie, pr.nazwisko, pr.DOB, pr.dzial};
                    model.addRow(data);
                }
            }

            //updating

            int lengthRows2 = model.getRowCount();

            for (int i = 0; i < lengthRows2; i ++) {
                String imie_nazwisko = (String) model.getValueAt(i, 1) + " " + (String) model.getValueAt(i, 2);

                for (Pracownik pr : Pracownik.wszyscyPracownicy) {
                    String in = pr.imie + " " + pr.nazwisko;
                    if (imie_nazwisko.equals(in)) {
                        model.setValueAt(pr.imie, i, 1);
                        model.setValueAt(pr.nazwisko, i, 2);
                        model.setValueAt(pr.DOB, i, 3);
                        model.setValueAt(pr.dzial, i, 4);
                    }
                }

            }

        }

        if (dzialTable != null) {
            DefaultTableModel model = (DefaultTableModel) dzialTable.getModel();
            int lengthRows = model.getRowCount();

            ArrayList<String> notUsed = new ArrayList<>();

            for (int i = 0; i < lengthRows; i++) {
                String nazwa = (String)model.getValueAt(i, 1);
                notUsed.add(nazwa);
            }
            for (String i : Dzial.UniqueNames) {
                if (!notUsed.contains(i)) {
                    Object[] a = {false, i};
                    model.addRow(a);
                }
            }
        }

        if (pracaTable != null && loggedInUser instanceof Brygadzista) {
            DefaultTableModel model = (DefaultTableModel) pracaTable.getModel();

            List<Praca> listaThisPrac = new ArrayList<>();

            for (Zlecenie zlec : ((Brygadzista) loggedInUser).getZlecenia()) {
                for (Praca pr : zlec.listaPrac) {
                    for (Praca subpr : pr.praceDoZrealizowania) {
                        listaThisPrac.add(subpr);
                    }
                    listaThisPrac.add(pr);
                }
            }

            // dodawnaie prac, ktorych nie ma w JTable
            for (Praca praca : listaThisPrac) {
                if (getRowIndex(pracaTable, praca.id) == -1) {
                    Object[] data = {false, praca.getthisId(), praca.rodzajPracy, praca.czasPracy, praca.opis, praca.zrealizowane};
                    model.addRow(data);
                }
            }


            // updatowanie prac, ktore sa w jtable
            int lengthRows = model.getRowCount();
            for (int i = 0; i < lengthRows; i++) {
                int id = (int)(model.getValueAt(i, 1));
                for (Praca pr : listaThisPrac) {
                    if (pr.id == id) {
                        model.setValueAt(pr.zrealizowane, i, 5);
                        break;
                    }
                }
            }
        }

        if (zlecenieTable != null && loggedInUser instanceof Brygadzista) {
            DefaultTableModel model = (DefaultTableModel) zlecenieTable.getModel();
            int lengthRow = model.getRowCount();

            // Updatowanie zlecenia
            for (int i = 0; i < lengthRow; i++) {
                int currId = (int)model.getValueAt(i, 1);
                for (Zlecenie zlec : ((Brygadzista) loggedInUser).getZlecenia()) {
                    if (zlec.getId() == currId) {
                        model.setValueAt(zlec.dataUtworzenia, i, 5);
                        model.setValueAt(zlec.dataRozpoczecia, i, 6);
                        model.setValueAt(zlec.dataZakonczenia, i, 7);
                        break;
                    }
                }

            }

            // dodawnaie zlecen, ktore nie są w JTable
            for (Zlecenie zlec : ((Brygadzista) loggedInUser).getZlecenia()) {
                if (getRowIndex(zlecenieTable, zlec.getId()) == -1) {
                    Object[] data = {false, zlec.getId(), zlec.rodzajZlecenia, zlec.brygada, zlec.listaPrac, zlec.dataUtworzenia, zlec.dataRozpoczecia, zlec.dataZakonczenia};
                    model.addRow(data);
                }
            }
        }

        if (pendingTable != null && loggedInUser instanceof Brygadzista) {
            DefaultTableModel model = (DefaultTableModel) pendingTable.getModel();

            // dodawanie tych ktorych nie ma w JTable, a są w Zlecenie.map
            if (refreshPending) {
                for (Zlecenie zlec : ((Brygadzista) loggedInUser).getZlecenia()) {
                    if (getRowIndex(pendingTable, zlec.getId()) == -1) {
                        Object[] data = {false, zlec.getId(), zlec.rodzajZlecenia, zlec.brygada, zlec.listaPrac, zlec.dataUtworzenia, zlec.dataRozpoczecia, zlec.dataZakonczenia};
                        model.addRow(data);
                    }
                }
            }
            // update'owanie tych które już są
            int lengthRow = model.getRowCount();
            for (int i = 0; i < lengthRow; i++) {
                int currId = (int)model.getValueAt(i, 1);
                if (Zlecenie.getZlecenie(currId) != null) {
                    Zlecenie zlec = Zlecenie.getZlecenie(currId);

                    model.setValueAt(zlec.dataUtworzenia, i, 5);
                    model.setValueAt(zlec.dataRozpoczecia, i, 6);
                    model.setValueAt(zlec.dataZakonczenia, i, 7);
                }
            }

            // usuwanie tych, których data zakonczenia jest inna niż null.
            for (int i = lengthRow-1 ; i >= 0 ; i--) {
                LocalDateTime dataZako = (LocalDateTime)model.getValueAt(i, 7);
                if (dataZako != null) {
                    model.removeRow(i);
                }
            }
        }
    }



    private static int getRowIndex(JTable table, int id) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (Integer.parseInt(table.getValueAt(i, 1).toString()) == id) {
                return i;
            }
        }
        return -1;
    }

    public static void clearTables() {
        for (JTable table : tablesCenter.values()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
        }
        tablesCenter.clear();
    }
}