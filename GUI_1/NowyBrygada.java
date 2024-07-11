import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyBrygada extends JFrame{

    private JButton submitButton;

    private JTextField nazwaf;
    private int selectedBrygadzistaId = -1;

    public NowyBrygada(JTable brygadzistaTable) {
        setTitle("Brygada");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Nazwa brygady"));
        nazwaf = new JTextField();
        panel.add(nazwaf);


        panel.add(new JLabel("Brygadzista:"));
        JButton brygadzistaButton = new JButton("Select Brygadzista");
        brygadzistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowObject object = new ShowObject(brygadzistaTable);
                object.setVisible(true);
                selectedBrygadzistaId = object.getIdOfObject();
            }
        });
        panel.add(brygadzistaButton);

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

        if (selectedBrygadzistaId != -1) {
            Brygadzista brygadzista = Brygadzista.mapaBrygadzistow.get(selectedBrygadzistaId);

            if (nazwa != null && brygadzista != null) {
                Brygada brygada = new Brygada(nazwa, brygadzista);
                System.out.println("Created Brygada: " + brygada);
            }
        }

    }

}
