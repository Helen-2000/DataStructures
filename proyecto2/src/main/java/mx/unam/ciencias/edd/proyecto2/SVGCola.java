package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * Clase para dibujar la estructura de datos: Cola
 */
public class SVGCola{

  /** Lista con los elementos a dibujar */
  private Lista<Integer> enteros = new Lista<>();

  /** Primer línea de nuestro código SVG */
  private String firstline = "<?xml version = \'1.0\' encoding = \'utf-8\' ?> \n";

  /**
   * Contructor que recibe la lista con los elementos a dibujar.
   * @param enteros de la estructura que vamos a representar.
   */
  public SVGCola(Lista<Integer> enteros){
    this.enteros = enteros;
  }

  /**
   * Define de qué tamaño será nuestra representación de la estructura.
   * @return la línea que define la longitud en código SVG y "<g>".
   */
  public String setLongitud(){
    int num = enteros.getElementos();
    int width = (num * 39);
    String sndline = String.format("<svg width=\'%d\' height=\'95\'>",width);
    return sndline += "\n <g>";
  }

  /**
   * @return el código SVG para dibujar la figura sobre la que irán
   * los elementos.
   */
  public String dibujaCola(int x){
    String p = String.format("\n<text x=\'%d\' y=\'63\' " +
    "font-family=\'Verdana\' font-size=\'60\' fill=\'olivedrab\'>▰</text>", x);
    return p;
  }

  /**
   * @return el código SVG para el texto que va dentro de la figura de la
   * cola, en este caso son sólo enteros.
   */
  public String texto(int x, int elemento){
    String text = String.format("\n<text x=\'%s\' y=\'52\'" +
    " font-family=\'Verdana\' font-size=\'10\' fill=\'seashell\'>" +
    "%d</text>",x,elemento);
    return text;
  }

  /**
   *  @return el código SVG que dibuja la cola con todos los elementos.
   */
  public String cadenaSVG(){
    enteros = enteros.reversa();
    String cad = firstline + this.setLongitud();
    String svg = "";
    int x = 3;
    for(int e : enteros){
      svg += dibujaCola(x);
      if(e < 10)
        svg += texto(x + 19,e);
      else if(e >= 10 && e < 100)
        svg += texto(x + 16,e);
      else
        svg+= texto(x + 12, e);
      x += 36;
    }
    return cad += svg + "\n </g> \n</svg>";
  }

}
