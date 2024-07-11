import javax.swing.*;
import java.awt.*;

public class myFrame extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    CardLayout cardLayout;
    JPanel cardPanel;
    Uzytkownik loggedInUser;


    myFrame() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPage loginPage = new loginPage(this);
        cardPanel.add(loginPage, "loginPage");

        this.add(cardPanel);
        this.setResizable(false);
        this.setTitle("Polaris");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);


    }

    public void showDashboard(Uzytkownik user) {
        this.loggedInUser = user;
        TableManager t = new TableManager(user);
        Dashboard dashboard = new Dashboard(this, user);
        cardPanel.add(dashboard, "dashboard");
        cardLayout.show(cardPanel, "dashboard");
    }
    public void showLoginPage() {
        cardLayout.show(cardPanel, "loginPage");
    }
}
