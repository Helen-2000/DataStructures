package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.NumberFormatException;

import mx.unam.ciencias.edd.*;

/**
 *Clase que define los tipos de lectura para cada Entrada en la consola.
 */
public class Entrada<T> extends Lista<T>{

  /** Nombre de la estructura a dibujar */
  private static String estructura;

  /** Nombre del archivo recibido por el usuario */
  private String archivo;

  /** Lector del archivo */
  private BufferedReader br;

  /** Determina si una estructura es válida o no */
  private EstructuraValida esValid;

  /** Lista que guarda los enteros a representar en la estructura requerida */
  private Lista<Integer> enteros = new Lista<>();

  /**
   * Analiza de qué tipo son los argumentos recibidos por el usuario y
   * determina el modo en que serán leídos los datos.
   * @param args recibidos desde el main.
   */
  public void lectura(String[] args){
    if(args.length < 2){
      archivo = args[0];
      leeArchivo(archivo);
    }else{
      estructura = args[0];
      esValid = new EstructuraValida(estructura);
      if(!esValid.esValida()){
        System.err.println("Ingresa una estructura de " +
        "datos válida.");
        System.exit(1);
      }
      try{
        for(int i = 1; i < args.length; i++){
          int aux = Integer.parseInt(args[i]);
          enteros.agrega(aux);
        }
      }catch(NumberFormatException nfe){
        System.err.println("Favor de ingresar solamente enteros.");
        System.exit(0);
      }
      String s = creaEstructura(estructura);
      System.out.println(s);
    }
  }

  /**
   *Lee el archivo recibido por el usuario, verifica que en la primer línea
   *encontrada esté la estructura de datos válida junto con sus elementos.
   *Si una línea comienza con el símbolo <simb> "#" <simb/> se ignora
   *y lee la siguiente.
   *@param estructura nombre del archivo recibido.
   *@throws FileNotFoundException si no se encuentra el archivo recibido.
   *@throws IOException si ocurre un error al leer el archivo recibido.
   *@throws NoSuchElementException si la estructura recibida en el archivo
   *no es válida.
   */
  public void leeArchivo(String archivo){
    Lista<String> p = new Lista<>();
    try {
      br = new BufferedReader(new FileReader(archivo));
      String input;
      while ((input = br.readLine()) != null) {

          input = transformaCadena(input);

          String[] lineas = input.split(";");

          for (String s : lineas) {
            if(!input.trim().startsWith("#")) {
              p.agrega(s);
            } else {
              input = br.readLine();
            }
          }
          input = br.readLine();
      }
      br.close();
    } catch(FileNotFoundException fne) {
      System.err.println("No se encontró el archivo, por favor ingresa un " +
      "archivo válido.");
      System.exit(0);
    }catch(IOException e){
      System.err.println("No se pudo leer el archivo.");
      System.exit(0);
    }

    estructura = p.eliminaPrimero().trim();
    esValid = new EstructuraValida(estructura);

    if(esValid.esValida()){
      for(String e : p){
        e = e.trim();
        String[] a = e.split(" ");
        for(int i = 0; i < a.length; i++){
          try{
            int aux = Integer.parseInt(a[i]);
            enteros.agrega(aux);
          }catch(NumberFormatException nfe){
            System.err.println("Favor de ingresar solamente enteros.");
            System.exit(0);
          }
        }
      }
      String s = creaEstructura(estructura);
      System.out.println(s);
    }else{
      System.err.println("Ingrese una estructura de datos válida al inicio " +
      "del archivo");
      System.exit(0);
    }
  }

  /**
   * Transforma las palabras de los archivos para quitarles acentos, pasarlas
   * todas a minúsculas, y en cada espacio se agrega un punto y coma para
   * indicar la separación de palaras.
   * @param cad la cadena a transformar.
   * @return la cadena transformada.
   */
  public String transformaCadena(String cad) {
    cad = cad.trim().replace(" ", ";");
    return cad;
  }

  /**
   * Define la estructura que debemos representar en SVG.
   * @param estructura a representar.
   * @return la cadena SVG correspondiente.
   */
  public String creaEstructura(String estructura){
    String es = "";
    switch(estructura){
      case "Lista":
        SVGLista l = new SVGLista(enteros);
        es += l.cadenaSVG();
        break;
      case "Arreglo":
        SVGArreglo a = new SVGArreglo(enteros);
        es += a.cadenaSVG();
        break;
      case "ArbolAVL":
        SVGArbolAVL b = new SVGArbolAVL(enteros);
        es += b.cadenaSVGAVL();
        break;
      case "ArbolBinarioCompleto":
        SVGArbolBinarioCompleto c = new SVGArbolBinarioCompleto(enteros);
        es += c.cadenaSVG();
        break;
      case "ArbolBinarioOrdenado":
        SVGArbolBinarioOrdenado d = new SVGArbolBinarioOrdenado(enteros);
        es += d.cadenaSVG();
        break;
      case "ArbolRojinegro":
        SVGArbolRojinegro e = new SVGArbolRojinegro(enteros);
        es += e.cadenaSVG();
        break;
      case "Cola":
        SVGCola f = new SVGCola(enteros);
        es += f.cadenaSVG();
        break;
      case "Pila":
        SVGPila g = new SVGPila(enteros);
        es += g.cadenaSVG();
        break;
      case "MonticuloArreglo":
        SVGMonticuloArreglo h = new SVGMonticuloArreglo(enteros);
        es += h.cadenaSVG();
        break;
      case "MonticuloMinimo":
        SVGMonticuloMinimo i = new SVGMonticuloMinimo(enteros);
        es += i.cadenaSVG();
        break;
      case "Grafica":
        System.out.println("Sin implementar");
        break;
      default:
        System.err.println("Ingresa una estructura válida.");
        break;
    }
    return es;
  }


}
