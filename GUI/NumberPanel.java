package GUI;
import javax.swing.*;

/**
 * Write a description of class NumberPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NumberPanel extends JPanel
{
    // instance variables - replace the example below with your own
    private GUICalculator controller; // Reference to the parent frame

    /**
     * Constructor for objects of class NumberPanel
     */
    public NumberPanel(GUICalculator parentController)
    {
         // initialise instance variables
        this.controller = parentController;
        // ... other UI setup ...

        JButton oneButton = new JButton("1");
        oneButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("1");
        });
        this.add(oneButton);
        JButton twoButton = new JButton("2");
        twoButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("2");
        });
        this.add(twoButton);
        JButton threeButton = new JButton("3");
        threeButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("3");
        });
        this.add(threeButton);
        JButton fourButton = new JButton("4");
        fourButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("4");
        });
        this.add(fourButton);
        JButton fiveButton = new JButton("5");
        fiveButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("5");
        });
        this.add(fiveButton);
        JButton sixButton = new JButton("6");
        sixButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("6");
        });
        this.add(sixButton);
        JButton sevenButton = new JButton("7");
        sevenButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("7");
        });
        this.add(sevenButton);
        JButton eightButton = new JButton("8");
        sevenButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("8");
        });
        this.add(eightButton);
        
        JButton nineButton = new JButton("9");
        nineButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("9");
        });
        this.add(nineButton);
        
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber("0");
        });
        this.add(zeroButton);
        JButton pointButton = new JButton(".");
        pointButton.addActionListener(e -> {
            // After successful login logic...
            // Call the controller's method to change the view
            controller.updateNumber(".");
        });
        this.add(pointButton);
    }
}
