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
    private JButton dobButton;

    private int selectedDzialId = -1;
    private LocalDate selectedDob;
    private JTextField loginf;
    private JTextField haslof;
    private JButton submitButton;

    public NowyUzytkownik(JTable dzialTable) {
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

        panel.add(new JLabel("DOB:"));
        dobButton = new JButton("Select Date");
        dobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KalendarzykPanel kalendarz = new KalendarzykPanel(NowyUzytkownik.this);
                kalendarz.setVisible(true);
                selectedDob = kalendarz.getSelectedDate();
            }
        });
        panel.add(dobButton);

        panel.add(new JLabel("Dzial:"));
        JButton dzialButton = new JButton("Select Dzial");
        dzialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowObject object = new ShowObject(dzialTable);
                object.setVisible(true);
                selectedDzialId = object.getIdOfObject();
            }
        });
        panel.add(dzialButton);

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
        LocalDate DOB = selectedDob;
        String login = loginf.getText();
        String haslo = haslof.getText();

        DzialPracownikow dzial = DzialPracownikow.listaWszystkichDzialow.get(selectedDzialId);

        if (dzial != null) {
            Uzytkownik uzytkownik = new Uzytkownik(imie, nazwisko, DOB, dzial, login, haslo);
            System.out.println("Created Uzytkownik: " + uzytkownik);
        } else {
            System.out.println("error");
        }
    }

}
