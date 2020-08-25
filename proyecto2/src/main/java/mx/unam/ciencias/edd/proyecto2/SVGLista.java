package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para dibujar la estructura Lista.
 */
public class SVGLista{

  /** Lista que contiene los elementos que vamos a dibujar */
  private Lista<Integer> enteros = new Lista<>();

  /** Primer línea del código SVG */
  private String firstline = "<?xml version = \'1.0\' encoding = \'utf-8\' ?> \n";

  /**
   * Contructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar.
   */
  public SVGLista(Lista<Integer> enteros){
    this.enteros = enteros;
  }

  /**
   * Define de qué tamaño será nuestra representación de la estructura.
   * @return la línea que define la longitud en código SVG y "<g>".
   */
  public String setLongitud(){
    int num = enteros.getElementos();
    int width = (num * 36) + (num * 7) - 2;
    String sndline = String.format("<svg width=\'%d\' height=\'40\'>",width);
    return sndline += "\n <g>";
  }

  /**
   * @return la última línea del código SVG.
   */
  public String cierre(){
    return "\n </g> \n</svg>";
  }

  /**
   * @return el código SVG de dibujar un rectángulo para la Lista.
   */
  public String dibujaRectangulo(int x){
    String rect = String.format("\n<rect x=\'%s\' y=\'8\' rx=\'5\' ry =\'5\'" +
     " width=\'35\' height=\'25\'" +
     " style=\'fill:pink;stroke:yellow;stroke-width:1;opacity:100\' />",x);
    return rect;
  }

  /**
   * @return el código SVG para el texto que va dentro del rectángulo de la
   * Lista, en este caso son sólo enteros.
   */
  public String texto(int x, int elemento){
    String text = String.format("\n<text x=\'%s\' y=\'25\'" +
    " font-family=\'Verdana\' font-size=\'10\' fill=\'brown\'>" +
    "%d</text>",x,elemento);
    return text;
  }

  /**
   * @param x posicion x donde empezarán las flechas.
   * @return el código SVG que dibuja un flecha de anterior y otra de siguiente,
   * puesto que nuestras listas son doblemente ligadas.
   */
  public String dibujaFlechas(int x){
    String r = String.format("\n<text x=\'%d\' y=\'21\' " +
    "font-family=\'Verdana\' font-size=\'10\' fill=\'brown\'>→</text>", x);
    String l = String.format("\n<text x=\'%d\' y=\'27\' " +
    "font-family=\'Verdana\' font-size=\'10\' fill=\'brown\'>←</text>", x);
    return r + l;
  }

  /**
   * @return el código SVG que dibuja la Lista con todos los elementos.
   */
  public String cadenaSVG(){
    String cad = firstline + this.setLongitud();
    String svg = "";
    int x = 2;
    int w;
    for(int e : enteros){
      svg += dibujaRectangulo(x);
      if(e < 10)
        svg += texto(x + 14,e);
      else if(e >= 10 && e < 100)
        svg += texto(x + 12,e);
      else
        svg+= texto(x + 8, e);
      x += 35 + 8;
      w = x - 8;
      svg += dibujaFlechas(w);
    }
    return cad += svg + this.cierre();
  }

}
