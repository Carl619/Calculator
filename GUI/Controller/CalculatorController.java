package GUI.Controller;

import GUI.View.CalculatorView;
import CalculatorLogic.FlexCalculator;
import CalculatorLogic.CalculatorObserver;
import MathFunctions.MathFunction;
import MathFunctions.MathFunctionFactory;
import java.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CalculatorController implements CalculatorObserver{

    private final FlexCalculator model;
    private final CalculatorView view;

    public CalculatorController(FlexCalculator model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        model.addObserver(this);
        view.addComputeListener(new ComputeAction());
        view.addShowHistoryListener(e -> refreshHistory());
        view.addUndoListener(e -> { model.undoLast(); refreshHistory(); });
        view.addClearListener(e -> { model.clearHistory(); refreshHistory(); });
        view.addExitListener(e -> System.exit(0));
        view.addSaveListener(e -> {
        model.saveHistory();
        view.showMessage("History saved");
    });

    view.addLoadListener(e -> {
        model.loadHistory();
        view.showMessage("History loaded");
    });
        refreshHistory();
    }
    
    @Override
    public void onHistoryChanged() {
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
            
            Map<String, Integer> argCountMap = new HashMap<>();
for (String name : functionNames) {
    MathFunction f = MathFunctionFactory.create(name);
    if (f != null) {
        argCountMap.put(name, f.getRequiredArguments());
    }
}
        String[] input = view.showComputeDialog(functionNames, argCountMap);
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
            ex.printStackTrace();
        }
            
            
        }
    }
}
