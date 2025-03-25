mport org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    private Interpreter interpreter;

    @BeforeEach
    public void setUp() {
        interpreter = new Interpreter();
    }

    @Test
    public void testSuma() {
        Expression exp = Expression.parse("(+ 2 3)");
        Object result = interpreter.evaluate(exp);
        assertEquals(5, result);
    }

    @Test
    public void testAsignacionYUsoVariable() {
        interpreter.evaluate(Expression.parse("(setq x 10)"));
        Object result = interpreter.evaluate("x");
        assertEquals(10, result);
    }

    @Test
    public void testFuncionCuadrado() {
        interpreter.evaluate(Expression.parse("(defun cuadrado (x) (* x x))"));
        Object result = interpreter.evaluate(Expression.parse("(cuadrado 4)"));
        assertEquals(16, result);
    }

    @Test
    public void testCondicional() {
        interpreter.evaluate(Expression.parse("(setq a 5)"));
        Object result = interpreter.evaluate(Expression.parse("(cond (< a 10) 100 (t 200))"));
        assertEquals(100, result);
    }

    @Test
    public void testRecursividadFactorial() {
        interpreter.evaluate(Expression.parse(
            "(defun factorial (n) (cond (< n 2) 1 (t (* n (factorial (- n 1))))))"
        ));
        Object result = interpreter.evaluate(Expression.parse("(factorial 5)"));
        assertEquals(120, result);
    }
}
