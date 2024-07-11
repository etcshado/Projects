import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NowyZlecenie extends JFrame{

    private JTextField planowanef;
    private JTextField nazwaf;
    private JButton submitButton;

    public NowyZlecenie() {
        setTitle("Zlecenie");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Planowane? (true/false)"));
        planowanef = new JTextField();
        panel.add(planowanef);

        panel.add(new JLabel("Nazwa brygady"));
        nazwaf = new JTextField();
        panel.add(nazwaf);

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
        String planowane = planowanef.getText();
        String nazwaBrygady = nazwaf.getText();

        if (planowane.equals("false") || planowane.equals("true")) {
            boolean plan = false;
            switch (planowane) {
                case "true":
                    plan = true;
                    break;
                case "false":
                    plan = false;
                    break;
            }

            for (Brygada b : Brygada.listaBrygad.values()) {
                if (b.nazwa.equals(nazwaBrygady)) {
                    Zlecenie zlecenie = new Zlecenie(plan, b);
                    System.out.println("Succesfully added Zlecenie");
                    break;
                }
            }
        }
    }
}
