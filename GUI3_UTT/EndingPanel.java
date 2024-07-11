import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndingPanel extends JPanel implements ActionListener {

    //kolorki
    String buttonColor = "#3c3c3c";
    String backgroundColor = "#444444";
    String textColor = "#bebebe";
    public myFrame parentFrame;


    public EndingPanel(myFrame parentFrame) {
        //default settings
        this.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        this.setVisible(true);
        this.setBackground(Color.decode(backgroundColor));
        this.setLayout(null);
        this.parentFrame = parentFrame;

        JButton exitButton = createButton();

        this.add(exitButton);
    }

    public JButton createButton() {
        JButton button = new JButton();

        button.setSize(150, 50);
        button.setLocation(325, 400);
        button.setText("Exit");
        button.setFont(new Font("Helvetica", Font.BOLD, 40));
        button.setForeground(Color.decode(textColor));
        button.setBackground(Color.decode(buttonColor));
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    public JLabel createLabel(String name) {
        JLabel label = new JLabel();

        if (name.equals("None")) {
            label.setText("Tie");
        } else {
            label.setText("The Winner is: " + name);
        }
        label.setFont(new Font("Helvetica", Font.BOLD, 50));
        label.setForeground(Color.decode("#EBD480"));
        label.setVisible(true);
        label.setSize(500, 200);
        label.setLocation(185, 150);

        return label;
    }

    public void addLabel(String whoWon) {
        this.add(createLabel(whoWon));

        revalidate();
        repaint();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
