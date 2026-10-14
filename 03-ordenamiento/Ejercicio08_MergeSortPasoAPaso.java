/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 08
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí en Java el algoritmo MergeSort para ordenar un arreglo de enteros desordenado recibido como parámetro de entrada. El razonamiento de MergeSort se basa en Divide y Vencerás mediante división matemática exacta a la mitad sin pivotes. El programa debe mostrar paso a paso en consola: el proceso de división recursiva indicando los límites de cada subarreglo, el momento exacto en que se alcanza la condición de corte (subarreglos de 1 solo elemento) y la fase progresiva de fusión de subarreglos en orden. Agregá comentarios claros en el código que expliquen la diferencia conceptual entre la fase de división (que fragmenta el problema de forma recursiva) y la fase de fusión o mezcla (merge) que realiza el trabajo real de comparación y ordenamiento en memoria auxiliar. Para verificar el correcto funcionamiento, el programa debe mostrar en pantalla toda la traza de división y mezcla hasta devolver el arreglo consolidado y ordenado."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio08_MergeSortPasoAPaso {
    public static void main(String[] args) {
        int[] numeros = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        mergeSort(numeros, 0, numeros.length - 1, 0);
        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        if (!estaOrdenado(numeros)) throw new IllegalStateException("Merge Sort no ordenó el arreglo.");
    }

    private static void mergeSort(int[] numeros, int inicio, int fin, int nivel) {
        String sangria = repetir("  ", nivel);
        if (inicio == fin) {
            System.out.println(sangria + "Corte: [" + inicio + ", " + fin + "] = " + numeros[inicio]);
            return;
        }
        if (inicio > fin) return;
        int medio = inicio + (fin - inicio) / 2;
        // La división solo fragmenta el rango; todavía no compara ni ordena valores.
        System.out.println(sangria + "Dividir [" + inicio + ", " + fin + "] en ["
                + inicio + ", " + medio + "] y [" + (medio + 1) + ", " + fin + "]");
        mergeSort(numeros, inicio, medio, nivel + 1);
        mergeSort(numeros, medio + 1, fin, nivel + 1);
        // El trabajo de ordenamiento se realiza al fusionar las mitades ya ordenadas
        // mediante un arreglo auxiliar y elegir repetidamente el menor siguiente.
        merge(numeros, inicio, medio, fin);
        System.out.println(sangria + "Mezcla [" + inicio + ", " + fin + "]: "
                + Arrays.toString(Arrays.copyOfRange(numeros, inicio, fin + 1)));
    }

    private static void merge(int[] numeros, int inicio, int medio, int fin) {
        int[] auxiliar = new int[fin - inicio + 1];
        int izquierda = inicio, derecha = medio + 1, destino = 0;
        while (izquierda <= medio && derecha <= fin) {
            if (numeros[izquierda] <= numeros[derecha]) auxiliar[destino++] = numeros[izquierda++];
            else auxiliar[destino++] = numeros[derecha++];
        }
        while (izquierda <= medio) auxiliar[destino++] = numeros[izquierda++];
        while (derecha <= fin) auxiliar[destino++] = numeros[derecha++];
        System.arraycopy(auxiliar, 0, numeros, inicio, auxiliar.length);
    }

    private static String repetir(String texto, int cantidad) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cantidad; i++) resultado.append(texto);
        return resultado.toString();
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) if (numeros[i - 1] > numeros[i]) return false;
        return true;
    }
}
