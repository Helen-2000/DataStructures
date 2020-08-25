package mx.unam.ciencias.edd.proyecto1;

import java.text.Collator;
import java.util.Comparator;

/**
 *Clase Comparador que implementa a Comparator.
 *Collator nos permite crear una instancia collator para poder
 *comparar nuestras cadenas.
 */
public class Comparador implements Comparator<String> {

  /**
   * Compara las líneas de la lista omitiendo símbolos especiales.
   *
   * Nos permite ordenar por líneas; las líneas vacías se ordenan antes que las
   * no vacías, los espacios son ignorados al ordenar pero preservados al
   * imprimir, y los acentos, eñes y diéresis se toman como vocales.
   */
  @Override public int compare(String linea1, String linea2){
    linea1 = linea1.replaceAll("\\p{P}","");
    linea2 = linea2.replaceAll("\\p{P}","");
    Collator collator = Collator.getInstance();
    collator.setStrength(Collator.FULL_DECOMPOSITION);
    return collator.compare(linea1,linea2);
  }

}
