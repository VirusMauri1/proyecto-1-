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

        if (input.startsWith("'")) {
            Expression quoted = new Expression();
            quoted.add("quote");
            quoted.add(parse(input.substring(1)));
            return quoted;
        }

        if (input.startsWith("(") && input.endsWith(")")) {
            input = input.substring(1, input.length() - 1).trim();
            Expression exp = new Expression();
            int depth = 0;
            StringBuilder token = new StringBuilder();
            List<String> tokens = new ArrayList<>();

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '(') depth++;
                if (c == ')') depth--;
