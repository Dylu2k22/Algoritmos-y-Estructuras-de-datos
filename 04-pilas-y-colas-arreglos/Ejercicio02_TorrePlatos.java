/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 2
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí un programa en Java que simule la gestión de una torre de platos utilizando un arreglo interno de cadenas de texto (String[]) o enteros para representar cada plato. El programa debe permitir agregar un plato a la torre, retirar el plato superior y consultar cuál es el plato ubicado en la cima. Agregá comentarios explicativos detallados que justifiquen técnicamente por qué este dominio de problema se resuelve naturally mediante una Pila (Stack) bajo la regla LIFO (Last In, First Out) y no mediante una Cola (Queue), argumentando que en una pila física de platos el último plato colocado arriba es obligatoriamente el único accesible y el primero en retirarse para evitar el colapso de la estructura. Diseñá un método main interactivo o de prueba que simule el apilado de varios platos, la consulta de la cima y el desapilado progresivo, mostrando el estado de la torre por consola en cada acción."
 * ============================================================================
 */
public class Ejercicio02_TorrePlatos {
    private final String[] platos;
    // La torre sigue LIFO: Last In, First Out. Un plato físico solo puede
    // retirarse con seguridad desde arriba; sacar uno del medio o de la base
    // haría colapsar los que están encima. Una cola FIFO exigiría lo contrario.
    // top es el puntero de control a la cima y -1 representa la torre vacía.
    private int top = -1;

    public Ejercicio02_TorrePlatos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        platos = new String[capacidad];
    }

    public void agregar(String plato) {
        if (plato == null || plato.trim().isEmpty()) {
            throw new IllegalArgumentException("El plato debe tener una identificación.");
        }
        if (top == platos.length - 1) {
            // Overflow: no queda una celda donde apilar otro plato.
            throw new IllegalStateException("Overflow: la torre está llena.");
        }
        platos[++top] = plato;
    }

    public String retirar() {
        if (top == -1) {
            // Underflow: retirar de una torre vacía no tiene un elemento válido.
            throw new IllegalStateException("Underflow: no hay platos para retirar.");
        }
        String plato = platos[top];
        platos[top--] = null;
        return plato;
    }

    public String cima() {
        if (top == -1) {
            throw new IllegalStateException("Underflow: la torre está vacía.");
        }
        return platos[top];
    }

    public void mostrarEstado() {
        System.out.print("Base -> ");
        for (int i = 0; i <= top; i++) {
            System.out.print(platos[i] + (i < top ? " | " : ""));
        }
        System.out.println(top == -1 ? "(vacía)" : " <- Cima");
    }

    public static void main(String[] args) {
        Ejercicio02_TorrePlatos torre = new Ejercicio02_TorrePlatos(5);
        for (String plato : new String[]{"Plato A", "Plato B", "Plato C", "Plato D"}) {
            torre.agregar(plato);
            System.out.print("Se apiló " + plato + ": ");
            torre.mostrarEstado();
        }
        System.out.println("Plato accesible en la cima: " + torre.cima());
        while (torre.top >= 0) {
            System.out.println("Se retira " + torre.retirar() + ".");
            torre.mostrarEstado();
        }
    }
}
