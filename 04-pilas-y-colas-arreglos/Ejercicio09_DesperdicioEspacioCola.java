/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 9
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá un programa en Java que ponga de manifiesto de forma didáctica la limitación de una cola lineal basada en arreglos estándar: el problema del desperdicio de posiciones en memoria. Programá una clase ColaSimple con un arreglo de tamaño pequeño (por ejemplo, capacidad 5) y ejecutá una serie de operaciones de enqueue hasta llenar la capacidad, seguidas de varias operaciones de dequeue que vacíen los primeros índices desplazando el puntero front. El programa debe imprimir en consola el estado visual completo del arreglo indicando qué celdas contienen datos y cuáles quedaron como 'espacios fantasma' vacíos al inicio. Incluí comentarios explicativos solicitando explícitamente que se muestre cómo el sistema marca erróneamente un estado de desbordamiento (Overflow) al intentar encolar un nuevo elemento cuando rear alcanza el límite final, evidenciando la imposibilidad de reutilizar las posiciones libres del comienzo en una cola lineal no circular."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio09_DesperdicioEspacioCola {
    private static final class ColaSimple {
        private final int[] datos;
        // Esta cola conserva FIFO: front es el primero que saldrá y rear la
        // siguiente posición de entrada. Ambos avanzan sin circularidad.
        private int front = 0;
        private int rear = 0;

        ColaSimple(int capacidad) {
            datos = new int[capacidad];
        }

        void enqueue(int valor) {
            // En una cola lineal rear nunca retrocede; aunque queden huecos antes
            // de front, rear == capacidad causa el falso Overflow didáctico.
            if (rear == datos.length) {
                throw new IllegalStateException("Overflow lineal: rear llegó al final del arreglo.");
            }
            datos[rear++] = valor;
        }

        int dequeue() {
            if (front == rear) {
                // Underflow: no existe elemento pendiente para extraer.
                throw new IllegalStateException("Underflow: la cola está vacía.");
            }
            int valor = datos[front];
            datos[front++] = 0;
            return valor;
        }

        void mostrar(String momento) {
            System.out.println(momento + ": " + Arrays.toString(datos)
                    + " | front=" + front + ", rear=" + rear);
            for (int i = 0; i < datos.length; i++) {
                if (i < front) {
                    System.out.println("  índice " + i + ": espacio fantasma (liberado)");
                }
            }
        }
    }

    public static void main(String[] args) {
        ColaSimple cola = new ColaSimple(5);
        for (int valor = 10; valor <= 50; valor += 10) {
            cola.enqueue(valor);
        }
        cola.mostrar("Cola llena");
        System.out.println("Se desencolan " + cola.dequeue() + ", " + cola.dequeue()
                + " y " + cola.dequeue());
        cola.mostrar("Luego de liberar las primeras tres celdas");
        try {
            cola.enqueue(60);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage()
                    + " Las celdas libres al inicio no se reutilizan.");
        }
        cola.mostrar("Estado tras el intento de enqueue");
    }
}
