import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class tictactoe extends JPanel implements ActionListener {

    // logika gry
    private ArrayList<JButton> notUsedButtons = new ArrayList<>();

    public ArrayList<JButton> buttonyPlansza = new ArrayList<>();

    private String[][] plansza = new String[3][3];

    public boolean locked;
    public String whoWon = "";

    // ikony
    private Icon iconX = new ImageIcon("ticX.png");
    private Icon iconO = new ImageIcon("ticO.png");

    // komponenty
    DefaultTableModel model;
    JTable miniWyniki;
    GamePanel gamePanel;
    JPanel wynikiminiGry;

    public tictactoe(GamePanel gamePanel) {
        //default settings
        this.gamePanel = gamePanel;
        this.locked = false;
        this.setVisible(true);
        this.setLayout(new GridLayout(3, 3));


        // dodawanie buttonow jako plansza
        for (int i = 0; i < 9; i++) {
            this.add(createButton());
        }

        // tworzenie swojej tablicy
        setupMiniWyniki();
    }

    public JButton createButton() {
        JButton button = new JButton();
        button.addActionListener(this);
        button.setVisible(true);
        button.setFocusable(false);
        buttonyPlansza.add(button);
        notUsedButtons.add(button);
        return button;
    }


    public boolean checkPlansza() {
        // sprawdzanie wierszy i kolumn
        for (int i = 0; i < 3; i++) {
            if (plansza[i][0] != null && plansza[i][0].equals(plansza[i][1]) && plansza[i][1].equals(plansza[i][2])) {
                return true;
            }
            if (plansza[0][i] != null && plansza[0][i].equals(plansza[1][i]) && plansza[1][i].equals(plansza[2][i])) {
                return true;
            }
        }
        // sprawdzanie przekątnych
        if (plansza[0][0] != null && plansza[0][0].equals(plansza[1][1]) && plansza[1][1].equals(plansza[2][2])) {
            return true;
        }
        if (plansza[0][2] != null && plansza[0][2].equals(plansza[1][1]) && plansza[1][1].equals(plansza[2][0])) {
            return true;
        }

        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (notUsedButtons.contains((JButton)e.getSource()) && !locked ) {

            // ustawianie indeksu, kolumny i wiersza miejsca gdzie postawiono znak
            JButton currButton = (JButton) e.getSource();
            int index = buttonyPlansza.indexOf(currButton);
            System.out.println(index);
            int row = index/3;
            int col = index%3;

            // nadpisywanie listy plansza zeby potem sprawdzic czy ktos wygral
            if (GamePanel.counter % 2 == 0) {


                //resizing the icon
                Image img = ((ImageIcon) iconO).getImage();
                Dimension size = currButton.getSize();
                Image newimg = img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                ImageIcon currIcon = new ImageIcon(newimg);

                //setting the resized icon as the button's icon
                currButton.setIcon(currIcon);

                plansza[row][col] = "O";
                Object[] data = {CounterRuchow.counterRuchow, "O", MapaMiejsc.mapaMiejsc.get(gamePanel.listaGierek.indexOf(this)), MapaMiejsc.mapaMiejsc.get(index)};
                Object[] minidata = {CounterRuchow.counterRuchow, "O", MapaMiejsc.mapaMiejsc.get(index)};
                CounterRuchow.counterRuchow++;
                gamePanel.updateWyniki(data);
                model.addRow(minidata);
            } else {

                //resizing the icon
                Image img = ((ImageIcon) iconX).getImage();
                Dimension size = currButton.getSize();
                Image newimg = img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                ImageIcon currIcon = new ImageIcon(newimg);

                //setting the resized icon as the button's icon
                currButton.setIcon(currIcon);

                plansza[row][col] = "X";
                Object[] data = {CounterRuchow.counterRuchow, "X", MapaMiejsc.mapaMiejsc.get(gamePanel.listaGierek.indexOf(this)), MapaMiejsc.mapaMiejsc.get(index)};
                Object[] minidata = {CounterRuchow.counterRuchow, "X", MapaMiejsc.mapaMiejsc.get(index)};
                CounterRuchow.counterRuchow++;
                gamePanel.updateWyniki(data);
                model.addRow(minidata);
            }

            // "usuwanie" przycisku z planszy aby nie można było go nadpisać
            notUsedButtons.remove(currButton);

            // ustawianie całej planszy jako jeden JButton jesli ktos wygrał
            if (checkPlansza()) {
                String winner = (GamePanel.counter % 2 != 0) ? "X" : "O";
                notUsedButtons.clear();

                JButton mb = setFinalButton();
                mb.setText(""); // remove pozniej

                if (winner.equals("X")) {

                    //resizing the icon
                    Image img = ((ImageIcon) iconX).getImage();
                    Dimension size = mb.getSize();
                    Image newimg = img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                    ImageIcon currIcon = new ImageIcon(newimg);

                    //setting the resized icon as the button's icon
                    mb.setIcon(currIcon);

                    whoWon = "X";
                } else {
                    //resizing the icon
                    Image img = ((ImageIcon) iconO).getImage();
                    Dimension size = mb.getSize();
                    Image newimg = img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                    ImageIcon currIcon = new ImageIcon(newimg);

                    //setting the resized icon as the button's icon
                    mb.setIcon(currIcon);

                    whoWon = "O";
                }
                this.setLayout(null);
                this.add(mb);

            }

            // uniemożliwia wybór pola który jest niezgodyn z logiką utt
            for (int i = 0; i < 9; i ++) {
                tictactoe currtic = gamePanel.listaGierek.get(i);
                if (i == index) {
                    // odblokowanie planszy, ktora jest indexem zgodna do wybranego punktu w mini planszy

                    if (currtic.getComponents().length <= 2) { // sprawdzanie czy odblokowana plansza jest juz rozwiązana, jesli tak to odblokowuje wszystkie mini plansze
                        System.out.println("cosss");
                        for (tictactoe t : gamePanel.listaGierek) {
                            t.locked = false;
                            for (JButton but : t.buttonyPlansza) {
                                but.setBackground(Color.green); // zamiana pol mozliwych do wybrania na odpowiedni kolor
                            }
                        }
                        break;
                    } else {
                        currtic.locked = false;
                        for (JButton but : currtic.buttonyPlansza) {
                            but.setBackground(Color.green);
                        }
                    }

                } else {
                    currtic.locked = true; // w przeciwnym wypadku całą reszte blokuje
                    for (JButton but : currtic.buttonyPlansza) { // zamiana pól niemozliwych do wybranai na odpowiedni kolor
                        but.setBackground(Color.red);
                    }
                }
            }
            GamePanel.counter++;
        }

        if (notUsedButtons.isEmpty()) { // jesli jest remis i nikt nie wygral na mini planszy
            JButton mb = setFinalButton();
            this.setLayout(null);
            this.add(mb);
        }

        // uniemozliwienie zdolnosci dalszej gry jesli ktos wygra UTTT
        if (gamePanel.checkWinner() || gamePanel.checkRemis()) {
            for (tictactoe lp : gamePanel.listaGierek) {
                for (JButton buttony : lp.buttonyPlansza) {
                    buttony.setBackground(Color.white);
                    lp.remove(buttony);
                }
            }
        }


        this.repaint();
        this.revalidate();
    }

    public void setupMiniWyniki() {
        String[] kolumny = {"L.p.","Gracz","Miejsce Na Konkretnej Planszy"};
        model = new DefaultTableModel(kolumny, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        miniWyniki = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(miniWyniki);
        wynikiminiGry = new JPanel(new BorderLayout());
        wynikiminiGry.add(scrollPane, BorderLayout.CENTER);
        wynikiminiGry.setSize(400, 800); // mozliwe ze do zmiany
    }

    public void showminiWyniki() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this));
        dialog.setContentPane(wynikiminiGry);
        dialog.pack();
        dialog.setTitle("Wyniki Mini Gry");
        dialog.setVisible(true);
    }

    public JButton setFinalButton() {
        for (JButton b : buttonyPlansza) {
            this.remove(b);
        }

        JButton mb = new JButton();
        mb.setVisible(true);
        mb.setSize(this.getSize());
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showminiWyniki();
            }
        });
        mb.setText("REMIS");

        return mb;
    }

}
