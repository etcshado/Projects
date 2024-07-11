import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // wazne jesli zaimplementowal bym tryb z botem
    public static String playersPick = "";
    public static String choosenMode = "";

    // wazne dla logiki sprawdzania kto wybiera, ktore plansze są odblokowane
    tictactoe currTic;
    private boolean planszaWybrana = false; // do sterowania klawiaturą numeryczną


    public ArrayList<tictactoe> listaGierek = new ArrayList<>();
    public static int counter = 1;

    // komponenty
    myFrame parentFrame;
    JPanel wynikiGry;
    JPanel planszaPanel;
    DefaultTableModel model;
    GamePanel(myFrame parentFrame) {
        //default settings
        this.parentFrame = parentFrame;
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setSize(parentFrame.getSize());

        // dodawanie planszy i wynikow
        planszaPanel = setupPlansza();
        wynikiGry = setupWyniki();
        this.add(planszaPanel, BorderLayout.CENTER);
        this.add(wynikiGry, BorderLayout.EAST);

        setupKlawiatura();
    }

    // ustawianie planszy do gry w utt (9 tictactoe)
    public JPanel setupPlansza() {
        JPanel planszaPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++ ) {
            tictactoe currtic = new tictactoe(this);
            planszaPanel.add(currtic);
            listaGierek.add(currtic);
        }
        return planszaPanel;
    }

    // ustawianie tablicy z wynikami i przebiegiem gier.
    public JPanel setupWyniki() {
        String[] kolumny = {"L.p.","Gracz","Glowna Plansza","Miejsce Na Konkretnej Planszy"};
        model = new DefaultTableModel(kolumny, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        JTable tabelaWynikow = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelaWynikow);
        JPanel wynikiGry = new JPanel(new BorderLayout());
        wynikiGry.add(scrollPane, BorderLayout.CENTER);
        wynikiGry.setPreferredSize(new Dimension(300, this.getHeight()));

        return wynikiGry;
    }

    public boolean checkWinner() {

        for (int i = 0; i < 3; i++) {
            if (!listaGierek.get(i).whoWon.isEmpty() && listaGierek.get(i).whoWon.equals(listaGierek.get(i+3).whoWon) && listaGierek.get(i).whoWon.equals(listaGierek.get(i+6).whoWon)) { // kolumny
                JOptionPane.showMessageDialog(null, "Wygrał: " + listaGierek.get(i).whoWon);
                parentFrame.endingPanel.addLabel(listaGierek.get(i).whoWon);
                parentFrame.showEnd();
                return true;
            }
            if (!listaGierek.get(i*3).whoWon.isEmpty() && listaGierek.get(i*3).whoWon.equals(listaGierek.get(i*3+1).whoWon) && listaGierek.get(i*3).whoWon.equals(listaGierek.get(i*3+2).whoWon)) { //wiersze
                JOptionPane.showMessageDialog(null, "Wygrał: " + listaGierek.get(i*3).whoWon);
                parentFrame.endingPanel.addLabel(listaGierek.get(i*3).whoWon);
                parentFrame.showEnd();
                return true;
            }
        }

        //diagonalne
        if (!listaGierek.get(0).whoWon.isEmpty() && listaGierek.get(0).whoWon.equals(listaGierek.get(4).whoWon) && listaGierek.get(0).whoWon.equals(listaGierek.get(8).whoWon)) {
            JOptionPane.showMessageDialog(null, "Wygrał: " + listaGierek.get(0).whoWon);
            parentFrame.endingPanel.addLabel(listaGierek.get(0).whoWon);
            parentFrame.showEnd();
            return true;
        }
        if (!listaGierek.get(6).whoWon.isEmpty() && listaGierek.get(6).whoWon.equals(listaGierek.get(4).whoWon) && listaGierek.get(6).whoWon.equals(listaGierek.get(2).whoWon)) {
            JOptionPane.showMessageDialog(null, "Wygrał: " + listaGierek.get(6).whoWon);
            parentFrame.endingPanel.addLabel(listaGierek.get(6).whoWon);
            parentFrame.showEnd();
            return true;
        }
        return false;
    }

    public void updateWyniki(Object[] data) {
        if (data.length == 4) {
            model.addRow(new Object[]{data[0], data[1], data[2], data[3]});
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean checkRemis() {
        for (tictactoe t : listaGierek) {
            if (t.getComponents().length > 2) {
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "REMIS");
        parentFrame.endingPanel.addLabel("None");
        parentFrame.showEnd();
        return true;
    }

    private void setupKlawiatura() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();


        for (int i = 1; i <= 9; i++) {
            inputMap.put(KeyStroke.getKeyStroke("pressed NUMPAD" + i), "numpad" + i);
            int finalI = i;
            actionMap.put("numpad" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int indexGierki = MapaMiejsc.NumericToIndex.get(finalI);

                    System.out.println("key pressed; " + finalI);
                    System.out.println("index: " + indexGierki);

                    if (planszaWybrana && currTic.getComponents().length > 2) { // very very very explosive line of code
                        currTic.buttonyPlansza.get(indexGierki).doClick();
                        planszaWybrana = false;
                    } else {
                        currTic = listaGierek.get(indexGierki);
                        planszaWybrana = true;
                    }
                }
            });
        }
    }
}
