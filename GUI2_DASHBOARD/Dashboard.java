import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;

public class Dashboard extends JPanel implements ActionListener {

    public final int WIDTH = 1000;
    public final int HEIGHT = 700;

    HashMap<String, JButton> buttonsL = new HashMap<>();
    HashMap<String, JButton> buttonsU = new HashMap<>();

    private Uzytkownik loggedInUser;
    private myFrame parentFrame;
    private JLabel centerText;



    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel centerPanel;
    Dashboard(myFrame parentFrame, Uzytkownik user) {
        this.parentFrame = parentFrame;
        this.loggedInUser = user;
        setPanel();
        addComponents();
    }

    public void setPanel() {
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
    }

    private void showMenu(int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuDzial = new JMenuItem("DzialPracownikow");
        JMenuItem menuPracownik = new JMenuItem("Pracownik");


        JMenuItem menuBrygadzista = new JMenuItem("Brygadzista");
        JMenuItem menuBrygada = new JMenuItem("Brygada");
        JMenuItem menuZlecenie = new JMenuItem("Zlecenie");
        JMenuItem menuUzytkownik = new JMenuItem("Uzytkownik");
        JMenuItem menuPraca = new JMenuItem("Praca");

        menuDzial.addActionListener(this);
        menuPracownik.addActionListener(this);
        menuBrygadzista.addActionListener(this);
        menuBrygada.addActionListener(this);
        menuZlecenie.addActionListener(this);
        menuUzytkownik.addActionListener(this);
        menuPraca.addActionListener(this);

        menu.add(menuDzial);
        menu.add(menuPracownik);
        menu.add(menuBrygadzista);
        menu.add(menuBrygada);
        menu.add(menuZlecenie);
        menu.add(menuUzytkownik);
        menu.add(menuPraca);

        menu.show(this, x, y);
    }

    public void addComponents() {

        // buttony klas, i buttony edycji.

        JPanel buttonPanelL = new JPanel();
        buttonPanelL.setLayout(new GridLayout(8, 1));

        JPanel buttonPanelU = new JPanel();
        buttonPanelU.setLayout(new GridLayout(1, 6));

        buttonsU.put("Nowy", createButton("Nowy"));
        buttonsU.put("Edycja", createButton("Edycja"));
        buttonsU.put("Usun", createButton("Usun"));
        buttonsU.put("Zakoncz", createButton("Zakoncz"));
        buttonsU.put("Witaj", createButton("Witaj"));


        buttonsL.put("DzialPracownikow", createButton("DzialPracownikow"));
        buttonsL.put("Pracownik", createButton("Pracownik"));
        buttonsL.put("Uzytkownik", createButton("Uzytkownik"));
        buttonsL.put("Brygadzista", createButton("Brygadzista"));
        buttonsL.put("Brygada", createButton("Brygada"));
        buttonsL.put("Zlecenie", createButton("Zlecenie"));
        buttonsL.put("Praca", createButton("Praca"));

        if (loggedInUser instanceof Brygadzista) {
            buttonsL.put("PendingZlecenia", createButton("PendingZlecenia"));
        }

        addButton2Panel(buttonPanelL, buttonsL);
        addButton2Panel(buttonPanelU, buttonsU);

        buttonsU.get("Witaj").setText("Witaj " + loggedInUser.inicial);

        this.add(buttonPanelL, BorderLayout.WEST);
        this.add(buttonPanelU, BorderLayout.NORTH);

        // centerPanel ma w sobie JTable i JLabel - ktory mowi na jaką klase obecnie patrzymy.

        centerPanel = new JPanel(new BorderLayout());
        centerText = new JLabel("Nazwa");
        centerText.setFont(new Font("Calibri", Font.BOLD, 40));
        centerText.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(centerText, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        initializeTable();

        // buttony Wyloguj i Zmien Haslo
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));
        JButton wylogujButton = createButton("Wyloguj");
        southPanel.add(wylogujButton);
        JButton zmienHaslo = createButton("Zmien Haslo");
        southPanel.add(zmienHaslo);

