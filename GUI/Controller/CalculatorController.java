package GUI.Controller;

import GUI.View.CalculatorView;
import CalculatorLogic.FlexCalculator;
import MathFunctions.MathFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CalculatorController {

    private final FlexCalculator model;
    private final CalculatorView view;

    public CalculatorController(FlexCalculator model, CalculatorView view) {
        this.model = model;
        this.view = view;

        view.addComputeListener(new ComputeAction());
        view.addShowHistoryListener(e -> refreshHistory());
        view.addUndoListener(e -> { model.undoLast(); refreshHistory(); });
        view.addClearListener(e -> { model.clearHistory(); refreshHistory(); });
        view.addExitListener(e -> System.exit(0));

        refreshHistory();
    }

    private void refreshHistory() {
        StringBuilder sb = new StringBuilder();
        List<String> hist = model.getHistory();
        if (hist.isEmpty()) {
            sb.append("No operations in history.");
        } else {
            for (String line : hist) {
                sb.append(line).append("\n");
            }
        }
        view.setHistoryText(sb.toString());
    }

    private class ComputeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> functionNames = model.getAvailableFunctions();
        String[] input = view.showComputeDialog(functionNames);
        if (input == null) return;

        String fnName = input[0];
        String arg1Str = input[1];
        String arg2Str = input[2];

        try {
            double arg1 = Double.parseDouble(arg1Str);
            double result;

            if (arg2Str == null || arg2Str.trim().isEmpty()) {
                result = model.doCalculation(fnName, arg1);
            } else {
                double arg2 = Double.parseDouble(arg2Str);
                result = model.doCalculation(fnName, arg1, arg2);
            }

            view.showMessage("Result: " + result);
            refreshHistory();

        } catch (NumberFormatException ex) {
            view.showError("Format number error");
        } catch (IllegalArgumentException ex) {
            view.showError("Error: " + ex.getMessage());
        } catch (Exception ex) {
            view.showError("Error: " + ex.getMessage());
            ex.printStackTrace(); // Para depuración en consola
        }
            
            
        }
    }
}
