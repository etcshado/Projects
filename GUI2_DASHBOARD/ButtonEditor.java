import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    String whichClass;

    public ButtonEditor(JCheckBox checkBox, String whichClass) {
        super(checkBox);
        this.whichClass = whichClass;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        if (whichClass.equals("DzialPracownikow")) {
            int idDzialu = (int) table.getValueAt(row, 0);
            JTable dzialTable = new JTable();
            dzialTable.setDefaultEditor(Object.class, null);
            DefaultTableModel model = (DefaultTableModel) dzialTable.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new String[]{"ID", "Imie", "Nazwisko", "DOB"});
            DzialPracownikow currDzial = DzialPracownikow.listaWszystkichDzialow.get(idDzialu);
            for (Pracownik pr : currDzial.listaPracownikowDzialu()) {
                model.addRow(new Object[]{pr.id, pr.imie, pr.nazwisko, pr.dataUrodzenia});
            }
            dzialTable.setModel(model);

            JDialog dialog = new JDialog((Frame) null);
            dialog.setSize(600, 400);
            dialog.setLocationRelativeTo(null);

            JScrollPane scrollPane = new JScrollPane(dzialTable);
            dialog.add(scrollPane);
            dialog.setTitle("Lista pracownikow dzialu o id: " + idDzialu);
            dialog.setVisible(true);
        } else if (whichClass.equals("Zlecenie")) {
            int idZlecenia = (int) table.getValueAt(row, 0);
            JTable zlecenieTable = new JTable();
            zlecenieTable.setDefaultEditor(Object.class, null);
            DefaultTableModel model = (DefaultTableModel) zlecenieTable.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new String[]{"ID", "rodzajPracy", "czasPracy", "opis", "zrealizowane?"});
            Zlecenie currZlecenie = Zlecenie.mapaWszystkichZlecen.get(idZlecenia);

            for (Praca praca : currZlecenie.prace) {
                model.addRow(new Object[]{praca.id, praca.rodzajPracy, praca.czasPracy, praca.opis, praca.czyZrealizowane});
            }

            zlecenieTable.setModel(model);

            JDialog dialog = new JDialog((Frame) null);
            dialog.setSize(600, 400);
            dialog.setLocationRelativeTo(null);

            JScrollPane scrollPane = new JScrollPane(zlecenieTable);
            dialog.add(scrollPane);
            dialog.setTitle("Lista prac zlecenia o id: " + idZlecenia);
            dialog.setVisible(true);
        }



        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {

        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

