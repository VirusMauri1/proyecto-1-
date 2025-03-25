import java.util.List;

@FunctionalInterface
public interface LispFunction {
    Object apply(List<Object> args);
}
