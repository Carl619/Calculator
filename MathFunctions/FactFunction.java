package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class FactFunction implements SingleArgMathFunction {
    
    private static String name = "Fact";

    public String getName() {
        return name;
    }
    
    public static long factorial(double n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public double calculate(double arg) {
        return factorial(arg);
    }
    
    @Override
    public MathFunction createClone() {
        return new FactFunction();
    }
}
