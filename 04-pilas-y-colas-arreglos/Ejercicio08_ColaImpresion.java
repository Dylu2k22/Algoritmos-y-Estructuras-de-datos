/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 8
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un programa en Java que simule un spooler de impresión. Creá una clase Documento con los atributos nombre (String) y paginas (int), y gestionalos dentro de una clase ColaImpresion implementada con un arreglo de objetos Documento. El programa debe permitir enviar un documento a la cola (enqueue), procesar e imprimir el documento en turno (dequeue) y consultar el documento que encabeza la lista de espera. Agregá comentarios teóricos que expliquen detalladamente cómo la estructura de Cola representa con precisión la justicia del orden de impresión, asegurando que las solicitudes enviadas por los usuarios se procesen en la secuencia temporal exacta en que fueron recibidas sin alterar el orden. Incluí un método main que envíe varios documentos con distintos tamaños de página a la cola y simule la impresión paso a paso, mostrando en consola los datos de cada documento procesado."
 * ============================================================================
 */
public class Ejercicio08_ColaImpresion {
    public static final class Documento {
        private final String nombre;
        private final int paginas;

        public Documento(String nombre, int paginas) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El documento debe tener nombre.");
            }
            if (paginas <= 0) {
                throw new IllegalArgumentException("La cantidad de páginas debe ser positiva.");
            }
            this.nombre = nombre;
            this.paginas = paginas;
        }

        @Override
        public String toString() {
            return nombre + " (" + paginas + " páginas)";
        }
    }

    /*
     * FIFO conserva la secuencia temporal de solicitudes: el documento que
     * primero se envió es el primero procesado. El puntero front indica el
     * trabajo en turno y rear la posición libre siguiente para enqueue.
     * Si rear llega a capacidad se informa Overflow; front == rear indica cola
     * vacía y procesar o consultar entonces informa Underflow.
     */
    public static final class ColaImpresion {
        private final Documento[] documentos;
        private int front = 0;
        private int rear = 0;

        public ColaImpresion(int capacidad) {
            if (capacidad <= 0) {
                throw new IllegalArgumentException("La capacidad debe ser positiva.");
            }
            documentos = new Documento[capacidad];
        }

        public void enqueue(Documento documento) {
            if (documento == null) {
                throw new IllegalArgumentException("El documento no puede ser null.");
            }
            if (rear == documentos.length) {
                throw new IllegalStateException("Overflow: la cola de impresión está llena.");
            }
            documentos[rear++] = documento;
        }

        public Documento dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Underflow: no hay documentos pendientes.");
            }
            Documento documento = documentos[front];
            documentos[front++] = null;
            return documento;
        }

        public Documento front() {
            if (isEmpty()) {
                throw new IllegalStateException("Underflow: no hay documento en turno.");
            }
            return documentos[front];
        }

        public boolean isEmpty() {
            return front == rear;
        }
    }

    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion(5);
        cola.enqueue(new Documento("Informe.pdf", 12));
        cola.enqueue(new Documento("Factura.pdf", 2));
        cola.enqueue(new Documento("Apuntes.pdf", 35));
        cola.enqueue(new Documento("Presentacion.pdf", 18));
        while (!cola.isEmpty()) {
            System.out.println("Siguiente en turno: " + cola.front());
            System.out.println("Imprimiendo: " + cola.dequeue());
        }
    }
}
