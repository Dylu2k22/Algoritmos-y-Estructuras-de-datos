/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 5
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí un simulador de historial de navegación web en Java utilizando una pila enlazada simple de cadenas de texto (URLs). El programa debe exponer las operaciones visitarPagina(String url), volverAtras(), paginaActual() e imprimirHistorial(). Incluí comentarios teóricos que expliquen por qué la estructura de Pila (LIFO) modela con exacta precisión el comportamiento del botón 'Atrás' en un navegador, fundamentando que la última página web visitada es la primera en ser abandonada para revelar la página anterior. Probá en main la simulación con varias visitas y retrocesos."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se validan URL vacías o null; volver atrás
 * o consultar página actual sin historial genera una excepción explícita."
 * ============================================================================
 */
public class Ejercicio05_HistorialNavegacion {
    /*
     * LIFO modela la navegación: cada visita queda en la cima, y volver atrás
     * retira la última URL para dejar visible la anterior. Tanto visitar como
     * retroceder manipulan el inicio de la lista en O(1). Una navegación real
     * también separa historial futuro; aquí se representa solo la pila de atrás
     * solicitada, por lo que una nueva visita se agrega sobre la página actual.
     */
    private static final class NodoUrl {
        private final String url;
        private NodoUrl siguiente;

        private NodoUrl(String url, NodoUrl siguiente) {
            this.url = url;
            this.siguiente = siguiente;
        }
    }

    private NodoUrl head;

    public void visitarPagina(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL no puede ser null ni estar vacía.");
        }
        head = new NodoUrl(url, head);
        System.out.println("Visitando: " + url);
    }

    public String volverAtras() {
        if (head == null || head.siguiente == null) {
            throw new IllegalStateException("No hay páginas previas en el historial.");
        }
        String abandonada = head.url;
        head = head.siguiente;
        System.out.println("Se abandonó " + abandonada + "; página actual: " + head.url);
        return head.url;
    }

    public String paginaActual() {
        if (head == null) {
            throw new IllegalStateException("No hay una página actual.");
        }
        return head.url;
    }

    public void imprimirHistorial() {
        System.out.print("Página actual -> ");
        for (NodoUrl actual = head; actual != null; actual = actual.siguiente) {
            System.out.print(actual.url);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Ejercicio05_HistorialNavegacion historial = new Ejercicio05_HistorialNavegacion();
        historial.visitarPagina("https://inicio.example");
        historial.visitarPagina("https://materia.example");
        historial.visitarPagina("https://ejercicios.example");
        historial.imprimirHistorial();
        System.out.println("Actual: " + historial.paginaActual());
        historial.volverAtras();
        historial.imprimirHistorial();
        historial.volverAtras();
        System.out.println("Actual tras retroceder: " + historial.paginaActual());
    }
}
