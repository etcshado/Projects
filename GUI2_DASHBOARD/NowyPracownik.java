import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyPracownik extends JFrame{
    private JTextField imief;
    private JTextField nazwiskof;

    private JButton dobButton;
    private LocalDate selectedDob;
    private int selectedDzialId = -1;
    private JButton submitButton;

    public NowyPracownik(JTable dzialTable) {
        setTitle("Pracownik");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Imie:"));
        imief = new JTextField();
        panel.add(imief);

        panel.add(new JLabel("Nazwisko:"));
        nazwiskof = new JTextField();
        panel.add(nazwiskof);

        panel.add(new JLabel("DOB:"));
        dobButton = new JButton("Select Date");
        dobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KalendarzykPanel kalendarz = new KalendarzykPanel(NowyPracownik.this);
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

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPracownik();
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

    private void createPracownik() {
        String imie = imief.getText();
        String nazwisko = nazwiskof.getText();
        LocalDate DOB = selectedDob;

        DzialPracownikow dzial = DzialPracownikow.listaWszystkichDzialow.get(selectedDzialId);

        Pracownik pracownik = new Pracownik(imie, nazwisko, DOB, dzial);
        System.out.println("Created Pracownik: " + pracownik);
    }

}
