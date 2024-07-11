import javax.swing.*;

public class NowyDzial {

    public static void newDzial() {

        String nazwa = JOptionPane.showInputDialog("Please enter a name of dzial you want to create.");
        try {
            if (nazwa != null) {
                if (!nazwa.isEmpty() || !nazwa.isBlank()) {
                    Dzial.createDzial(nazwa);
                    JOptionPane.showMessageDialog(null, "Dzial: " + nazwa + " has been created succesfully!");
                }
            }
        } catch (NotUniqueNameException e){
            JOptionPane.showMessageDialog(null, "Name is not Unique!");
        }
    }
}
