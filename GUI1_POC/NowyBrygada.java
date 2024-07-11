import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyBrygada extends JFrame{

    private JButton submitButton;

    private JTextField nazwaf;
    private JTextField imief;
    private JTextField nazwiskof;

    public NowyBrygada() {
        setTitle("Brygada");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Nazwa brygady"));
        nazwaf = new JTextField();
        panel.add(nazwaf);

        panel.add(new JLabel("Imie pracownika:"));
        imief = new JTextField();
        panel.add(imief);

        panel.add(new JLabel("Nazwisko pracownika"));
        nazwiskof = new JTextField();
        panel.add(nazwiskof);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBrygada();
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

    private void createBrygada() {
        String nazwa = nazwaf.getText();
        String imie = imief.getText();
        String nazwisko = nazwiskof.getText();

        Pracownik availablePracownik = null;

        for (Pracownik pr : Pracownik.wszyscyPracownicy) {
            if (pr.imie.equals(imie) && pr.nazwisko.equals(nazwisko)) {
                availablePracownik = pr;
                break;
            } else {
//                System.out.println("---------------------");
//                System.out.println(pr.imie + " " + pr.nazwisko + " istniejace | moje " + imie + " " + nazwisko);
//                System.out.println(pr.imie.equals(imie) + " " +pr.nazwisko.equals(nazwisko));
//                System.out.println("---------------------");
//                System.out.println("error, no pracownik with the name: " + imie + " and surname: " +nazwisko);
            }
        }

        if (availablePracownik != null) {
            Brygada brygada = new Brygada(nazwa, availablePracownik);
        } else {
            System.out.println("error");
        }
    }

}
