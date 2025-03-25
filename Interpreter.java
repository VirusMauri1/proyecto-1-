import java.util.List;
import java.util.stream.Collectors;

public class Interpreter {
    private final Environment globalEnv;

    public Interpreter() {
        globalEnv = new Environment();
        setupGlobalEnvironment();
    }

    private void setupGlobalEnvironment() {
        globalEnv.define("+", (LispFunction) args -> args.stream().mapToInt(a -> (int) a).sum());

        globalEnv.define("-", (LispFunction) args -> {
            int result = (int) args.get(0);
            for (int i = 1; i < args.size(); i++) result -= (int) args.get(i);
            return result;
        });

        globalEnv.define("*", (LispFunction) args -> args.stream().mapToInt(a -> (int) a).reduce(1, (a, b) -> a * b));

        globalEnv.define("/", (LispFunction) args -> {
            int result = (int) args.get(0);
            for (int i = 1; i < args.size(); i++) {
                int d = (int) args.get(i);
                if (d == 0) throw new ArithmeticException("División por cero");
                result /= d;
            }
            return result;
        });

        globalEnv.define("print", (LispFunction) args -> {
            args.forEach(System.out::println);
            return null;
        });

       globalEnv.define("quote", (LispFunction) args -> args.get(0));

        globalEnv.define("setq", (LispFunction) args -> {
            String varName = (String) args.get(0);
            Object value = evaluate(args.get(1), globalEnv);
            globalEnv.define(varName, value);
            return value;
        });
        
        lobalEnv.define("defun", (LispFunction) args -> {
    String name = (String) args.get(0);
    Expression params = (Expression) args.get(1);
    Expression body = (Expression) args.get(2);
    Function function = new Function(params, body, globalEnv);
    globalEnv.define(name, function);
    return name;
});

globalEnv.define("cond", (LispFunction) args -> {
    for (int i = 0; i < args.size(); i += 2) {
        if ((boolean) evaluate(args.get(i), globalEnv)) {
            return evaluate(args.get(i + 1), globalEnv);
        }
    }
    return null;
});

globalEnv.define("atom", (LispFunction) args -> !(args.get(0) instanceof Expression));

globalEnv.define("list", (LispFunction) args -> {
    Expression list = new Expression();
    args.forEach(list::add);
    return list;
});

globalEnv.define("equal", (LispFunction) args -> args.get(0).equals(args.get(1)));

globalEnv.define("<", (LispFunction) args -> (int) args.get(0) < (int) args.get(1));
globalEnv.define(">", (LispFunction) args -> (int) args.get(0) > (int) args.get(1));
}

public Object evaluate(Object exp) {
return evaluate(exp, globalEnv);
}

public Object evaluate(Object exp, Environment env) {
if (exp instanceof String) {
    return env.lookup((String) exp);
} else if (exp instanceof Expression) {
    Expression list = (Expression) exp;
    if (list.size() == 0) {
        throw new IllegalArgumentException("Error: Se intentó evaluar una lista vacía.");
    }
            if ("defun".equals(first)) {
                String name = (String) list.get(1);
                Expression params = (Expression) list.get(2);
                Expression body = (Expression) list.get(3);
                Function function = new Function(params, body, env);
                env.define(name, function);
                return name;
            }

            //  Evaluar función normalmente
           Object functionObject = evaluate(first, env);

            if (!(functionObject instanceof LispFunction)) {
                throw new IllegalArgumentException("Error: '" + first + "' no es una función válida.");
            }

            List<Object> args = list.stream().subList(1, list.size())
                    .stream().map(arg -> evaluate(arg, env)).collect(Collectors.toList());

            return ((LispFunction) functionObject).apply(args);
        }

        return exp;
    }
}
