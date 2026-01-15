package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class TanFunction implements SingleArgMathFunction {
    
    private static String name = "Tan";

    public String getName() {
        return name;
    }
    
    public double calculate(double arg) {
        return Math.tan(arg);
    }
    
    @Override
    public MathFunction createClone() {
        return new TanFunction();
    }
    
    @Override
    public int getRequiredArguments() {
    return 1;
}
}
