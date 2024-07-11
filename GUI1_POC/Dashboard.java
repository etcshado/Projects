import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.*;

public class Dashboard extends JPanel implements ActionListener {

    public final int WIDTH = 1000;
    public final int HEIGHT = 700;

    ArrayList<JButton> buttonsL = new ArrayList<>();
    ArrayList<JButton> buttonsU = new ArrayList<>();

    private Uzytkownik loggedInUser;
    private JScrollPane centerScrollPane;
    private myFrame parentFrame;
    private TableManager tableManager;
    private JLabel centerText;
    private Timer timer;

    Dashboard(myFrame parentFrame, Uzytkownik user) {
        this.parentFrame = parentFrame;
        this.loggedInUser = user;
        this.tableManager = new TableManager(user);
        setPanel();
        addComponents();
        setTimer();
    }

    public void setPanel() {
        this.setBounds(0, 0, myFrame.WIDTH, myFrame.HEIGHT);
        this.setLayout(new BorderLayout());
    }
    private void setTimer() {
        timer = new Timer(1000, this);
        timer.start();
    }

    // Buttons
    public JButton Dzial() { return new JButton("Dzial pracownikow"); }

    public JButton Pracownik() { return new JButton("Pracownik"); }
    public JButton Uzytkownik() { return new JButton("Uzytkownik"); }
    public JButton Brygadzista() { return new JButton("Brygadzista"); }
    public JButton Brygada() { return new JButton("Brygada"); }
    public JButton Zlecenie() { return new JButton("Zlecenie"); }
    public JButton Praca() { return new JButton("Praca"); }
    public JButton PendingZlecenia() { return new JButton("PendingZlecenia");}
    public JButton Wyloguj() { return new JButton("Wyloguj"); }

    public JButton Nowy() {
        JButton button = new JButton("Nowy");
        return button;
    }
    public JButton Edycja() { return new JButton("Edycja"); }
    public JButton Usun() { return new JButton("Usun"); }

    public JButton Zakoncz() { return new JButton("Zakoncz"); }

    public JButton Witaj() {
        return new JButton("Witaj, " + loggedInUser.inicial);
    }

    public JButton ZmienHaslo() {
        JButton button = new JButton("Zmien haslo");

        button.setFocusable(false);
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#0077B6"));
        button.setFont(new Font("Calibri", Font.BOLD, 20));
        button.addActionListener(this);
        return button;
    }

    private void showMenu(int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuDzial = new JMenuItem("Dzial");
        JMenuItem menuBrygadzista = new JMenuItem("Brygadzista");
        JMenuItem menuBrygada = new JMenuItem("Brygada");
        JMenuItem menuZlecenie = new JMenuItem("Zlecenie");
        JMenuItem menuUzytkownik = new JMenuItem("Uzytkownik");
        JMenuItem menuPraca = new JMenuItem("Praca");

        menuDzial.addActionListener(this);
        menuBrygadzista.addActionListener(this);
        menuBrygada.addActionListener(this);
        menuZlecenie.addActionListener(this);
        menuUzytkownik.addActionListener(this);
        menuPraca.addActionListener(this);

        menu.add(menuDzial);
        menu.add(menuBrygadzista);
        menu.add(menuBrygada);
        menu.add(menuZlecenie);
        menu.add(menuUzytkownik);
        menu.add(menuPraca);

        menu.show(this, x, y);
    }

