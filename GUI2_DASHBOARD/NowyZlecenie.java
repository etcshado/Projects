import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyZlecenie extends JFrame{

    private int selectedBrygadaId = -1;
    private JTextField planowanef;
    private JButton submitButton;

    public NowyZlecenie(JTable brygadaTable) {
        setTitle("Zlecenie");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Planowane? (true/false)"));
        planowanef = new JTextField();
        panel.add(planowanef);

        panel.add(new JLabel("Brygada:"));
        JButton dzialButton = new JButton("Select Brygada");
        dzialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowObject object = new ShowObject(brygadaTable);
                object.setVisible(true);
                selectedBrygadaId = object.getIdOfObject();
            }
        });
        panel.add(dzialButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createZlecenie();
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

    private void createZlecenie() {
        String planowane = planowanef.getText().toUpperCase();

        if (planowane.equals("FALSE") || planowane.equals("TRUE")) {
            boolean plan = false;
            switch (planowane) {
                case "TRUE":
                    plan = true;
                    break;
                case "FALSE":
                    plan = false;
                    break;
            }
            Brygada brygada = Brygada.mapaBrygad.get(selectedBrygadaId);
            Zlecenie zlec = new Zlecenie(brygada, plan);
            System.out.println("Created Zlecenie: " + zlec);
        } else {
            System.out.println("erorr");
        }

    }
}
