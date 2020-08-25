package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase para dibujar con SVG Pilas(Stacks).
 */
public class SVGPila{

  /** Lista que contiene los elementos que vamos a dibujar */
  private Lista<Integer> enteros = new Lista<>();

  /** Primer línea del código SVG */
  private String firstline = "<?xml version = \'1.0\' encoding = \'utf-8\' ?> \n";

  /**
   * Contructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar.
   */
  public SVGPila(Lista<Integer> enteros){
    this.enteros = enteros;
  }

  /**
   * Define de qué tamaño será nuestra representación de la estructura.
   * @return la línea que define la longitud en código SVG y "<g>".
   */
  public String setLongitud(){
    int height = (enteros.getElementos() * 26) - 2;
    String sndline = String.format("<svg width=\'101\' height=\'%d\'>",height);
    return sndline += "\n <g>";
  }

  /**
   * @return la última línea del código SVG.
   */
  public String cierre(){
    return "\n </g> \n</svg>";
  }

  /**
   * @return el código SVG de dibujar un rectángulo de la Pila.
   */
  public String dibujaRectangulo(int y){
    String rect = String.format("\n<rect x=\'3\' y=\'%d\' rx=\'2\' ry =\'2\'" +
     " width=\'95\' height=\'25\' style=\'fill:indianred;" +
     "stroke:mediumseagreen;stroke-width:1;opacity:100\' />",y);
    return rect;
  }

  /**
   * @return el código SVG para el texto que va dentro de la Pila,
   * en este caso son sólo enteros.
   */
  public String texto(int y, int elemento){
    int x = 0;
    if(elemento < 10)
      x = 48;
    else if(elemento >= 100)
      x = 42;
    else
      x = 45;
    String text = String.format("\n<text x=\'%s\' y=\'%s\'" +
    " font-family=\'Verdana\' font-size=\'10\' fill=\'lavender\'>" +
    "%d</text>",x,y,elemento);
    return text;
  }

  /**
   * @return el código SVG que dibuja todo el arreglo.
   */
  public String cadenaSVG(){
    enteros = enteros.reversa();
    String cad = firstline + this.setLongitud();
    String svg = "";
    int y = 5;
    for(int e : enteros){
      svg += dibujaRectangulo(y);
      svg += texto(y + 16, e);
      y += 25;
    }
    return cad += svg + this.cierre();
  }

}
