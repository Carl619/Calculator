package GUI;
import javax.swing.*;


/**
 * Write a description of class MathLogic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MathPanel extends JPanel
{
    // instance variables - replace the example below with your own
    private GUICalculator controller; // Reference to the parent frame

    /**
     * Constructor for objects of class MathLogic
     */
    public MathPanel(GUICalculator parentController)
    {
        // initialise instance variables
        this.controller = parentController;
        // ... other UI setup ...

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.selectOperation("add");
        });
        this.add(addButton);
        JButton equalButton = new JButton("=");
        addButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.selectOperation("equal");
        });
        this.add(equalButton);
    }

}
