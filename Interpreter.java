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
