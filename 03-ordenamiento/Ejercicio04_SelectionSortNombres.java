/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 04
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí un programa en Java que ordene alfabéticamente un arreglo de cadenas de texto recibido como entrada, utilizando la lista String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"}; y aplicando el algoritmo Selection Sort. El razonamiento del algoritmo consiste en buscar secuencialmente el nombre menor en orden lexicográfico e intercambiarlo con la posición actual. Por pantalla se debe mostrar el arreglo de nombres original y el arreglo resultante ordenado de A a Z. Agregá comentarios extensos en el código que razonen y expliquen cómo cambia la mecánica de comparación al trabajar con objetos de texto en lugar de tipos numéricos primitivos, detallando el uso obligatorio del método compareTo() o compareToIgnoreCase() de la clase String para evaluar la prioridad ASCII/Unicode. La verificación del correcto funcionamiento se comprobará imprimiendo la secuencia final y validando que 'Ana' ocupe el primer índice y 'Pedro' el último."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio04_SelectionSortNombres {
    public static void main(String[] args) {
        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};
        System.out.println("Nombres originales: " + Arrays.toString(nombres));
        selectionSort(nombres);
        System.out.println("Nombres ordenados:  " + Arrays.toString(nombres));
        if (!"Ana".equals(nombres[0]) || !"Pedro".equals(nombres[nombres.length - 1])) {
            throw new IllegalStateException("El orden alfabético no es el esperado.");
        }
    }

    public static void selectionSort(String[] nombres) {
        for (int inicio = 0; inicio < nombres.length - 1; inicio++) {
            int indiceMenor = inicio;
            for (int i = inicio + 1; i < nombres.length; i++) {
                // Los String son objetos: compareToIgnoreCase compara lexicográficamente
                // sus caracteres Unicode sin distinguir mayúsculas de minúsculas.
                // Una comparación numérica directa no es aplicable a referencias String.
                if (nombres[i].compareToIgnoreCase(nombres[indiceMenor]) < 0) {
                    indiceMenor = i;
                }
            }
            if (indiceMenor != inicio) {
                String auxiliar = nombres[inicio];
                nombres[inicio] = nombres[indiceMenor];
                nombres[indiceMenor] = auxiliar;
            }
        }
    }
}