    public void addComponents() {
        JPanel buttonPanelL = new JPanel();
        buttonPanelL.setLayout(new GridLayout(8, 1));

        JPanel buttonPanelU = new JPanel();
        buttonPanelU.setLayout(new GridLayout(1, 6));

        buttonsU.add(Nowy());
        buttonsU.add(Edycja());
        buttonsU.add(Usun());
        buttonsU.add(Zakoncz());
        buttonsU.add(Witaj());

        for (JButton button : buttonsU) {
            button.setFocusable(false);
            button.setBackground(Color.decode("#90E0EF"));
            button.setForeground(Color.white);
            button.setFont(new Font("Calibri", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanelU.add(button);
        }

        buttonsL.add(Dzial());
        buttonsL.add(Pracownik());
        buttonsL.add(Uzytkownik());
        buttonsL.add(Brygadzista());
        buttonsL.add(Brygada());
        buttonsL.add(Zlecenie());
        buttonsL.add(Praca());
        buttonsL.add(PendingZlecenia());
        buttonsL.add(Wyloguj());

        if (! (loggedInUser instanceof Brygadzista)) {
            buttonsU.remove(3);
            buttonsL.remove(7);
        } else {
            buttonsL.remove(2);
        }

        for (JButton button : buttonsL) {
            button.setFocusable(false);
            button.setForeground(Color.white);
            button.setBackground(Color.decode("#0077B6"));
            button.setFont(new Font("Calibri", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanelL.add(button);
        }

        this.add(buttonPanelL, BorderLayout.WEST);
        this.add(buttonPanelU, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerText = new JLabel("Nazwa");
        centerText.setFont(new Font("Calibri", Font.BOLD, 40));

        centerText.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(centerText, BorderLayout.NORTH);


        centerScrollPane = new JScrollPane();
        centerPanel.add(centerScrollPane, BorderLayout.CENTER);
//        this.add(centerScrollPane, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);

        this.add(ZmienHaslo(), BorderLayout.SOUTH);


        if (loggedInUser instanceof Brygadzista) {
            centerText.setText("PendingZlecenia");
            centerScrollPane.setViewportView(tableManager.getTable("PendingZlecenia"));
            revalidate();
            repaint();
        }



    }

    public void logout() {
        TableManager.clearTables();
        loggedInUser = null;
        TableManager.refreshPending = true;

        parentFrame.showLoginPage();
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem) {

            String command = ((JMenuItem) e.getSource()).getText();

            switch (command) {
                case "Dzial":
                    NowyDzial.newDzial();
                    break;
                case "Brygadzista":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new NowyBrygadzista().setVisible(true);
                        }
                    });
                    break;
                case "Brygada":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new NowyBrygada().setVisible(true);
                        }
                    });
                    break;
                case "Zlecenie":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new NowyZlecenie().setVisible(true);
                        }
                    });
                    break;
                case "Uzytkownik":
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new NowyUzytkownik().setVisible(true);
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

            }

        } else if (e.getSource() instanceof Timer) {
            TableManager.updateTableData(loggedInUser);
            revalidate();
            repaint();
        } else if (e.getSource() instanceof JButton){
            TableManager.updateTableData(loggedInUser);

            String nazwa = ((JButton) e.getSource()).getText();

            JTable table = TableManager.getTable(nazwa);
            if (nazwa.equals("Wyloguj")) {
                logout();
                parentFrame.showLoginPage();
            } else if (nazwa.equals("Edycja")) {

                JTable currTable = null;

                currTable = TableManager.getTable(centerText.getText());

                if (currTable != null) {
                    int selectedRow = currTable.getSelectedRow();
                    if (selectedRow != -1 && currTable.getSelectedRowCount() == 1) {
                        showEdycja(currTable, selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(this, "Please select exactly one row to edit.");
                    }
                }

            } else if (nazwa.equals("Usun")) {
                String currTab = centerText.getText();

                switch (currTab) {
                    case "Dzial pracownikow":
                        deleteDzial(TableManager.getTable("Dzial pracownikow"));
                        break;
                    case "Pracownik":
                        deletePracownik(TableManager.getTable("Pracownik"));
                        break;
                    case "Brygadzista":
                        deleteBrygadzista(TableManager.getTable("Brygadzista"));
                        break;
                    case "Brygada":
                        deleteBrygada(TableManager.getTable("Brygada"));
                        break;
                    case "Zlecenie":
                        deleteZlecenie(TableManager.getTable("Zlecenie"));
                        break;
                    case "Praca":
                        if (loggedInUser instanceof Brygadzista) {
                            deletePraca(TableManager.getTable("Praca"));
                            break;
                        }
                    case "PendingZlecenia":
                        deletePending(TableManager.getTable("PendingZlecenia"));
                        TableManager.refreshPending = false;
                        break;
                }

            }
            else if (nazwa.equals("Zakoncz")) {

                centerText.setText("Zlecenie");
                centerScrollPane.setViewportView(TableManager.getTable("Zlecenie"));
                JTable zleceniaTable = TableManager.getTable("Zlecenie");

                DefaultTableModel model = (DefaultTableModel) zleceniaTable.getModel();
                int lengthRows = model.getRowCount();

                for (int i = 0; i < lengthRows; i++) {
                    boolean end = (boolean)model.getValueAt(i, 0);
                    if (end) {
                        int id = (int)model.getValueAt(i, 1);

                        Zlecenie zlecenie = Zlecenie.getZlecenie(id);
                        if (zlecenie != null) {
                            zlecenie.dataZakonczenia = LocalDateTime.now();
                            System.out.println("Zlecenie " + id + " ended at " + zlecenie.dataZakonczenia);

                            for (Praca pr : zlecenie.listaPrac) {
                                for (Praca subpr : pr.praceDoZrealizowania) {
                                    subpr.zrealizowane = true;
                                    subpr.interrupt();
                                }
                                pr.zrealizowane = true;
                                pr.interrupt();
                                System.out.println("Praca " + pr + " marked as: " + pr.zrealizowane);
                            }
                        }
                    }
                }
            } else if (nazwa.equals("Zmien haslo")) {
                String newPassword = JOptionPane.showInputDialog(null, "Enter your new password");
                if (newPassword == null) {
                    JOptionPane.showMessageDialog(null, "Password must not be empty!");
                }
                assert newPassword != null;
                if (!newPassword.isEmpty()) {
                    loggedInUser.haslo = newPassword;
                    TableManager.updateTableData(loggedInUser);
                    JOptionPane.showMessageDialog(null, "Succesfully changed password, logging out.");
                    logout();
                }

            } else if (buttonsL.contains((JButton)e.getSource())) {
                centerText.setText(nazwa);
                if (table != null) {
                    centerScrollPane.setViewportView(table);
                    centerScrollPane.revalidate();
                    centerScrollPane.repaint();
                }
            }
            else if (nazwa.equals("Nowy")) {
                showMenu(getX(), getY());
            }
            revalidate();
            repaint();
        }
    }

    private void deleteDzial(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int lengthRows = model.getRowCount();


        for (int i = lengthRows-1; i >= 0; i--) {
            boolean check_if_delete = (boolean)model.getValueAt(i, 0);
            String nazwaDzialu = (String)model.getValueAt(i, 1);

            if (check_if_delete) {
                for (Dzial d : Dzial.listaDzialow) {
                    if (d.nazwa.equals(nazwaDzialu)) {
                        d.deleteDzial();
                        break;
                    }
                }
            }
            model.removeRow(i);
        }

    }
    private void deletePracownik(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int lengthRows = model.getRowCount();


        for (int i = lengthRows-1; i >= 0; i--) {
            boolean check_if_delete = (boolean)model.getValueAt(i, 0);
            String imie_nazwisko = (String) model.getValueAt(i, 1) + " " + (String)model.getValueAt(i, 2);

            if (check_if_delete) {
                Pracownik.deletePracownik(imie_nazwisko);
            }
            model.removeRow(i);
        }

    }
    private void deleteBrygadzista(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (loggedInUser instanceof Brygadzista) {
            ((Brygadzista) loggedInUser).deleteBrygadzista();
            model.removeRow(0);
        }
    }
    private void deleteBrygada(JTable table) {

        if (loggedInUser instanceof Brygadzista) {

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int lengthRows = model.getRowCount();

            for (int i = lengthRows - 1; i >= 0; i--) {
                boolean check_to_delete = (boolean) model.getValueAt(i, 0);
                int id = (int) model.getValueAt(i, 1);

                if (check_to_delete) {

                    for (Brygada brygada : new ArrayList<>(((Brygadzista) loggedInUser).getlistaBrygad())) {
                        if (brygada.getId() == id){
                            try {
                                brygada.deleteBrygada();
                            } catch (ConcurrentModificationException e) {
                                System.out.println("oopsie woopsie, you tried deleting Brygada, which zlecenie was an active thread.");
                            }
                            model.removeRow(i);
                        }
                    }

                }

            }
        }
    }
    public void deleteZlecenie(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int lengthRows = model.getRowCount();

        for (int i = lengthRows-1; i >= 0; i--) {

            boolean check_to_delete = (boolean)model.getValueAt(i, 0);
            int currId = (int)model.getValueAt(i, 1);

            if (check_to_delete) {
                Zlecenie.getZlecenie(currId).deleteZlecenie(currId);
                model.removeRow(i);
            }
        }
    }
    public void deletePraca(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int lengthRows = model.getRowCount();

        for (int i = lengthRows-1; i >= 0; i--) {

            boolean check_to_delete = (boolean)model.getValueAt(i, 0);
            int currId = (int)model.getValueAt(i, 1);

            if (check_to_delete) {

                Praca.getPraca(currId).deletePraca((Brygadzista) loggedInUser);
                model.removeRow(i);

            }
        }
    }
    public void deletePending(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int lengthRows = model.getRowCount();

        for (int i = lengthRows-1 ; i>=0 ; i--) {

            boolean check_to_delete = (boolean)model.getValueAt(i, 0);
            int currId = (int)model.getValueAt(i, 1);

            if (check_to_delete) {

                model.removeRow(i);
            }
        }
    }
    private void showEdycja(JTable table, int rowIndex) {
        JFrame editFrame = new JFrame("Edycja");
        editFrame.setSize(400, 300);
        editFrame.setLayout(new GridLayout(0, 2));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int columnCount = model.getColumnCount();
        JTextField[] textFields = new JTextField[columnCount];

        for (int i = 0; i < columnCount; i++) {
            String columnName = model.getColumnName(i);
            Object value = model.getValueAt(rowIndex, i);

            editFrame.add(new JLabel(columnName));
            textFields[i] = new JTextField(String.valueOf(value));
            editFrame.add(textFields[i]);
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < columnCount; i ++) {
//                    model.setValueAt(textFields[i].getText(), rowIndex, i); //impostor
                    Class<?> columnClass = model.getColumnClass(i);
                    if (columnClass == Boolean.class) {
                        model.setValueAt(Boolean.parseBoolean(textFields[i].getText()), rowIndex, i);
                    } else if (columnClass == Integer.class) {
                        model.setValueAt(Integer.parseInt(textFields[i].getText()), rowIndex, i);
                    } else if (columnClass == LocalDateTime.class) {
                        model.setValueAt(LocalDateTime.parse(textFields[i].getText()), rowIndex, i);
                    } else {
                        model.setValueAt(textFields[i].getText(), rowIndex, i);
                    }
                }
                String id = (String)model.getValueAt(rowIndex, 1);
                updateObjectInstance(centerText.getText(), id, rowIndex, model);
                editFrame.dispose();
            }
        });

        editFrame.add(new JLabel());
        editFrame.add(saveButton);
        editFrame.setVisible(true);
    }
    private void updateObjectInstance(String tableName, String id, int rowIndex, DefaultTableModel model) {
        switch (tableName) {
            case "Dzial pracownikow":
                updateDzialInstance(id, rowIndex, model);
                break;
//            case "Pracownik":
//                updatePracownikInstance(id, rowIndex, model);
//                break;
//            case "Brygadzista":
//                updateBrygadzistaInstance(id, rowIndex, model);
//                break;
            case "Brygada":
                updateBrygadaInstance(id, rowIndex, model);
                break;
            case "Zlecenie":
                updateZlecenieInstance(id, rowIndex, model);
                break;
            case "Praca":
                updatePracaInstance(id, rowIndex, model);
                break;
            // Add cases for other tables as needed
        }
    }
    private void updateDzialInstance(String id, int rowIndex, DefaultTableModel model) {
        // Retrieve the Dzial object using the unique identifier (ID)
        Dzial dzial = Dzial.getDzial(id);

        if (dzial != null) {
            System.out.println("dizala?");
            dzial.nazwa = (String)model.getValueAt(rowIndex, 1);
            Dzial.UniqueNames.remove(id);
            Dzial.UniqueNames.add(dzial.nazwa);
        }

//        for (Pracownik pr : Pracownik.wszyscyPracownicy) {
//            System.out.println(pr.dzial);
//        }
//        for (Dzial dz : Dzial.listaDzialow) {
//            System.out.println(dz.nazwa);
//        }

    }

