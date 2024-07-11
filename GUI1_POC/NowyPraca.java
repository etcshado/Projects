import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyPraca extends JFrame{
    private JTextField rodzajpracyf;
    private JTextField czaspracyf;
    private JTextField opisf;
    private JButton submitButton;

    public NowyPraca() {
        setTitle("Brygadzista");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Rodzaj pracy: <Ogolna, Montaz, Demontaz, Wymiana>"));
        rodzajpracyf = new JTextField();
        panel.add(rodzajpracyf);

        panel.add(new JLabel("Czas pracy"));
        czaspracyf = new JTextField();
        panel.add(czaspracyf);

        panel.add(new JLabel("Opis"));
        opisf = new JTextField();
        panel.add(opisf);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPraca();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        panel.add(submitButton);

        add(panel);
    }

    private void createPraca() {
        //RodzajPracy rodzajPracy, int czasPracy, String opis

        //Ogolna, Montaz, Demontaz, Wymiana

        String rodzajP = rodzajpracyf.getText();
        int czasPracy = Integer.parseInt(czaspracyf.getText());
        String opis = opisf.getText();
        Praca.RodzajPracy rodzajPracy = null;

        switch (rodzajP) {
            case "Ogolna":
                rodzajPracy = Praca.RodzajPracy.Ogolna;
                break;
            case "Montaz":
                rodzajPracy = Praca.RodzajPracy.Montaz;
                break;
            case "Demontaz":
                rodzajPracy = Praca.RodzajPracy.Demontaz;
                break;
            case "Wymiana":
                rodzajPracy = Praca.RodzajPracy.Wymiana;
                break;
        }
        if (rodzajPracy != null) {
            Praca praca = new Praca(rodzajPracy, czasPracy, opis);
            System.out.println("Pomyslnie utworzono prace.");
        } else {
            System.out.println("Rodzaj pracy musi być: Ogolna, Montaz, Demonatz albo Wymiana!");
        }

    }

}
