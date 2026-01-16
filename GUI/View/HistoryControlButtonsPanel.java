package GUI.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HistoryControlButtonsPanel extends JPanel {

    private JTextArea historyTextArea;
    private JButton undoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;

    public HistoryControlButtonsPanel() {
        super(new BorderLayout(5, 5));
        setBorder(BorderFactory.createTitledBorder("Historial y Controles"));
        setPreferredSize(new Dimension(400, 200)); 

        // Área de historial (centro)
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        historyTextArea.setLineWrap(true);
        historyTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        undoButton  = new JButton("Undo");
        saveButton  = new JButton("Save");
        loadButton  = new JButton("Load");
        clearButton = new JButton("Clear");

        buttonsPanel.add(undoButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(clearButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        for (JButton btn : new JButton[]{undoButton, saveButton, loadButton, clearButton}) {
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setFocusPainted(false);
        }
    }

    // Métodos para actualizar el historial desde el controller
    public void setHistoryText(String text) {
        historyTextArea.setText(text);
        historyTextArea.setCaretPosition(historyTextArea.getDocument().getLength());
    }

    public String getHistoryText() {
        return historyTextArea.getText();
    }

    // Métodos para registrar listeners
    public void addUndoListener(ActionListener l)   { undoButton.addActionListener(l); }
    public void addSaveListener(ActionListener l)   { saveButton.addActionListener(l); }
    public void addLoadListener(ActionListener l)   { loadButton.addActionListener(l); }
    public void addClearListener(ActionListener l)  { clearButton.addActionListener(l); }
}