        this.add(southPanel, BorderLayout.SOUTH);
    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setBackground(Color.decode("#0077B6"));
        button.setForeground(Color.white);
        button.setFont(new Font("Calibri", Font.BOLD, 20));
        button.addActionListener(this);
        return button;
    }

    private void addButton2Panel(JPanel panel, HashMap<String, JButton> buttons){
        for (JButton button : buttons.values()) {
            panel.add(button);
        }
    }

    private void initializeTable() {
        tableModel = new DefaultTableModel(0, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        //default
        updateTableContent("Pracownik");
        if (loggedInUser instanceof Brygadzista) {
            updateTableContent("PendingZlecenia");
        }
    }
    private void updateTableContent(String className) {
        tableModel.setRowCount(0);

        centerText.setText(className);

        switch (className) {
            case "DzialPracownikow":
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nazwa", "ListaPracownikow"});
                for (DzialPracownikow dzial : DzialPracownikow.listaWszystkichDzialow.values()) {
                    StringBuilder pracownicyStr = new StringBuilder();
                    tableModel.addRow(new Object[]{dzial.id, dzial.nazwa, "ListaPracownikow"});
                }
                table.getColumn("ListaPracownikow").setCellRenderer(new ButtonRenderer());
                table.getColumn("ListaPracownikow").setCellEditor(new ButtonEditor(new JCheckBox(), "DzialPracownikow"));
                break;
            case "Pracownik":
                tableModel.setColumnIdentifiers(new String[]{"ID", "Imie", "Nazwisko", "DzialPracownikow", "dataUrodzenia"});
                for (Pracownik pr : Pracownik.mapaWszystkichPracownikow.values()) {
                    String dzial = pr.dzialPracownikow != null ? pr.dzialPracownikow.nazwa : "NONE";
                    tableModel.addRow(new Object[]{pr.id, pr.imie, pr.nazwisko, dzial, pr.dataUrodzenia});
                }
                break;

            case "Uzytkownik":
                tableModel.setColumnIdentifiers(new String[]{"ID", "Imie", "Nazwisko", "dataUrodzenia", "DzialPracownikow", "Login", "Haslo", "Inicial"});
                for (Pracownik pr : Pracownik.mapaWszystkichPracownikow.values()) {
                    if (!(pr instanceof Brygadzista) && (pr instanceof Uzytkownik)) {
                        Uzytkownik user = (Uzytkownik) pr;
                        String departmentName = user.dzialPracownikow != null ? user.dzialPracownikow.nazwa : "NONE";
                        tableModel.addRow(new Object[]{user.id, user.imie, user.nazwisko, user.dataUrodzenia, departmentName, user.login, user.haslo, user.inicial});
                    }
                }
                break;

            case "Brygadzista":
                tableModel.setColumnIdentifiers(new String[]{"ID", "Imie", "Nazwisko", "dataUrodzenia", "DzialPracownikow", "Login", "Inicial", "Brygady", "Zlecenia"});
                for (Pracownik pr : Pracownik.mapaWszystkichPracownikow.values()) {
                    if (pr instanceof Brygadzista) {
                        Brygadzista brygadzista = (Brygadzista) pr;
                        String departmentName = brygadzista.dzialPracownikow != null ? brygadzista.dzialPracownikow.nazwa : "NONE";
                        tableModel.addRow(new Object[]{brygadzista.id, brygadzista.imie, brygadzista.nazwisko, brygadzista.dataUrodzenia, departmentName, brygadzista.login, brygadzista.inicial, brygadzista.brygady.size(), brygadzista.listaZlecen.size()});
                    }
                }
                break;
            case "Brygada":
                tableModel.setColumnIdentifiers(new String[]{"ID", "Nazwa", "Liczba pracownikow"});
                if (loggedInUser instanceof Brygadzista) {
                    for (Brygada bryg : Brygada.mapaBrygad.values()) {
                        tableModel.addRow(new Object[]{bryg.id, bryg.nazwa, bryg.listaPracownikow.size()});
                    }
                }
                break;
            case "Zlecenie":
                if (loggedInUser instanceof Brygadzista) {
                    tableModel.setColumnIdentifiers(new String[]{"ID", "StanZlecenia", "DataUtworzenia", "DataRealizacji", "DataZakonczenia", "ListaPrac"});
                    for (Zlecenie zlecenie : ((Brygadzista) loggedInUser).listaZlecen.values()) {
                        tableModel.addRow(new Object[]{zlecenie.id, zlecenie.stanZlecenia, zlecenie.dataUtworzenia, zlecenie.dataRealizacji, zlecenie.dataZakonczenia, "ListaPrac"});
                    }
                    table.getColumn("ListaPrac").setCellRenderer(new ButtonRenderer());
                    table.getColumn("ListaPrac").setCellEditor(new ButtonEditor(new JCheckBox(), "Zlecenie"));

                }
                break;
            case "Praca":
                if (loggedInUser instanceof Brygadzista) {
                    tableModel.setColumnIdentifiers(new String[]{"ID", "RodzajPracy", "CzasPracy", "Opis", "CzyZrealizowane"});
                    for (Zlecenie zlec : ((Brygadzista) loggedInUser).listaZlecen.values()) {
                        for (Praca pr : zlec.prace) {
                            tableModel.addRow(new Object[]{pr.id, pr.rodzajPracy, pr.czasPracy, pr.opis, pr.czyZrealizowane});
                        }
                    }
                }
                break;
            case "PendingZlecenia":
                if (loggedInUser instanceof Brygadzista) {
                    tableModel.setColumnIdentifiers(new String[]{"ID", "StanZlecenia", "Brygady", "Prace", "DataUtworzenia", "DataRealizacji", "DataZakonczenia"});
                    for (Zlecenie zlecenie : ((Brygadzista) loggedInUser).listaZlecen.values()) {
                        if (zlecenie.dataZakonczenia == null) {
                            tableModel.addRow(new Object[]{zlecenie.id, zlecenie.stanZlecenia, zlecenie.brygada.listaPracownikow.size(), zlecenie.prace.size(), zlecenie.dataUtworzenia, zlecenie.dataRealizacji, zlecenie.dataZakonczenia});
                        }
                    }
                }
                break;
        }
    }
    private void editTableRow(JTable table) {

        // czy jakikolwiek row jest wybrany
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Nie wybrano żadnego wiersza.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // czy wiecej niz 1 wiersz jest wybrany
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this, "Mozna edytowac tylko jeden wiersz na raz", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table.getSelectedRow();
        int columnCount = table.getColumnCount();

        //skipowanie kolumny z ID, nie chce aby ID było edytowalne
        Object[] wData = new Object[columnCount - 1];

        for (int i = 1; i < columnCount; i++) {
            if (!table.getColumnName(i).equals("ListaPracownikow") && !table.getColumnName(i).equals("ListaPrac")) {
                wData[i - 1] = table.getValueAt(row, i);
            }
        }

        //tworzenie panelu do edytowania
        JPanel editPanel = new JPanel(new GridLayout(columnCount - 1, 2));
        JTextField[] textf = new JTextField[columnCount - 1];
        for (int i = 1; i < columnCount; i++) {
            if (!table.getColumnName(i).equals("ListaPracownikow") && !table.getColumnName(i).equals("ListaPrac")) {
                JLabel label = new JLabel(table.getColumnName(i) + ":");
                Object value = table.getValueAt(row, i);
                if (value != null) {
                    wData[i - 1] = value;
                } else {
                    wData[i - 1] = "";
                }
                JTextField textField = new JTextField(wData[i - 1].toString());
                textf[i - 1] = textField;
                editPanel.add(label);
                editPanel.add(textField);
            }
        }

        JDialog dialog = new JDialog((Frame) null, "Edit Row", true);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i < columnCount; i++) { // Start from 1 to skip the ID column
                    if (!table.getColumnName(i).equals("ListaPracownikow") && !table.getColumnName(i).equals("ListaPrac")) {
                        table.setValueAt(textf[i - 1].getText(), row, i);
                    }
                }

                dialog.dispose();
            }
        });

        dialog.getContentPane().add(editPanel, BorderLayout.CENTER);
        dialog.getContentPane().add(submitButton, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateClassVars();
    }
    public void updateClassVars() {

        String whichClass = centerText.getText();

        switch (whichClass) {
            case "DzialPracownikow":
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int id = (int) tableModel.getValueAt(i, 0);

                    DzialPracownikow currDzial = DzialPracownikow.listaWszystkichDzialow.get(id);

                    if (currDzial != null) {
                        currDzial.nazwa = (String)tableModel.getValueAt(i, 1);
                    } else {
                        System.out.println("Error in changing dzial's variables.");
                    }
                }
                break;
            case "Pracownik":
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int id = (int) tableModel.getValueAt(i, 0);

                    Pracownik currPracownik = Pracownik.mapaWszystkichPracownikow.get(id);

                    if (currPracownik != null) {
                        currPracownik.imie = (String)tableModel.getValueAt(i, 1);
                        currPracownik.nazwisko = (String)tableModel.getValueAt(i, 2);
                        currPracownik.dzialPracownikow = getDzialbyNazwa((String)tableModel.getValueAt(i, 3));
                        try {
                            currPracownik.dataUrodzenia = LocalDate.parse(tableModel.getValueAt(i, 4).toString());
                        } catch (DateTimeParseException e) {
                            System.out.println("oopsie woopsie wrong date time format");
                        }
                    } else {
                        System.out.println("Error in changing variables.");
                    }
                }
                break;
            case "Brygadzista":
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int id = (int) tableModel.getValueAt(i, 0);

                    Brygadzista currBrygadzista = Brygadzista.mapaBrygadzistow.get(id);

                    if (currBrygadzista != null) {
                        currBrygadzista.imie = (String)tableModel.getValueAt(i, 1);
                        currBrygadzista.nazwisko = (String)tableModel.getValueAt(i, 2);
                        try {
                            currBrygadzista.dataUrodzenia = LocalDate.parse(tableModel.getValueAt(i, 3).toString());
                        } catch (DateTimeParseException e) {
                            System.out.println("oopsie woopsie wrong date format");
                        }
                        currBrygadzista.dzialPracownikow = getDzialbyNazwa((String)tableModel.getValueAt(i, 4));
                        currBrygadzista.login = (String)tableModel.getValueAt(i, 5);
                    } else {
                        System.out.println("Error in changing brygadzista's variables.");
                    }
                }
                break;
            case "Uzytkownik":
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int id = (int) tableModel.getValueAt(i, 0);

                    Uzytkownik currUzytkownik = Uzytkownik.mapaWszystkichUzytkownikow.get(id);

                    if (currUzytkownik != null) {
                        currUzytkownik.imie = (String)tableModel.getValueAt(i, 1);
                        currUzytkownik.nazwisko = (String)tableModel.getValueAt(i, 2);
                        try {
                            currUzytkownik.dataUrodzenia = LocalDate.parse(tableModel.getValueAt(i, 3).toString());
                        } catch (DateTimeParseException e) {
                            System.out.println("oopsie woopsie wrong date format");
                        }
                        currUzytkownik.dzialPracownikow = getDzialbyNazwa((String)tableModel.getValueAt(i, 4));
                        currUzytkownik.login = (String)tableModel.getValueAt(i, 5);
                        currUzytkownik.haslo = (String)tableModel.getValueAt(i, 6);
                        currUzytkownik.inicial = currUzytkownik.getInicial(currUzytkownik.imie, currUzytkownik.nazwisko);
                    } else {
                        System.out.println("Error in changing uzytkownik's variables.");
                    }
                }
                break;
            case "Praca":

                if (loggedInUser instanceof Brygadzista) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        int id = (int) tableModel.getValueAt(i, 0);

                        Praca currPraca = null;
                        for (Zlecenie zlec : ((Brygadzista) loggedInUser).listaZlecen.values()) {
                            for (Praca prac : zlec.prace) {
                                if (prac.id == id) {
                                    currPraca = prac;
                                    //  public enum RodzajPracy { OGOLNA, MONTAZ, DEMONTAZ, WYMIANA }
                                    String rodzajp = tableModel.getValueAt(i, 1).toString();

                                    Praca.RodzajPracy rodzajPracy = null;
                                    switch (rodzajp.toUpperCase()) {
                                        case "OGOLNA":
                                            rodzajPracy = Praca.RodzajPracy.OGOLNA;
                                            break;
                                        case "MONTAZ":
                                            rodzajPracy = Praca.RodzajPracy.MONTAZ;
                                            break;
                                        case "DEMONTAZ":
                                            rodzajPracy = Praca.RodzajPracy.DEMONTAZ;
                                            break;
                                        case "WYMIANA":
                                            rodzajPracy = Praca.RodzajPracy.WYMIANA;
                                            break;
                                    }
                                    currPraca.rodzajPracy = rodzajPracy;
                                    currPraca.czasPracy = Integer.parseInt(tableModel.getValueAt(i, 2).toString());
                                    currPraca.opis = (String) tableModel.getValueAt(i, 3);
                                    String czyZrealizowane = tableModel.getValueAt(i, 4).toString();
                                    boolean cz = false;
                                    switch (czyZrealizowane.toUpperCase()) {
                                        case "TRUE":
                                            cz = true;
                                            break;
                                        case "FALSE":
                                            cz = false;
                                            break;
                                    }
                                    currPraca.czyZrealizowane = cz;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            case "Brygada":
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int id = (int) tableModel.getValueAt(i, 0);

                    Brygada currBrygada = Brygada.mapaBrygad.get(id);

                    if (currBrygada != null) {
                        currBrygada.nazwa = (String) tableModel.getValueAt(i, 1);
//                        currBrygada.listaPracownikow = (boolean) tableModel.getValueAt(i, 4);
                    } else {
                        System.out.println("Error in changing brygada variables.");
                    }
                }
                break;
            case "Zlecenie":
                if (loggedInUser instanceof Brygadzista) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        int id = (int) tableModel.getValueAt(i, 0);

                        Zlecenie currZlecenie = ((Brygadzista) loggedInUser).listaZlecen.get(id);

                        if (currZlecenie != null) {
                            String stanZlecenia = tableModel.getValueAt(i, 1).toString();
                            switch (stanZlecenia.toUpperCase()) {
                                case "PLANOWANE":
                                    currZlecenie.stanZlecenia = currZlecenie.stanZlecenia.PLANOWANE;
                                    break;
                                case "NIEPLANOWANE":
                                    currZlecenie.stanZlecenia = currZlecenie.stanZlecenia.NIEPLANOWANE;
                                    break;
                                case "REALIZOWANE":
                                    currZlecenie.stanZlecenia = currZlecenie.stanZlecenia.REALIZOWANE;
                                    break;
                                case "ZAKONCZONE":
                                    currZlecenie.stanZlecenia = currZlecenie.stanZlecenia.ZAKONCZONE;
                                    break;

                            }

                            try {
                                if (tableModel.getValueAt(i, 2) != null && !tableModel.getValueAt(i, 2).toString().isEmpty()) {
                                    currZlecenie.dataUtworzenia = LocalDateTime.parse(tableModel.getValueAt(i, 2).toString());
                                }
                                if (tableModel.getValueAt(i, 3) != null && !tableModel.getValueAt(i, 3).toString().isEmpty()) {
                                    currZlecenie.dataRealizacji = LocalDateTime.parse(tableModel.getValueAt(i, 3).toString());
                                }
                                if (tableModel.getValueAt(i, 4) != null && !tableModel.getValueAt(i, 4).toString().isEmpty()) {
                                    currZlecenie.dataZakonczenia = LocalDateTime.parse(tableModel.getValueAt(i, 4).toString());
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("oopsie woopsie, you tried parsing data in the wrong format!");
                            }


                        } else {
                            System.out.println("cos poszlo nie tak");
                        }
                    }
                }
                break;


        }

        updateTableContent(whichClass);
    }

    public DzialPracownikow getDzialbyNazwa(String nazwaDzialu) {
        for (DzialPracownikow dzial : DzialPracownikow.listaWszystkichDzialow.values()) {
            if (nazwaDzialu.equals(dzial.nazwa)) {
                return dzial;
            }
        }
        return null;
    }
    public void deleteData() {
        String whichClass = centerText.getText();

        int[] rowIds = table.getSelectedRows();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        System.out.println(Arrays.toString(rowIds));
        switch (whichClass) {
            case "DzialPracownikow":
                System.out.println("dzial usuniety");
                for(int id : rowIds) {
                    DzialPracownikow currDzial = DzialPracownikow.listaWszystkichDzialow.get((int)model.getValueAt(id, 0));
                    currDzial.deleteDzial();

                }
                break;
            case "Pracownik":
                for(int id : rowIds) {
                    Pracownik currPracownik = Pracownik.mapaWszystkichPracownikow.get((int)model.getValueAt(id, 0));
                    currPracownik.deletePracownik();
                }
                break;
            case "Uzytkownik":
                for(int id : rowIds) {
                    Uzytkownik currUzytkownik = Uzytkownik.mapaWszystkichUzytkownikow.get((int)model.getValueAt(id, 0));
                    currUzytkownik.deleteUzytkownik();
                }
                break;
            case "Brygadzista":
                for(int id : rowIds) {
                    Brygadzista currBrygadzista = Brygadzista.mapaBrygadzistow.get((int)model.getValueAt(id, 0));
                    currBrygadzista.deleteBrygadzista();
                }
                break;
            case "Brygada":
                for(int id : rowIds) {
                    Brygada currBrygada = Brygada.mapaBrygad.get((int)model.getValueAt(id, 0));
                    currBrygada.deleteBrygada();
                }
                break;
            case "Zlecenie":
                for(int id : rowIds) {
                    Zlecenie currZlecenie = Zlecenie.mapaWszystkichZlecen.get((int)model.getValueAt(id, 0));
                    currZlecenie.deleteZlecenie();
                }
                break;
            case "Praca":
                for (int id : rowIds) {
                    Praca praca = Praca.mapaWszystkichPrac.get((int)model.getValueAt(id, 0));
                    praca.deletePraca();
                }
                break;
        }
        updateTableContent(whichClass);

    }
    public void logout() {
        loggedInUser = null;
        parentFrame.showLoginPage();
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {

            String whichClass = ((JMenuItem) e.getSource()).getText();

            switch (whichClass) {
                case "DzialPracownikow":
                    NowyDzial.newDzial();
                    break;
                case "Brygadzista":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTableContent("DzialPracownikow");
                            new NowyBrygadzista(table).setVisible(true);
                        }
                    });
                    break;
                case "Brygada":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTableContent("Brygadzista");
                            new NowyBrygada(table).setVisible(true);
                        }
                    });
                    break;
                case "Zlecenie":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTableContent("Brygada");
                            new NowyZlecenie(table).setVisible(true);
                        }
                    });
                    break;
                case "Uzytkownik":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTableContent("DzialPracownikow");
                            new NowyUzytkownik(table).setVisible(true);
                        }
                    });
                    break;
                case "Praca":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new NowyPraca().setVisible(true);
                        }
                    });
                    break;
                case "Pracownik":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateTableContent("DzialPracownikow");
                            new NowyPracownik(table).setVisible(true);
                        }
                    });
                    break;
            }
            updateTableContent(centerText.getText());

        } else if (e.getSource() instanceof JButton) {
            JButton buttonPressed = (JButton) e.getSource();
            String textOfButtonPressed = buttonPressed.getText();

            switch (textOfButtonPressed) {
                case "Zakoncz":
                    if (loggedInUser instanceof Brygadzista) {
                        if (!centerText.getText().equals("PendingZlecenia")) {
                            updateTableContent("PendingZlecenia");
                        }
                        int[] selectedRow = table.getSelectedRows();
//                        int lengthRows = selectedRow.length;
//
//
//                        for (int i = 0; i < lengthRows; i++) {
//                            int zlecenieId = (int) tableModel.getValueAt(i, 0);
//                            ((Brygadzista) loggedInUser).listaZlecen.get(zlecenieId).dataZakonczenia = LocalDateTime.now();
//                        }
//                    }
                        for (int i : selectedRow) {
                            int currId = (int) tableModel.getValueAt(i, 0);
                            Zlecenie zlec = Zlecenie.mapaWszystkichZlecen.get(currId);
                            for (Praca pr : zlec.prace) {
                                pr.czyZrealizowane = true;
                            }
                            zlec.dataZakonczenia = LocalDateTime.now();
                        }
                    }
                    updateTableContent("PendingZlecenia");

                    break;

                case "Wyloguj":
                    logout();
                    break;
                case "Nowy":
                    showMenu(buttonPressed.getX(), buttonPressed.getY());
                    break;
                case "Zmien Haslo":
                    String newPass = JOptionPane.showInputDialog("Enter your new password");
                    String checkPass = newPass != null ? newPass : "";
                    if (!checkPass.isEmpty()) {
                        loggedInUser.haslo = newPass;
                        logout();
                    } else {
                        System.out.println("sumting wong");
                    }
                    break;
                case "Edycja":
                    editTableRow(table);
                    break;
                case "Usun":
                    System.out.println("usun");
                    deleteData();
                    updateTableContent(centerText.getText());
                    break;

                case "DzialPracownikow":
                case "Pracownik":
                case "Uzytkownik":
                case "Brygadzista":
                case "Zlecenie":
                case "Praca":
                case "PendingZlecenia":
                case "Brygada":
                    updateTableContent(textOfButtonPressed);
                    break;
            }
        }
    }
}