package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase que hereda a SVGArbolBinario para dibujar un MonticuloMinimo.
 */
public class SVGMonticuloMinimo extends SVGArbolBinario{

  /** Contiene a los elementos que vamos a dibujar */
  private ArbolBinarioCompleto<Integer> enteros;

  /** Vertice que queremos dibujar */
  private VerticeArbolBinario v;

 /**
  * Contructor que recibe la lista con los elementos a dibujar.
  * @param enteros de la estructura que vamos a representar.
  */
  public SVGMonticuloMinimo(Lista<Integer> enteros){
      enteros = MonticuloMinimo.heapSort(enteros);
      this.enteros = new ArbolBinarioCompleto<Integer>(enteros);
  }

  /**
   * Método que regresa el código SVG para representar la estructura y sus
   * elementos.
   */
  public String cadenaSVG(){
    int width = enteros.getElementos() * 80;
    int height = enteros.altura() * 80;
    String svg = "";
    svg += setLongitud(width + 10,height);
    v = enteros.raiz();
    dibujaArbol(v, 0, 0, width/2);
    svg += getDibujaArbol();
    svg += cierre();
    return svg;
  }

}
