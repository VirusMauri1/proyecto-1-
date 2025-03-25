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

     globalEnv.define("defun", (LispFunction) args -> {
            String name = (String) args.get(0);
            Expression params = (Expression) args.get(1);
            Expression body = (Expression) args.get(2);
            globalEnv.define(name, new Function(params, body, globalEnv));
            return name;
        });
