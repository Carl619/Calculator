package GUI.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScientificFunctionsPanel extends JPanel {

    // Funciones científicas (1 argumento)
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton logButton;
    private JButton expButton;
    private JButton sqrtButton;

    // Operaciones binarias / funciones de 2 argumentos
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton powerButton;

    public ScientificFunctionsPanel() {
        super(new GridLayout(0, 2, 10, 10));  // 2 columnas, filas automáticas
        setBorder(BorderFactory.createTitledBorder("Funciones"));

        // Primera fila: científicas
        add(sinButton     = createFunctionButton("sin"));
        add(cosButton     = createFunctionButton("cos"));
        
        add(tanButton     = createFunctionButton("tan"));    
        add(logButton     = createFunctionButton("log"));

        add(expButton     = createFunctionButton("exp"));
        add(sqrtButton    = createFunctionButton("√"));

        // Segunda sección: operaciones básicas / binarias
        add(addButton      = createFunctionButton("+"));
        add(subtractButton = createFunctionButton("-"));

        add(multiplyButton = createFunctionButton("×"));
        add(divideButton   = createFunctionButton("÷"));

        add(powerButton    = createFunctionButton("xʸ"));     // o "^" o "pow"

        // Estilo uniforme
        for (Component c : getComponents()) {
            if (c instanceof JButton btn) {
                btn.setFont(new Font("Arial", Font.PLAIN, 18));
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(80, 50));  // tamaño más cómodo
            }
        }
    }

    private JButton createFunctionButton(String text) {
        JButton btn = new JButton(text);
        
        if (text.matches("[+−×÷xʸ]")) {
            btn.setBackground(new Color(240, 240, 240));
        } else {
            btn.setBackground(new Color(220, 240, 255)); 
        }
        return btn;
    }

    /**
     * Registra el mismo listener en TODOS los botones del panel
     */
    public void addFunctionListener(ActionListener listener) {
        sinButton.addActionListener(listener);
        cosButton.addActionListener(listener);
        if (tanButton != null) tanButton.addActionListener(listener);
        logButton.addActionListener(listener);
        expButton.addActionListener(listener);
        sqrtButton.addActionListener(listener);

        addButton.addActionListener(listener);
        subtractButton.addActionListener(listener);
        multiplyButton.addActionListener(listener);
        divideButton.addActionListener(listener);
        powerButton.addActionListener(listener);

        
    }
}