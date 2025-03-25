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
                // Verificar si la expresión es vacía antes de parsear
                if (input.isEmpty()) {
                    System.out.println("Error: La expresión no puede estar vacía.");
                    continue;
                }

                // Parsear la expresión
                Expression parsedExpression = Expression.parse(input);
                System.out.println("Expresión parseada: " + parsedExpression);

                // Evaluar la expresión
                Object result = interpreter.evaluate(parsedExpression);
                System.out.println("Resultado: " + result);

            } catch (Exception e) {
                System.err.println("Error al procesar la expresión: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
