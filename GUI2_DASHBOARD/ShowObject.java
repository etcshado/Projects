import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowObject extends JDialog {

    private int idOfObject = -1;

    public ShowObject(JTable objectTable) {
        super((Frame) null, true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        DefaultTableModel model = (DefaultTableModel) objectTable.getModel();
        JTable thisTable = new JTable(model);
        thisTable.setDefaultEditor(Object.class, null);
        thisTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(thisTable), BorderLayout.CENTER);

        JButton submitObject = new JButton("Submit");
        submitObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = thisTable.getSelectedRow();
                if (row != -1) {
                    idOfObject = (int) model.getValueAt(row, 0);
                }
                dispose();
            }
        });

        this.add(submitObject, BorderLayout.SOUTH);
    }

    public int getIdOfObject() {
        return idOfObject;
    }
}