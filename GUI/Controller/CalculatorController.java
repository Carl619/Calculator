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
    private double currentNumber = 0;         
    private double firstNumber = 0;            
    private String pendingOperator = "";       
    private boolean isNewInput = true;         
    private boolean hasError = false;          

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
    view.addNumberListener(e -> {
    JButton source = (JButton) e.getSource();
    String text = source.getText();

    if (hasError) {
        clearDisplay();
        hasError = false;
    }

    if (isNewInput) {
        view.setDisplayText("");
        isNewInput = false;
    }

    // Evitar múltiples puntos
    if (text.equals(".") && view.getDisplayText().contains(".")) {
        return;
    }

    view.setDisplayText(view.getDisplayText() + text);
});

view.addFunctionListener(e -> {
    JButton btn = (JButton) e.getSource();
    String label = btn.getText();
    String functionName = normalizeFunctionName(label);

    handleFunction(functionName);
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
    
    private void clearDisplay() {
    view.clearDisplay();
    currentNumber = 0;
    firstNumber = 0;
    pendingOperator = "";
    isNewInput = true;
    hasError = false;
}

private void handleFunction(String rawFunctionName) {
    String functionName = normalizeFunctionName(rawFunctionName);

    String currentText = view.getDisplayText().trim();

    if (currentText.isEmpty() || currentText.equals("0") || hasError) {
        view.showError("Introduce un número antes de aplicar una función");
        return;
    }

    double arg1;
    try {
        arg1 = Double.parseDouble(currentText);
    } catch (NumberFormatException ex) {
        view.setDisplayText("Error");
        hasError = true;
        view.showError("Valor no válido en pantalla");
        return;
    }

    MathFunction func = MathFunctionFactory.create(functionName);
    if (func == null) {
        view.showError("Función no encontrada: " + functionName);
        return;
    }

    int required = func.getRequiredArguments();
    double result = 0;
    boolean success = false;

    if (required == 1) {
        try {
            result = model.doCalculation(functionName, arg1);
            success = true;
        } catch (Exception ex) {
            view.showError("Error en " + functionName + ": " + ex.getMessage());
        }
    } 
    else if (required == 2) {
        String secondStr = JOptionPane.showInputDialog(
            view,
            "Introduce el segundo valor para " + rawFunctionName + ":",
            "Segundo argumento",
            JOptionPane.QUESTION_MESSAGE
        );

        if (secondStr == null || secondStr.trim().isEmpty()) {
            return;
        }

        double arg2;
        try {
            arg2 = Double.parseDouble(secondStr.trim());
            result = model.doCalculation(functionName, arg1, arg2);
            success = true;
        } catch (NumberFormatException ex) {
            view.showError("Segundo valor no válido");
        } catch (Exception ex) {
            view.showError("Error en " + functionName + ": " + ex.getMessage());
        }
    } 
    else {
        view.showError("Número de argumentos no soportado (" + required + ")");
        return;
    }

    if (success) {
        view.setDisplayText(formatResult(result));
        hasError = false;
        isNewInput = true;
    } else {
        view.setDisplayText("Error");
        hasError = true;
    }
}

private String normalizeFunctionName(String label) {
    return switch (label) {
        case "√"   -> "sqrt";
        case "xʸ", "^" -> "power";
        case "×"   -> "multiply";
        case "÷"   -> "divide";
        case "+"   -> "add";
        case "-"   -> "subtract";
        default    -> label.toLowerCase().trim();
    };
}

private String formatResult(double value) {
    if (value == Math.floor(value)) {
        return String.valueOf((long) value);
    }
    String formatted = String.format("%.8f", value);
    formatted = formatted.replaceAll("\\.?0+$", "");
    return formatted;
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
