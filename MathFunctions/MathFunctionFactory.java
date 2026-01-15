package MathFunctions;

import java.util.*;

public class MathFunctionFactory {

    private static final Map<String, MathFunction> registry = new HashMap<>();

    static {
        register(new SinFunction());
        register(new CosFunction());
        register(new TanFunction());
        register(new LogFunction());
        register(new ExpFunction());
        register(new SquareRootFunction());
        register(new AddFunction());
        register(new MinusFunction());
        register(new MultiplyFunction());
        register(new DivideFunction());
        register(new FactFunction());
    }

    private static void register(MathFunction function) {
        String key = function.getName().toLowerCase().trim();
        if (registry.containsKey(key)) {
            System.err.println("Warning: Duplicate function registration: " + key);
        }
        registry.put(key, function);
    }

    /**
     * Intenta crear una nueva instancia de la función.
     * Devuelve null si no existe (en lugar de excepción inmediata)
     */
    public static MathFunction create(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        String key = name.toLowerCase().trim();
        MathFunction prototype = registry.get(key);

        if (prototype == null) {
            return null;
        }

        return prototype.createClone();
    }

    public static List<String> getAvailableFunctionNames() {
        return new ArrayList<>(registry.keySet());
    }

    public static MathFunction createOrThrow(String name) throws Exception {
        MathFunction func = create(name);
        if (func == null) {
            throw new Exception("The function '" + name + "' Doesn't exist ");
        }
        return func;
    }
}