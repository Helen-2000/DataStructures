package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;


/**
 * Clase que verifica que las estructuras recibidas por el usuario
 * sean válidas.
 */
public class EstructuraValida{

  private String estructura;

  /**
   * Constructor que recibe el nombre de la estructura de dato recibida.
   */
  public EstructuraValida(String estructura){
    this.estructura = estructura;
  }

  /**
   * @return true si la estructura recibida es una estructura válida.
   * @return false en otro caso.
   */
  public boolean esValida(){
    return (esArbolAVL() || esArbolBinarioCompleto() ||
      esArbolBinarioOrdenado() || esArbolRojinegro() || esPila() ||
      esCola() || esGrafica() || esLista() || esArreglo() ||
      esMonticuloArreglo() || esMonticuloMinimo());
  }

  /**
   * @return true si la estructura recibida es ArbolAVL.
   */
  public boolean esArbolAVL(){
    return (estructura.equals("ArbolAVL"));
  }

  /**
   * @return true si la estructura recibida es Arreglo.
   */
  public boolean esArreglo(){
    return (estructura.equals("Arreglo"));
  }

  /**
   * @return true si la estructura recibida es ArbolBinarioCompleto.
   */
  public boolean esArbolBinarioCompleto(){
    return (estructura.equals("ArbolBinarioCompleto"));
  }

  /**
   * @return true si la estructura recibida es ArbolBinarioOrdenado.
   */
  public boolean esArbolBinarioOrdenado(){
    return (estructura.equals("ArbolBinarioOrdenado"));
  }

  /**
   * @return true si la estructura recibida es ArbolRojinegro.
   */
  public boolean esArbolRojinegro(){
    return (estructura.equals("ArbolRojinegro"));
  }

  /**
   * @return true si la estructura recibida es Cola.
   */
  public boolean esCola(){
    return (estructura.equals("Cola"));
  }

  /**
   * @return true si la estructura recibida es Grafica.
   */
  public boolean esGrafica(){
    return (estructura.equals("Grafica"));
  }

  /**
   * @return true si la estructura recibida es Lista.
   */
  public boolean esLista(){
    return (estructura.equals("Lista"));
  }

  /**
   * @return true si la estructura recibida es Pila.
   */
  public boolean esPila(){
    return (estructura.equals("Pila"));
  }

  /**
   * @return true si la estructura recibida es MonticuloMinimo.
   */
  public boolean esMonticuloMinimo(){
    return (estructura.equals("MonticuloMinimo"));
  }

  /**
   * @return true si la estructura recibida es MonticuloArreglo.
   */
  public boolean esMonticuloArreglo(){
    return (estructura.equals("MonticuloArreglo"));
  }


}
