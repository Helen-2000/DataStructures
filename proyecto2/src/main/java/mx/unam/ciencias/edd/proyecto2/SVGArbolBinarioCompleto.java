package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase que hereda a SVGArbolBinario para dibujar un ArbolBinarioCompleto.
 */
public class SVGArbolBinarioCompleto extends SVGArbolBinario{

  /** Contiene a los elementos que vamos a dibujar */
  private ArbolBinarioCompleto<Integer> enteros;

  /** Vertice que dibujaremos */
  private VerticeArbolBinario v;

  /**
   * Constructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar, en este caso,
   * solo enteros.
   */
  public SVGArbolBinarioCompleto(Lista<Integer> enteros){
    this.enteros = new ArbolBinarioCompleto<Integer>(enteros);
  }

  /**
   * @return el código SVG correspondiente para dibujar el ArbolBinarioCompleto
   * con los elementos recibidos del usuario.
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
