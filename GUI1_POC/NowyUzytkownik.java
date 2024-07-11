import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyUzytkownik extends JFrame{

    //String imie, String nazwisko, LocalDate DOB, Dzial dzial, String login, String haslo
    private JTextField imief;
    private JTextField naziwskof;
    private JTextField dobf;
    private JTextField dzialf;
    private JTextField loginf;
    private JTextField haslof;
    private JButton submitButton;

    public NowyUzytkownik() {
        setTitle("Uzytkownik");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Imie:"));
        imief = new JTextField();
        panel.add(imief);

        panel.add(new JLabel("Nazwisko:"));
        naziwskof = new JTextField();
        panel.add(naziwskof);

        panel.add(new JLabel("DOB (yyyy-MM-dd):"));
        dobf = new JTextField();
        panel.add(dobf);

        panel.add(new JLabel("Dzial:"));
        dzialf = new JTextField();
        panel.add(dzialf);

        panel.add(new JLabel("Login:"));
        loginf = new JTextField();
        panel.add(loginf);

        panel.add(new JLabel("Haslo:"));
        haslof = new JPasswordField();
        panel.add(haslof);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUzytkownik();
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

    private void createUzytkownik() {
        String imie = imief.getText();
        String nazwisko = naziwskof.getText();
        LocalDate DOB = LocalDate.parse(dobf.getText());
        String login = loginf.getText();
        String haslo = haslof.getText();

        Dzial dzial = null;

        for (Dzial d : Dzial.listaDzialow) {
            if (d.nazwa.equals(dzialf.getText())) {
                dzial = d;
                break;
            }
        }

        if (dzial != null) {
            Uzytkownik uzytkownik = new Uzytkownik(imie, nazwisko, DOB, dzial, login, haslo);
            System.out.println("Created Uzytkownik: " + uzytkownik);
        } else {
            System.out.println("error");
        }
    }

}
