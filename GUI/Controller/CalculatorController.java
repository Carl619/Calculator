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
            List<String> names = new ArrayList<>();
            for (MathFunction f : model.getFunctions()) {
                names.add(f.getName());
            }

            String selected = view.showComputeDialog(names);
            if (selected == null) return;

            try {
                double result = model.doCalculation(selected, 0);
                view.showMessage("Result: " + result);
                refreshHistory();
            } catch (Exception ex) {
                view.showError("Error: " + ex.getMessage());
            }
        }
    }
}
