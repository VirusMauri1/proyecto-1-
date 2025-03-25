import java.util.List;
import java.util.stream.Collectors;

public class Interpreter {
    private final Environment globalEnv;

    public Interpreter() {
        globalEnv = new Environment();
        setupGlobalEnvironment();
    }
