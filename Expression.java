import java.util.ArrayList;
import java.util.List;

public class Expression {
    private final List<Object> elements;

    public Expression() {
        this.elements = new ArrayList<>();
    }

    public void add(Object obj) {
        elements.add(obj);
    }

    public Object get(int index) {
        return elements.get(index);
    }

    public int size() {
        return elements.size();
    }

    public List<Object> stream() {
        return elements;
    }

    public static Expression parse(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("La expresión está vacía.");
        }
