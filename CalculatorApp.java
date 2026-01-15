import GUI.Controller.CalculatorController;
import GUI.View.CalculatorView;
import CalculatorLogic.FlexCalculator;

import javax.swing.*;

public class CalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlexCalculator model = new FlexCalculator();
            CalculatorView view = new CalculatorView();
            new CalculatorController(model, view);
            view.setVisible(true);
        });
    }
}
