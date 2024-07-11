import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage extends JPanel implements ActionListener {

    JTextField textField;
    JPasswordField passwordField;
    JButton loginButton;
    JLabel label;
    JLabel loginLabel;
    JLabel passwordLabel;
    boolean showInvalid = false;
    public static boolean authenticated = false;

    myFrame parentFrame;

    String WEB_COLOR = "#2E3047";
    String TEXT_COLOR = "#707793";
    String BACKGROUND_COLOR = "#2E3047";
    final int FONT_SIZE = 30;

    loginPage(myFrame parentFrame) {
        this.parentFrame = parentFrame;

        setPanelProperties();

        initializeComponents();

        addComponentsToPanel();

    }
    private void setPanelProperties() {

        this.setBounds(0, 0, myFrame.WIDTH, myFrame.HEIGHT);
        this.setBackground(Color.decode(WEB_COLOR));
        this.setLayout(null);

    }
    private void initializeComponents() {
        label = createLabel(400, 100, 500, 50);
        loginLabel = createloginLabel(400, 160, 100, 50);
        textField = createTextField(400, 200, 200, 50);
        passwordLabel = createpasswordLabel(400, 260, 100, 50);
        passwordField = createPasswordField(400, 300, 200, 50);
        loginButton = createButton("Log In", 400, 400, 150, 50);
    }

    private JLabel createLabel(int x, int y, int width, int height) {
        JLabel label = new JLabel("Polaris corp");

        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, FONT_SIZE));
        label.setForeground(Color.white);

        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();

        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Helvetica", Font.PLAIN, FONT_SIZE));


        return textField;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordLabel = new JPasswordField();

        passwordLabel.setBounds(x, y, width, height);
        passwordLabel.setFont(new Font("Helvetica", Font.PLAIN, FONT_SIZE));


        return passwordLabel;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        button.setFocusable(false);
        button.setBackground(Color.decode(BACKGROUND_COLOR));
        button.setForeground(Color.decode(TEXT_COLOR));
        button.addActionListener(this);

        return button;
    }

    private JLabel createloginLabel(int x, int y, int width, int height) {
        JLabel loginLabel = new JLabel();
        loginLabel.setBounds(x, y, 100, 50);
        loginLabel.setText("username");
        loginLabel.setBackground(Color.decode(WEB_COLOR));
        loginLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, FONT_SIZE-10));
        loginLabel.setForeground(Color.white);

        return loginLabel;
    }
    private JLabel createpasswordLabel(int x, int y, int width, int height) {
        JLabel passwordLabel = new JLabel();
        passwordLabel.setBounds(x, y, 100, 50);
        passwordLabel.setText("password");
        passwordLabel.setBackground(Color.decode(WEB_COLOR));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE-10));
        passwordLabel.setForeground(Color.white);

        return passwordLabel;
    }

    private void addComponentsToPanel() {
        this.add(label);
        this.add(loginLabel);
        this.add(textField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String login = textField.getText();
        String pass = new String(passwordField.getPassword());

        for (Pracownik pr : Pracownik.mapaWszystkichPracownikow.values()) {
            if (pr instanceof Uzytkownik) {
                Uzytkownik uzytkownik = (Uzytkownik) pr;
                if (uzytkownik.login.equals(login) && uzytkownik.haslo.equals(pass)) {
                    System.out.println("Success!");
                    authenticated = true;
                    parentFrame.showDashboard(uzytkownik);
                    textField.setText("");
                    passwordField.setText("");
                    return;
                } else {
                    invalidCredentials();
                }
            }
        }

        // temporary
//        if (login.equals("") && pass.equals("")) {
//            parentFrame.showDashboard();
//            return;
//        } else {
//            if (!showInvalid) {
//                invalidCredentials();
//            }
//        }

        textField.setText("");
        passwordField.setText("");

    }
    public void invalidCredentials() {
        JLabel invalidCreds = new JLabel("Invalid username or password.");
        invalidCreds.setBounds(350, 500, 300, 50);
        invalidCreds.setFont(new Font("Helvetica", Font.PLAIN, 20));
        invalidCreds.setForeground(Color.red);

        showInvalid = true;

        this.add(invalidCreds);
        this.revalidate();
        this.repaint();
    }

}
