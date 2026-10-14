/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 3
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un algoritmo en Java que reciba una cadena de texto representando una expresión matemática y determine si sus paréntesis están correctamente balanceados, retornando true si es válida o false en caso contrario. Utilizá una Pila (Stack) basada en arreglos para guardar únicamente los caracteres de paréntesis abiertos '(' a medida que se recorre la expresión de izquierda a derecha. Incluí comentarios explicativos que detallen la lógica del algoritmo: cada vez que se encuentra un paréntesis de apertura '(' se realiza un push, y cada vez que se encuentra uno de cierre ')' se debe ejecutar un pop previa verificación de que la pila no esté vacía (lo cual indicaría un cierre sin apertura correspondiente). Explicá además que, al finalizar el recorrido de la cadena, la expresión será válida únicamente si la pila queda totalmente vacía. Probá la implementación en el método main evaluando expresiones válidas como \"(5 + 3) * (2 + 1)\" e inválidas como \"(5 + 3)) * (2 + 1\" y \"((1 + 2)\", mostrando el resultado de la validación en pantalla."
 * ============================================================================
 */
public class Ejercicio03_ParentesisBalanceados {
    private static final class PilaCaracteres {
        private final char[] datos;
        private int top = -1;

        PilaCaracteres(int capacidad) {
            datos = new char[capacidad];
        }

        void push(char caracter) {
            if (top == datos.length - 1) {
                // Overflow protege el arreglo; con capacidad igual al largo de
                // la expresión no debería ocurrir antes de procesar sus caracteres.
                throw new IllegalStateException("Overflow inesperado de la pila.");
            }
            datos[++top] = caracter;
        }

        void pop() {
            if (top == -1) {
                // Underflow señala un ')' sin '(' previo.
                throw new IllegalStateException("Underflow: no hay apertura correspondiente.");
            }
            top--;
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    public static boolean estanBalanceados(String expresion) {
        if (expresion == null) {
            throw new IllegalArgumentException("La expresión no puede ser null.");
        }
        PilaCaracteres abiertos = new PilaCaracteres(expresion.length());
        for (int i = 0; i < expresion.length(); i++) {
            char actual = expresion.charAt(i);
            if (actual == '(') {
                abiertos.push(actual);
            } else if (actual == ')') {
                // Un cierre sin apertura previa es inválido; no se intenta hacer pop.
                if (abiertos.isEmpty()) {
                    return false;
                }
                abiertos.pop();
            }
        }
        // Las aperturas sin cerrar quedan en la pila, por eso también invalidan.
        return abiertos.isEmpty();
    }

    public static void main(String[] args) {
        String[] expresiones = {
            "(5 + 3) * (2 + 1)",
            "(5 + 3)) * (2 + 1",
            "((1 + 2)"
        };
        for (String expresion : expresiones) {
            System.out.println(expresion + " -> " + estanBalanceados(expresion));
        }
    }
}
