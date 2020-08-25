package mx.unam.ciencias.edd.proyecto1;

/**
 * Proyecto 1 del curso de Estructuras de Datos, impartida por el doctor
 * Canek Peláez Valdés en el periodo 2019-2.
 *
 * Clase que simula el funcionamiento Sort de Unix.
 * Nos permite leer de uno o más archivos y de la entrada estándar, permite
 * ordenar las líneas del texto alfabéticamente e imprimirlas, o bien, tenemos
 * la opción de imprimir en reversa, ya sea en un archivo o en la
 * entrada estándar.
 */

public class Proyecto1{

  public static void main(String[] args) {
    Salida app = new Salida();
    app.imprime(args);
  }
}
