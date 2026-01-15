package GUI.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class CalculatorView extends JFrame {

    private JTextArea historyTextArea;
    private JButton computeButton;
    private JButton showHistoryButton;
    private JButton undoButton;
    private JButton clearButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton loadButton;

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyTextArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(historyTextArea), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        computeButton     = new JButton("Calculate");
        showHistoryButton = new JButton("Show history");
        saveButton        = new JButton("Save");
        loadButton        = new JButton("Load");
        undoButton        = new JButton("Undo");
        clearButton       = new JButton("Clear");
        exitButton        = new JButton("Exit");

        buttonsPanel.add(computeButton);
        buttonsPanel.add(showHistoryButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(undoButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void addComputeListener(ActionListener l)    { computeButton.addActionListener(l); }
    public void addShowHistoryListener(ActionListener l) { showHistoryButton.addActionListener(l); }
    public void addUndoListener(ActionListener l)       { undoButton.addActionListener(l); }
    public void addClearListener(ActionListener l)      { clearButton.addActionListener(l); }
    public void addExitListener(ActionListener l)       { exitButton.addActionListener(l); }
    public void addSaveListener(ActionListener l)          { saveButton.addActionListener(l); }
    public void addLoadListener(ActionListener l)          { loadButton.addActionListener(l); }

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

    public String[] showComputeDialog(List<String> functionNames, Map<String, Integer> argCountMap) {
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

    combo.addActionListener(e -> {
        String selected = (String) combo.getSelectedItem();
        if (selected != null) {
            Integer required = argCountMap.getOrDefault(selected, 1);
            boolean needsTwo = required == 2;
            arg2Label.setVisible(needsTwo);
            arg2Field.setVisible(needsTwo);
            if (!needsTwo) arg2Field.setText("");
        }
    });

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

    if (arg1Field.getText().isEmpty()) return null;

    return new String[] {
        (String) combo.getSelectedItem(),
        arg1Field.getText().trim(),
        arg2Field.getText().trim()
    };
}
}
