package GUI;
import java.awt.*;       // for any AWT features used
import java.awt.event.*; // for the listener
import javax.swing.*;    // for swing
import java.text.*;      // for the text formatting

/**
 * Write a description of class GUICalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUICalculator  extends JFrame
{
    // instance variables - replace the example below with your own
    private JPanel mathLogic;
    private JPanel history;
    private MathLogic logic;
    private CalculatorController controller;
    /**
     * Constructor for objects of class GUICalculator
     */
    public GUICalculator()
    {
        // initialise instance variables
        super("Calculator");
    }
    
    public void updateNumber(String number)
    {
        if(isNumeric(number))
        {
            logic.updateNumber(number);
        }
    }
    
    public static boolean isNumeric(String str) {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
    public void selectOperation(String op) {
        logic.setOperation(op);
    }
    
    public void showPanel(String panelName) {
        //cardLayout.show(cardPanel, panelName);
    }
    
}
