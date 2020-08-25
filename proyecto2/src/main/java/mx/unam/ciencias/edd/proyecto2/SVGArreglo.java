package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase que nos permite dibujar arreglos.
 */
public class SVGArreglo{

  /** Lista que contiene los elementos que vamos a dibujar */
  private Lista<Integer> enteros = new Lista<>();

  /** Primer línea del código SVG */
  private String firstline = "<?xml version = \'1.0\' encoding = \'utf-8\' ?> \n";

  /**
   * Contructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar.
   */
  public SVGArreglo(Lista<Integer> enteros){
    this.enteros = enteros;
  }

  /**
   * Define de qué tamaño será nuestra representación de la estructura.
   * @return la línea que define la longitud en código SVG y "<g>".
   */
  public String setLongitud(){
    int num = enteros.getElementos();
    int width = (num * 38);
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
   * @return el código SVG de dibujar un rectángulo para el arreglo.
   */
  public String dibujaRectangulo(int x){
    String rect = String.format("\n<rect x=\'%s\' y=\'8\'" +
     " width=\'35\' height=\'25\'" +
     " style=\'fill:palevioletred;stroke:gold" +
     ";stroke-width:1;opacity:100\' />",x);
    return rect;
  }

  /**
   * @return el código SVG para el texto que va dentro de la casilla del
   * arreglo, en este caso son sólo enteros.
   */
  public String texto(int x, int elemento){
    String text = String.format("\n<text x=\'%s\' y=\'25\'" +
    " font-family=\'Verdana\' font-size=\'10\' fill=\'sandybrown\'>" +
    "%d</text>",x,elemento);
    return text;
  }

  /**
   * @return el código SVG para dibujar la posicion de cada casilla del
   * arreglo.
   */
  public String setPosicion(int x, int posicion){
    String p = String.format("\n<text x=\'%s\' y=\'5\'" +
    " font-family=\'Verdana\' font-size=\'5\' fill=\'darkorange\'>" +
    "%d</text>",x,posicion);
    return p;
  }

  /**
   * @return el código SVG que dibuja todo el arreglo.
   */
  public String cadenaSVG(){
    String cad = firstline + this.setLongitud();
    String svg = "";
    int x = 2;
    int i = 0;
    for(int e : enteros){
      svg += dibujaRectangulo(x);
      svg += setPosicion(x+15,i++);
      if(e < 10)
        svg += texto(x + 14,e);
      else if(e >= 10 && e < 100)
        svg += texto(x + 12,e);
      else
        svg+= texto(x + 8, e);
      x += 35;
    }
    return cad += svg + this.cierre();
  }


}
