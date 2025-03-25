import java.util.List;

public class Function implements LispFunction {
    private final Expression params;
    private final Expression body;
    private final Environment env;

    public Function(Expression params, Expression body, Environment env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    @Override
    public Object apply(List<Object> args) {
        Environment localEnv = new Environment(env);
        for (int i = 0; i < params.size(); i++) {
            localEnv.define((String) params.get(i), args.get(i));
        }
        return new Interpreter().evaluate(body, localEnv);
    }
}
