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

    // Diálogo simple para computar (luego lo mejoramos)
    public String showComputeDialog(List<String> functionNames) {
        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select Function:",
                "Calculate",
                JOptionPane.QUESTION_MESSAGE,
                null,
                functionNames.toArray(),
                functionNames.get(0));

        return selected;
    }
}
