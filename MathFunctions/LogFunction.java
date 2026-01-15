package MathFunctions;


/**
 * Write a description of class LogFunction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LogFunction implements SingleArgMathFunction
{
    public String getName() { 
        return "Log";
    }

    public double calculate(double arg) { 
        return Math.log(arg); 
    }
    
    @Override
    public MathFunction createClone() {
        return new LogFunction();
    }
}
