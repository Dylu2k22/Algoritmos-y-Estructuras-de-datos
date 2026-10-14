/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 7
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un algoritmo en Java que determine si los paréntesis de una expresión matemática están correctamente balanceados utilizando una pila enlazada de caracteres. Incluí comentarios teóricos que expliquen el algoritmo: cada paréntesis de apertura '(' se apila mediante push, y cada paréntesis de cierre ')' desencadena un pop previa verificación de que la pila no esté vacía. Explicá por qué la expresión es válida solo si la pila queda totalmente vacía al finalizar el recorrido. Probá en main expresiones válidas como '(2 + 3) * (5 - 1)' y '((a + b) * c)' e inválidas como '(2 + 3' y '())('."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se cubrieron cierres sin apertura, aperturas
 * pendientes y expresión null; los caracteres no parentéticos se ignoran."
 * ============================================================================
 */
public class Ejercicio07_VerificadorParentesis {
    /*
     * Cada '(' se apila. Ante ')', si no hay nodo en la pila, falta una apertura
     * previa y la expresión es inválida; si lo hay, se desapila su pareja.
     * Al terminar, la pila debe estar vacía para asegurar que no quedó una
     * apertura sin cerrar. El recorrido cuesta O(n) y la pila usa como máximo
     * O(n) nodos dinámicos, donde n es la longitud de la expresión.
     */
    private static final class NodoCaracter {
        private final char dato;
        private NodoCaracter siguiente;

        private NodoCaracter(char dato, NodoCaracter siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private static final class PilaCaracteres {
        private NodoCaracter head;

        private void push(char dato) {
            head = new NodoCaracter(dato, head);
        }

        private char pop() {
            if (head == null) {
                throw new IllegalStateException("La pila de caracteres está vacía.");
            }
            char dato = head.dato;
            head = head.siguiente;
            return dato;
        }

        private boolean isEmpty() {
            return head == null;
        }
    }

    public static boolean estaBalanceada(String expresion) {
        if (expresion == null) {
            throw new IllegalArgumentException("La expresión no puede ser null.");
        }
        PilaCaracteres aperturas = new PilaCaracteres();
        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);
            if (caracter == '(') {
                aperturas.push(caracter);
            } else if (caracter == ')') {
                if (aperturas.isEmpty()) {
                    return false;
                }
                aperturas.pop();
            }
        }
        return aperturas.isEmpty();
    }

    public static void main(String[] args) {
        String[] expresiones = {
            "(2 + 3) * (5 - 1)",
            "((a + b) * c)",
            "(2 + 3",
            "())("
        };
        for (String expresion : expresiones) {
            System.out.println(expresion + " => "
                    + (estaBalanceada(expresion) ? "balanceada" : "no balanceada"));
        }
    }
}
