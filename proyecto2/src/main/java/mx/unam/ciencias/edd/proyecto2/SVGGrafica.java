package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

/**
 * INCOMPLETA!!!
 */
public class SVGGrafica{

  /** */
  private Grafica<Integer> enteros;

  /** */
  private VerticeGrafica v;

  /** */
  private static String g = "";

  /** */
  private String firstline = "<?xml version = \'1.0\' encoding = \'utf-8\' ?> \n";

  /**
   *
   */
  public SVGGrafica(Lista<Integer> enteros){
    this.enteros = new Grafica<Integer>();
  }

  /**
   *
   */
  public String setLongitud(int width, int height){;
    String sndline = String.format("<svg width=\'%d\' " +
    "height=\'40\'>",width, height);
    return firstline + sndline + "\n <g>";
  }

  /**
   *
   */
  public String dibujaVertice(int cx, int cy){
    String dv = String.format("\n<circle cx=\'%s\' cy=\'%d\' r=\'20\' " +
    "stroke=\'mediumvioletred\' stroke-width=\'2\' fill=\'salmon\' />",cx,cy);
    return dv;
  }

  /**
   *
   */
  public String cierre(){
    return "\n </g> \n</svg>";
  }


}
