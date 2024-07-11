import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameDifficulty extends JPanel implements ActionListener {

    // tryby (wazne do gamePanelu)
    String choosenMode = ""; // multiplayer, albo easy,medium,hard (robot)
    ArrayList<String> possibleRobots = new ArrayList<>();

    // kolorki
    String buttonColor = "#3c3c3c";
    String backgroundColor = "#444444";
    String textColor = "#bebebe";

    // komponenty
    JLabel mainLabel;
    JButton modeSelectorS;
    JButton modeSelectorM;
    myFrame parentFrame;
    JRadioButton rX;
    JRadioButton rO;
    public GameDifficulty(myFrame parentFrame) {
        // mozliwe tryby grania z komputerem
        possibleRobots.add("Easy");
        possibleRobots.add("Medium");
        possibleRobots.add("Hard");

        // defaultowe settingsy
        this.parentFrame = parentFrame;
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.decode(backgroundColor));

        // ustawianie buttonow i Labela
        modeSelectorS = createButton("Singleplayer", 150, 300, 250, 80);
        modeSelectorM = createButton("Multiplayer", 400, 300, 250, 80);
        mainLabel = createLabel("Choose mode", 150, 200);

        // dodawanie
        this.add(mainLabel);
        this.add(modeSelectorS);
        this.add(modeSelectorM);

    }
    public JLabel createLabel(String name, int x, int y) {
        JLabel label = new JLabel();
        label.setText(name);
        label.setFont(new Font("Helvetica", Font.ITALIC, 50));
        label.setForeground(Color.decode("#EBD480"));
        label.setVisible(true);
        label.setSize(500, 100);
        label.setLocation(x, y);

        return label;
    }

    public JButton createButton(String name, int x, int y, int sizeW, int sizeH) {
        JButton button = new JButton();
        button.addActionListener(this);
        button.setVisible(true);
        button.setSize(sizeW, sizeH);
        button.setLocation(x, y);
        button.setFocusable(false);
        button.setText(name);
        button.setFont(new Font("Helvetica", Font.BOLD, 30));
        button.setForeground(Color.decode(textColor));
        button.setBackground(Color.decode(buttonColor));

        return button;
    }

    public JRadioButton createPick(String text, int x, int y, int width, int height) {

        JRadioButton rb = new JRadioButton(text);
        rb.setSize(width, height);
        rb.setLocation(x, y);
        rb.setBackground(Color.decode(backgroundColor));
        rb.setForeground(Color.white);

        return rb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modeSelectorS) {

            // usuwanie starych buttonow
            mainLabel.setText("Choose difficulty");
            this.remove(modeSelectorS);
            this.remove(modeSelectorM);

            // dodawanie nowe
            JButton difficulty1 = createButton("Easy", 100, 300, 200, 80);
            JButton difficulty2 = createButton("Medium", 300, 300, 200, 80);
            JButton difficulty3 = createButton("Hard", 500, 300, 200, 80);
            this.add(difficulty1);
            this.add(difficulty2);
            this.add(difficulty3);

            // label dla radio buttonow
            JLabel rlabel = createLabel("Choose who do you want to play as.", 225, 375);
            rlabel.setFont(new Font("Helvetica", Font.ITALIC, 20));
            this.add(rlabel);

            // radio buttony kim ma grac gracz
            ButtonGroup group = new ButtonGroup(); // stowrzone aby wybor byl tylko jeden (x alb o)
            rX = createPick("X", 350, 450, 50, 50);
            rO = createPick("O",  400, 450, 50, 50);
            group.add(rX);
            group.add(rO);
            this.add(rX);
            this.add(rO);


            // updatowanie
            this.revalidate();
            this.repaint();

        } else if (e.getSource() == modeSelectorM) {
            choosenMode = "Multiplayer";

            // switching to gamePanel

            parentFrame.showGame();

        } else {
            JButton buttonPressed = (JButton) e.getSource();
            if (possibleRobots.contains(buttonPressed.getText())) {
                choosenMode = buttonPressed.getText();
            }
            GamePanel.choosenMode = choosenMode;
            GamePanel.playersPick = rX.isSelected() ? "X" : "O";

            // switching to gamePanel

            parentFrame.showGame();
        }
    }
}
