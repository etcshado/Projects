import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyBrygadzista extends JFrame{

    private JTextField imief;
    private JTextField naziwskof;
    private JButton dobButton;
    private LocalDate selectedDob;
    private JTextField loginf;
    private JTextField haslof;
    private JButton submitButton;
    private int selectedDzialId = -1;
    public NowyBrygadzista(JTable dzialTable) {
        setTitle("Brygadzista");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

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
                KalendarzykPanel kalendarz = new KalendarzykPanel(NowyBrygadzista.this);
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
                createBrygadzista();
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

    private void createBrygadzista() {
        String imie = imief.getText();
        String nazwisko = naziwskof.getText();
        LocalDate dataUrodzenia = selectedDob;
        String login = loginf.getText();
        String haslo = haslof.getText();

        if (selectedDzialId != -1) {
            DzialPracownikow dzial = DzialPracownikow.listaWszystkichDzialow.get(selectedDzialId);

            Brygadzista brygadzista = new Brygadzista(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
            System.out.println("Created Brygadzista: " + brygadzista);
        }
//        if (selectedDzial != null) {
//            Brygadzista brygadzista = new Brygadzista(imie, nazwisko, dataUrodzenia, selectedDzial, login, haslo);
//            System.out.println("Created Brygadzista: " + brygadzista);
//        } else {
//            System.out.println("error");
//        }


//        if (dzial != null) {
//            Brygadzista brygadzista = new Brygadzista(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
//            System.out.println("Created Brygadzista: " + brygadzista);
//        } else {
//            System.out.println("error");
//        }
    }

}