//    private void updatePracownikInstance(String id, int rowIndex, DefaultTableModel model) {
//        // Retrieve the Pracownik object using the unique identifier (ID)
//
//
//        if (pracownik != null) {
//            // Update the pracownik object with new values from the table
//            pracownik.setImie((String) model.getValueAt(rowIndex, 1));
//            pracownik.setNazwisko((String) model.getValueAt(rowIndex, 2));
//            pracownik.setDOB((String) model.getValueAt(rowIndex, 3));
//            pracownik.setDzial((String) model.getValueAt(rowIndex, 4));
//        }
//    }
//
//    private void updateBrygadzistaInstance(String id, int rowIndex, DefaultTableModel model) {
//        // Retrieve the Brygadzista object using the unique identifier (ID)
//        Brygadzista brygadzista = Brygadzista.getById(id);
//
//        if (brygadzista != null) {
//            // Update the brygadzista object with new values from the table
//            brygadzista.setImie((String) model.getValueAt(rowIndex, 1));
//            brygadzista.setNazwisko((String) model.getValueAt(rowIndex, 2));
//            brygadzista.setDOB((String) model.getValueAt(rowIndex, 3));
//            brygadzista.setLogin((String) model.getValueAt(rowIndex, 4));
//            brygadzista.setHaslo((String) model.getValueAt(rowIndex, 5));
//            brygadzista.setInicial((String) model.getValueAt(rowIndex, 6));
//            brygadzista.setListaBrygad((String) model.getValueAt(rowIndex, 7));
//            brygadzista.setZlecenia((String) model.getValueAt(rowIndex, 8));
//        }
//    }

    private void updateBrygadaInstance(String id, int rowIndex, DefaultTableModel model) {
        // Retrieve the Brygada object using the unique identifier (ID)
        int iden = Integer.parseInt(id);

        Brygada brygada = Brygada.listaBrygad.get(iden);



        if (brygada != null) {
            // Update the brygada object with new values from the table
            brygada.nazwa = (String) model.getValueAt(rowIndex, 2);
//            brygada.getpracownicyBrygady() =
        }
    }

    private void updateZlecenieInstance(String id, int rowIndex, DefaultTableModel model) {
        // Retrieve the Zlecenie object using the unique identifier (ID)
        int iden = Integer.parseInt(id);

        Zlecenie zlecenie = Zlecenie.getZlecenie(iden);

        if (zlecenie != null) {
            // Update the zlecenie object with new values from the table
            //Planowane, Nieplanowane
            switch ((String)model.getValueAt(rowIndex, 2)) {
                case "Planowane":
                    zlecenie.rodzajZlecenia = Zlecenie.RodzajZlecenia.Planowane;
                    break;
                case "Nieplanowane":
                    zlecenie.rodzajZlecenia = Zlecenie.RodzajZlecenia.Nieplanowane;
            }

            for (Brygada bryg : Brygada.listaBrygad.values()) {
                if (((String)model.getValueAt(rowIndex, 3)).equals(bryg.nazwa)) {
                    zlecenie.brygada = bryg;
                    break;
                }
            }
            zlecenie.dataUtworzenia = ((LocalDateTime) model.getValueAt(rowIndex, 5));
            zlecenie.dataRozpoczecia = ((LocalDateTime) model.getValueAt(rowIndex, 6));
            zlecenie.dataZakonczenia = ((LocalDateTime) model.getValueAt(rowIndex, 7));
        }
    }

    private void updatePracaInstance(String id, int rowIndex, DefaultTableModel model) {
        // Retrieve the Praca object using the unique identifier (ID)
        Praca praca = Praca.getPraca(Integer.parseInt(id));

        if (praca != null) {
            // Update the praca object with new values from the table

            //  public enum RodzajPracy {
            //        Ogolna, Montaz, Demontaz, Wymiana
            //    }
            switch ((String) model.getValueAt(rowIndex, 2)) {
                case "Ogolna":
                    praca.rodzajPracy = Praca.RodzajPracy.Ogolna;
                    break;
                case "Montaz":
                    praca.rodzajPracy = Praca.RodzajPracy.Montaz;
                    break;
                case "Demontaz":
                    praca.rodzajPracy = Praca.RodzajPracy.Demontaz;
                    break;
                case "Wymiana":
                    praca.rodzajPracy = Praca.RodzajPracy.Wymiana;
                    break;
            }

            praca.czasPracy = (int)model.getValueAt(rowIndex, 3);
            praca.opis = ((String) model.getValueAt(rowIndex, 4));
            praca.zrealizowane = ((boolean) model.getValueAt(rowIndex, 5));
        }
    }
}
