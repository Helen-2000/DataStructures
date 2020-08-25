package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

import mx.unam.ciencias.edd.Lista;

/** Clase que define los tipos de lectura para cada Entrada en la consola,
  *
  */
public class Entrada{

  /** Lista de los archivos a ordenar */
  private Lista<String> listaArchivos;

  /** Objeto que nos permite leer*/
  private BufferedReader br;

  /** Comparador para nuestras listas */
  private Comparador compara = new Comparador();

  /**
   *Constructor que recibe una lista con el nombre de los archivos a
   * ordenar.
   *@param listaArchivos que contiene todos los archivos recibidos en la
   *consola.
   */
  public Entrada(Lista<String> listaArchivos){
    this.listaArchivos = listaArchivos;
  }

  /** Método que lee el texto que contiene cada archivo recibido en la
   * consola, crea una nueva lista en donde guarda las líneas de todos los
   * archivos y las ordena utilizando mergeSort.
   *@throws FileNotFoundException si el archivo no existe.
   *@throws IOException si ocurre un error al leer el archivo.
   *@return la lista con las líneas ordenadas alfabéticamente.
   */
  public Lista<String> leeArchivo(){
    Lista<String> listaLineas = new Lista<>();
    for(String archivo : listaArchivos){
      try{
        br = new BufferedReader(new FileReader(archivo));
        String input = br.readLine();
        while(input != null){
          listaLineas.agrega(input);
          input = br.readLine();
        }
        br.close();
      }catch(FileNotFoundException e){
        System.err.println(String.format("No se encontró %s", archivo));
      }catch(IOException ioe) {
        System.err.println(String.format("Error al leer %s", archivo));
      }
    }
    return listaLineas.mergeSort(compara);
  }

  /** Método que lee cada línea recibida en la entrada estándar,
   * crea una nueva lista en donde guarda dichas líneas y las
   * ordena utilizando mergeSort.
   *@throws IOException si ocurre un error al leer la entrada estándar.
   *@return la lista con las líneas ordenadas alfabéticamente.
   */
  public Lista<String> leeEstandar(){
    Lista<String> listaLineas = new Lista<>();
    try{
      br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      while(input != null){
        listaLineas.agrega(input);
        input = br.readLine();
      }
      br.close();
    }catch(IOException ioe) {
      ioe.printStackTrace();
    }
    return listaLineas.mergeSort(compara);
  }


}
