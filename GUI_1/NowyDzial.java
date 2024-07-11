import javax.swing.*;

public class NowyDzial {

    public static void newDzial() {

        String nazwa = JOptionPane.showInputDialog("Please enter a name of dzial you want to create.");
        if (nazwa != null) {
            if (!nazwa.isEmpty() && !nazwa.isBlank()) {
                DzialPracownikow d1 = DzialPracownikow.createDzial(nazwa);
                System.out.println("Created Dzial: " + d1);
            }
        } else {
            System.out.println("please enter something");
        }
    }
}
