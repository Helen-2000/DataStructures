package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import mx.unam.ciencias.edd.Lista;

/**
  * Clase que va a definir el comportamiento del programa con el usuario.
  * Lee los argumentos para encontrar las banderas en cualquier posición del
  * arreglo (si las hay) y nos permite imprimir en un nuevo archivo,
  * sobreescribir un archivo existente o imprimir en la entrada estándar.
  */
public class Salida{

  /** Nombre del archivo sobre el que se va a escribir cuando se active "-o". */
  private String nuevoArchivo;

  /** Lista que guarda los archivos recibidos en los argumentos. */
  Lista<String> lista = new Lista<>();

  /** Lista para acceder al tipo de lectura de la consola. */
  Entrada listaEntrada = new Entrada(lista);

  /** Lista con los elementos de lista ordenados alfabéticamente. */
  private Lista<String> listaOrdenada;


  /**
   * Método que imprime la lista ordenada alfabéticamente. En caso de que se
   * active alguna bandera lo imprime ya sea en reversa, en un nuevo archivo
   * o simplemente ordenado.
   * @param args
   */
  public void imprime(String[] args){
    boolean r = false;
    boolean o = false;

    for(int i = 0; i < args.length; i++){
        if(args[i].equals("-r")){
          r = true;
        }else if(args[i].equals("-o")){
            o = true;
             if(i+1 < args.length){
                nuevoArchivo = args[i+1];
                i += 1;
            }else{
                System.err.println("La bandera -o requiere un archivo de entrada");
                System.exit(1);
            }
        }else{
            lista.agrega(args[i]);
        }
     }

     if(!lista.esVacia()){
       listaOrdenada = listaEntrada.leeArchivo();
       if(o)
          imprimeArchivo(listaOrdenada, r, nuevoArchivo);
       else if(r)
          imprimeEstandar(listaOrdenada, r);
       else if(!r)
          imprimeEstandar(listaOrdenada, r);
     }else{
       String ordenadas ="\n ************" + "Líneas Ordenadas: "
       + "************";
       listaOrdenada = listaEntrada.leeEstandar();

       if(o){
          imprimeArchivo(listaOrdenada, r, nuevoArchivo);
       }else if(r){
         System.out.println(ordenadas);
          imprimeEstandar(listaOrdenada, r);
       }else{
          System.out.println(ordenadas);
          imprimeEstandar(listaOrdenada, r);
       }
     }
  }

  /**Método auxiliar para imprimir en la entrada estándar.
   *@param listaordenada lista con los elementos ordenados alfabéticamente.
   *@param r booleano que nos dice si se imprimirá en revera o no.
   */
  public void imprimeEstandar(Lista<String> listaOrdenada, boolean r){
    if(r){
      listaOrdenada = listaOrdenada.reversa();
    }
    for(String linea : listaOrdenada){
      System.out.println(linea);
    }
  }

  /**Método auxiliar para imprimir en un nuevo archivo o sobreescribir en uno
   * existente.
   *@param listaordenada lista con los elementos ordenados alfabéticamente
   *@param r booleano que nos dice si se imprimirá en revera o no.
   *@param nuevoArchivo el archivo en que se imprime la lista.
   */
  public void imprimeArchivo(Lista<String> listaOrdenada, boolean r,
  String nuevoArchivo){
    try{
      File nuevo = new File(nuevoArchivo);
      FileWriter fw = new FileWriter(nuevo);
      BufferedWriter bw = new BufferedWriter(fw);
      if(r){
        for(String linea : listaOrdenada.reversa()){
          bw.write(linea);
          bw.newLine();
        }
        bw.close();
        fw.close();
      }else{
        for(String linea : listaOrdenada){
          bw.write(linea);
          bw.newLine();
        }
        bw.close();
        fw.close();
      }
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

}
