/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 9
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un sistema de cola de impresión en Java utilizando una cola enlazada de objetos TrabajoImpresion (atributos: nombreArchivo, cantidadPaginas, usuario). El programa debe soportar agregarTrabajo(TrabajoImpresion t), imprimirProximo(), consultarProximo() y mostrarPendientes(). Agregá comentarios teóricos que expliquen por qué un spooler de impresión debe operar bajo la política FIFO para evitar la postergación indefinida (starvation) de los archivos enviados en primer lugar. Probá el sistema en el método main con múltiples trabajos de impresión."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y la validación de trabajo, archivo, usuario y
 * páginas; la consulta e impresión de una cola vacía reportan excepción."
 * ============================================================================
 */
public class Ejercicio09_ColaImpresion {
    /*
     * El spooler agrega los trabajos al tail y siempre imprime desde head.
     * FIFO evita que solicitudes anteriores sean desplazadas repetidamente
     * por trabajos nuevos, reduciendo la postergación indefinida (starvation)
     * bajo la política ordinaria de orden de llegada. enqueue y dequeue son O(1).
     */
    private static final class TrabajoImpresion {
        private final String nombreArchivo;
        private final int cantidadPaginas;
        private final String usuario;

        private TrabajoImpresion(String nombreArchivo, int cantidadPaginas, String usuario) {
            if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del archivo es obligatorio.");
            }
            if (cantidadPaginas <= 0) {
                throw new IllegalArgumentException("La cantidad de páginas debe ser positiva.");
            }
            if (usuario == null || usuario.trim().isEmpty()) {
                throw new IllegalArgumentException("El usuario es obligatorio.");
            }
            this.nombreArchivo = nombreArchivo;
            this.cantidadPaginas = cantidadPaginas;
            this.usuario = usuario;
        }

        @Override
        public String toString() {
            return nombreArchivo + " (" + cantidadPaginas + " páginas, usuario "
                    + usuario + ")";
        }
    }

    private static final class NodoTrabajo {
        private final TrabajoImpresion trabajo;
        private NodoTrabajo siguiente;

        private NodoTrabajo(TrabajoImpresion trabajo) {
            this.trabajo = trabajo;
        }
    }

    private NodoTrabajo head;
    private NodoTrabajo tail;

    public void agregarTrabajo(TrabajoImpresion trabajo) {
        if (trabajo == null) {
            throw new IllegalArgumentException("El trabajo no puede ser null.");
        }
        NodoTrabajo nuevo = new NodoTrabajo(trabajo);
        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        System.out.println("Trabajo agregado: " + trabajo);
    }

    public TrabajoImpresion imprimirProximo() {
        if (head == null) {
            throw new IllegalStateException("No hay trabajos pendientes de impresión.");
        }
        TrabajoImpresion impreso = head.trabajo;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }
        System.out.println("Imprimiendo: " + impreso);
        return impreso;
    }

    public TrabajoImpresion consultarProximo() {
        if (head == null) {
            throw new IllegalStateException("No hay un trabajo próximo.");
        }
        return head.trabajo;
    }

    public void mostrarPendientes() {
        System.out.print("Pendientes: ");
        if (head == null) {
            System.out.println("(ninguno)");
            return;
        }
        for (NodoTrabajo actual = head; actual != null; actual = actual.siguiente) {
            System.out.print("[" + actual.trabajo + "]");
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Ejercicio09_ColaImpresion spooler = new Ejercicio09_ColaImpresion();
        spooler.agregarTrabajo(new TrabajoImpresion("informe.pdf", 12, "Dylan"));
        spooler.agregarTrabajo(new TrabajoImpresion("factura.pdf", 2, "Ana"));
        spooler.agregarTrabajo(new TrabajoImpresion("apuntes.pdf", 35, "Bruno"));
        spooler.mostrarPendientes();
        System.out.println("Siguiente: " + spooler.consultarProximo());
        spooler.imprimirProximo();
        spooler.mostrarPendientes();
        spooler.imprimirProximo();
        spooler.imprimirProximo();
        spooler.mostrarPendientes();
    }
}
