package GUI.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculatorView extends JFrame {

    private JTextArea historyTextArea;
    private JButton computeButton;
    private JButton showHistoryButton;
    private JButton undoButton;
    private JButton clearButton;
    private JButton exitButton;

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Historial
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyTextArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(historyTextArea), BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        computeButton     = new JButton("Calculate");
        showHistoryButton = new JButton("Show history");
        undoButton        = new JButton("Undo");
        clearButton       = new JButton("Clear");
        exitButton        = new JButton("Exit");

        buttonsPanel.add(computeButton);
        buttonsPanel.add(showHistoryButton);
        buttonsPanel.add(undoButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Métodos para que el Controller registre listeners
    public void addComputeListener(ActionListener l)    { computeButton.addActionListener(l); }
    public void addShowHistoryListener(ActionListener l) { showHistoryButton.addActionListener(l); }
    public void addUndoListener(ActionListener l)       { undoButton.addActionListener(l); }
    public void addClearListener(ActionListener l)      { clearButton.addActionListener(l); }
    public void addExitListener(ActionListener l)       { exitButton.addActionListener(l); }

    // Métodos para actualizar la vista
    public void setHistoryText(String text) {
        historyTextArea.setText(text);
        historyTextArea.setCaretPosition(historyTextArea.getDocument().getLength());
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String[] showComputeDialog(List<String> functionNames) {
    JDialog dialog = new JDialog(this, "Select Function and Arguments", true);
    dialog.setSize(400, 220);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new BorderLayout(10, 10));

    JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JComboBox<String> combo = new JComboBox<>(functionNames.toArray(new String[0]));
    JTextField arg1Field = new JTextField("0.0", 12);
    JTextField arg2Field = new JTextField("0.0", 12);
    JLabel arg2Label = new JLabel("Argument 2:");
    arg2Label.setVisible(false);
    arg2Field.setVisible(false);

    // Listener para mostrar/ocultar arg2 según función (asumimos nombres que terminan en "2" o similar)
    // Nota: idealmente deberíamos preguntar al modelo si es 1 o 2 args → lo mejoramos después
    combo.addActionListener(e -> {
        String selected = (String) combo.getSelectedItem();
        boolean needsTwo = selected != null && 
            (selected.equalsIgnoreCase("add") || 
             selected.equalsIgnoreCase("subtract") || 
             selected.equalsIgnoreCase("multiply") || 
             selected.equalsIgnoreCase("divide") || 
             selected.equalsIgnoreCase("power") || 
             selected.contains("2"));  // temporal, luego usamos instanceof o metadata

        arg2Label.setVisible(needsTwo);
        arg2Field.setVisible(needsTwo);
        if (!needsTwo) arg2Field.setText("");  // limpiar si no se usa
    });

    // Trigger inicial
    combo.getActionListeners()[0].actionPerformed(null);

    panel.add(new JLabel("Function:"));
    panel.add(combo);
    panel.add(new JLabel("Argument 1:"));
    panel.add(arg1Field);
    panel.add(arg2Label);
    panel.add(arg2Field);

    JButton okButton = new JButton("Calculate");
    okButton.addActionListener(e -> dialog.dispose());

    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    bottom.add(okButton);
    dialog.add(panel, BorderLayout.CENTER);
    dialog.add(bottom, BorderLayout.SOUTH);

    dialog.setVisible(true);

    // Después de cerrar, devolvemos los valores
    if (arg1Field.getText().isEmpty()) return null;

    return new String[] {
        (String) combo.getSelectedItem(),
        arg1Field.getText().trim(),
        arg2Field.getText().trim()
    };
}
}
