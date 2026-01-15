package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class SquareRootFunction implements SingleArgMathFunction {
    
    private static String name = "SquareRoot";

    public String getName() {
        return name;
    }
    
    public double calculate(double arg) {
        return Math.sqrt(arg);
    }
    
    @Override
    public MathFunction createClone() {
        return new SquareRootFunction();
    }
    
    @Override
    public int getRequiredArguments() {
    return 1;
}
}
