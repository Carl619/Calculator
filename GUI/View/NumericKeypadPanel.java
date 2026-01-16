package GUI.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumericKeypadPanel extends JPanel {

    private JButton[] numberButtons = new JButton[10];
    private JButton dotButton;
    private JButton equalsButton;
    private JButton clearButton;

    public NumericKeypadPanel() {
        super(new GridLayout(5, 4, 8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addNumberButton("7");
        addNumberButton("8");
        addNumberButton("9");

        addNumberButton("4");
        addNumberButton("5");
        addNumberButton("6");

        addNumberButton("1");
        addNumberButton("2");
        addNumberButton("3");

        addNumberButton("0");
        add(dotButton = createStyledButton("."));
        add(equalsButton = createStyledButton("="));

        
        add(new JLabel("")); 
        add(new JLabel(""));
        add(clearButton = createStyledButton("C"));
        add(new JLabel(""));
    }

    private void addNumberButton(String label) {
        JButton btn = createStyledButton(label);
        int num = Integer.parseInt(label);
        numberButtons[num] = btn;
        add(btn);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setFocusPainted(false);
 
        if (text.matches("[+\\-*/=C]")) {
            btn.setBackground(new Color(220, 220, 220));
        }
        return btn;
    }

    public void addNumberListener(ActionListener listener) {
        for (JButton btn : numberButtons) {
            if (btn != null) btn.addActionListener(listener);
        }
        dotButton.addActionListener(listener);
    }

    public void addOperatorListener(ActionListener listener) {
        equalsButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public JButton getEqualsButton() { return equalsButton; }
    public JButton getClearButton()  { return clearButton;  }
}