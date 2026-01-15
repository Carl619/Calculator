package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MinusFunction implements DoubleArgMathFunction {

    public String getName() { 
        return "Minus";
    }

    public double calculate(double x, double y) { 
        return x - y; 
    }
    
    @Override
    public MathFunction createClone() {
        return new MinusFunction();
    }
    @Override
    public int getRequiredArguments() {
    return 2;
}
}
