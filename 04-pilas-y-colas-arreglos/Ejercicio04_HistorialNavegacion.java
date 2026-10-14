/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 4
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java un simulador de historial de navegación web que almacene las direcciones URL visitadas por un usuario utilizando una Pila basada en un arreglo de cadenas (String[]). El programa debe proveer funciones para visitar una nueva página web (apilando su URL) y para retroceder a la página anterior ejecutando una operación pop que desapile la URL actual y revele la previa. Incluí comentarios teóricos en el código que expliquen por qué el historial de navegación opera estrictamente bajo el principio LIFO (Last In, First Out), fundamentando que la última página web accedida es la primera a la que se renuncia al presionar el botón 'Atrás', quedando las páginas más antiguas pausadas en el fondo de la pila. Incluí un método main que simule la navegación secuencial por al menos cuatro sitios web y realice llamadas a la función de retroceso, imprimiendo en pantalla la URL activa en todo momento."
 * ============================================================================
 */
public class Ejercicio04_HistorialNavegacion {
    private final String[] historial;
    // Las visitas forman una pila LIFO: Atrás quita primero la visita más
    // reciente y deja activa la página anterior, preservada debajo de top.
    // top=-1 identifica vacío; sus límites evitan Overflow y Underflow.
    private int top = -1;

    public Ejercicio04_HistorialNavegacion(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        historial = new String[capacidad];
    }

    public void visitar(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL no puede estar vacía.");
        }
        if (top == historial.length - 1) {
            throw new IllegalStateException("Overflow: el historial alcanzó su capacidad.");
        }
        historial[++top] = url;
    }

    public String atras() {
        if (top == -1) {
            throw new IllegalStateException("Underflow: no hay páginas en el historial.");
        }
        String actual = historial[top];
        historial[top--] = null;
        return actual;
    }

    public String paginaActiva() {
        return top == -1 ? null : historial[top];
    }

    public static void main(String[] args) {
        Ejercicio04_HistorialNavegacion navegador = new Ejercicio04_HistorialNavegacion(6);
        String[] sitios = {
            "https://www.iau.edu.ar",
            "https://www.wikipedia.org",
            "https://www.java.com",
            "https://docs.oracle.com"
        };
        for (String sitio : sitios) {
            navegador.visitar(sitio);
            System.out.println("Visitando: " + navegador.paginaActiva());
        }
        while (navegador.paginaActiva() != null) {
            System.out.println("Atrás desde " + navegador.atras()
                    + "; página activa: " + navegador.paginaActiva());
        }
    }
}
