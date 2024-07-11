import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

public class KalendarzykPanel extends JDialog {
    private JSpinner dzienSpin;
    private JSpinner miesiacSpin;
    private JSpinner rokSpin;
    private JButton submitButton;
    private JButton cancelButton;
    private LocalDate selectedDate;

    public KalendarzykPanel(Frame owner) {
        super(owner, true);
        setTitle("Select Date");
        setSize(300, 200);
        setLocationRelativeTo(owner);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Day:"));
        dzienSpin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        panel.add(dzienSpin);

        panel.add(new JLabel("Month:"));
        miesiacSpin = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        panel.add(miesiacSpin);

        panel.add(new JLabel("Year:"));
        rokSpin = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panel.add(rokSpin);

        submitButton = new JButton("OK");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = (int) dzienSpin.getValue();
                int month = (int) miesiacSpin.getValue();
                int year = (int) rokSpin.getValue();
                try {
                    selectedDate = LocalDate.of(year, month, day);
                } catch (DateTimeException s) {
                    System.out.println("Wybierz właściwą date!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                dispose();
            }
        });
        panel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate = null;
                dispose();
            }
        });
        panel.add(cancelButton);

        add(panel);
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}