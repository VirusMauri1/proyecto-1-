import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Intérprete LISP Iniciado. Escriba 'exit' para salir.");

        while (true) {
            System.out.print("LISP> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Saliendo del intérprete...");
                break;
            }

            try {
                Expression parsedExpression = Expression.parse(input);
                Object result = interpreter.evaluate(parsedExpression);
                if (result != null) {
                    System.out.println("Resultado: " + result);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
