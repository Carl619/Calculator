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
    
    private NumericKeypadPanel keypadPanel;
    private ScientificFunctionsPanel functionsPanel;
    private HistoryControlButtonsPanel historyAndControlsPanel;
    
    

    private JTextField display;

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        //History
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyTextArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(historyTextArea), BorderLayout.CENTER);

        //Calculator Buttons Panel
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
        
        //Keypad Panel
        keypadPanel = new NumericKeypadPanel();
        add(keypadPanel, BorderLayout.CENTER);
        
        //Functions Panel
        functionsPanel = new ScientificFunctionsPanel();
        add(functionsPanel, BorderLayout.EAST);

        //History Control Panel
        historyAndControlsPanel = new HistoryControlButtonsPanel();
    add(historyAndControlsPanel, BorderLayout.SOUTH);
    }

    public void addComputeListener(ActionListener l)    { computeButton.addActionListener(l); }
    public void addShowHistoryListener(ActionListener l) { showHistoryButton.addActionListener(l); }
    public void addExitListener(ActionListener l)       { exitButton.addActionListener(l); }
    
    public void addNumberListener(ActionListener l) {
        keypadPanel.addNumberListener(l);
    }

    public void addOperatorListener(ActionListener l) {
        keypadPanel.addOperatorListener(l);
    }
    
    public void addEqualsListener(ActionListener l) {
    keypadPanel.getEqualsButton().addActionListener(l);
}

    public void addClearListener(ActionListener l) {
        keypadPanel.addClearListener(l);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setDisplayText(String text) {
        display.setText(text);
    }

    public String getDisplayText() {
        return display.getText();
    }

public void clearDisplay() {
    display.setText("0");
}

public void appendToDisplay(String text) {
    display.setText(display.getText() + text);
}
    
    public void addFunctionListener(ActionListener l) {
    functionsPanel.addFunctionListener(l);
}

// Historial
public void setHistoryText(String text) {
    historyAndControlsPanel.setHistoryText(text);
}

public String getHistoryText() {
    return historyAndControlsPanel.getHistoryText();
}
    
    // Controles inferiores
    public void addUndoListener(ActionListener l)   { historyAndControlsPanel.addUndoListener(l); }
    public void addSaveListener(ActionListener l)   { historyAndControlsPanel.addSaveListener(l); }
    public void addLoadListener(ActionListener l)   { historyAndControlsPanel.addLoadListener(l); }
    public void addClearListenerFromControls(ActionListener l) {
        historyAndControlsPanel.addClearListener(l);
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
