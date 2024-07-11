import javax.swing.*;
import java.awt.*;

public class myFrame extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 650;
    JPanel mainPanel;

    GameDifficulty gameDifficulty;
    GamePanel gamePanel;
    EndingPanel endingPanel;

    public myFrame() {

        //defaultowe settingsy
        gameDifficulty = new GameDifficulty(this);
        gamePanel = new GamePanel(this);
        endingPanel = new EndingPanel(this);

        this.setTitle("Ultimate Tic-Tac-Toe by Danielson");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(WIDTH, HEIGHT);

        // mainPanel
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(gameDifficulty, "gameDifficulty");
        mainPanel.add(gamePanel, "gamePanel");
        mainPanel.add(endingPanel, "endingPanel");

        // dodawanie
        this.add(mainPanel);
        showDifficulty();
    }

    public void showDifficulty() {
        CardLayout currLayout = (CardLayout) mainPanel.getLayout();
        currLayout.show(mainPanel, "gameDifficulty");
    }
    public void showGame() {
        CardLayout currLayout = (CardLayout) mainPanel.getLayout();
        currLayout.show(mainPanel, "gamePanel");
    }

    public void showEnd() {
        CardLayout currLayout = (CardLayout) mainPanel.getLayout();
        currLayout.show(mainPanel, "endingPanel");
    }

}
