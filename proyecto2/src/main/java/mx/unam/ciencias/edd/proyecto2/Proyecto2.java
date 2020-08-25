package mx.unam.ciencias.edd.proyecto2;

import java.lang.IllegalArgumentException;

/**
 * Proyecto 2 del curso Estructuras de Datos impartida por
 * el profesor Canek Peláez Valdés en el periodo 2019-2.
 *
 * Clase que genera un código SVG para representar gráficamente las
 * Estructuras de Datos vistas hasta la Práctica 7 del mismo curso antes
 * mencionado.
 *
 * Elaborado por Helen Michelle Salazar Zaragoza.
 */
public class Proyecto2{

  public static void main(String[] args) {
    Entrada app = new Entrada();
    if(args.length == 0){
      System.err.println("Ingrese una estructura " +
      "de datos válida o un archivo de texto");
      System.exit(0);
    }
    app.lectura(args);
  }


}
