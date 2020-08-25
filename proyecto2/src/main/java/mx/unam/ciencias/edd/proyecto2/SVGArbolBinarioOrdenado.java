package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase que hereda de SVGArbolBinario para dibujar un ArbolBinarioOrdenado.
 */
public class SVGArbolBinarioOrdenado extends SVGArbolBinario{

  /** Contiene a los elementos que vamos a dibujar */
  private ArbolBinarioOrdenado<Integer> enteros;

  /** Vertice que queremos dibujar */
  private VerticeArbolBinario v;

  /**
   * Contructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar.
   */
  public SVGArbolBinarioOrdenado(Lista<Integer> enteros){
    this.enteros = new ArbolBinarioOrdenado<Integer>(enteros);
  }

  /**
   * @return el código SVG correspondiente para dibujar el ArbolBinarioOrdenado
   * con los elementos recibidos del usuario.
   */
  public String cadenaSVG(){
    int width = enteros.getElementos() * 100;
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
