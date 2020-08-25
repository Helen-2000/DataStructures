package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            this.anterior = null;
            this.siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
          if (this.siguiente == null){
            return false;
          }else{
            return true;
          }
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if(hasNext()){
              this.anterior = this.siguiente;
              this.siguiente = this.siguiente.siguiente;
              return this.anterior.elemento;
            }else{
              throw new NoSuchElementException();
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
          if(this.anterior == null){
            return false;
          }else{
            return true;
          }
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if(!hasPrevious()){
              throw new NoSuchElementException();
            }
            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return this.siguiente.elemento;
          }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
          this.siguiente = cabeza;
          this.anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
          this.anterior = rabo;
          this.siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
      return this.longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return this.longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
      if(cabeza == null){
        return true;
      }else{
        return false;
      }
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
      if(elemento == null){
        throw new IllegalArgumentException();
      }else{
        Nodo n = new Nodo(elemento);
        if(this.cabeza == null){
          this.cabeza = n;
          this.rabo = n;
          longitud++;
        }else{
          agregaFinal(elemento);
        }
      }
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
      if(elemento == null){
        throw new IllegalArgumentException();
      }else{
        Nodo n = new Nodo(elemento);
        longitud++;
        if(cabeza == null){
          this.cabeza = n;
          this.rabo = n;
        }else{
          this.rabo.siguiente = n;
          n.anterior = this.rabo;
          this.rabo = n;
        }
      }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
      if(elemento == null){
        throw new IllegalArgumentException();
      }else{
        Nodo n = new Nodo(elemento);
        longitud++;
        if(cabeza == null){
          cabeza = n;
          rabo = n;
        }else{
          this.cabeza.anterior = n;
          n.siguiente = this.cabeza;
          this.cabeza = n;
        }
      }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al final de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
      if(elemento == null)
        throw new IllegalArgumentException();
      if(i <= 0){
        agregaInicio(elemento);
      }else if(i >= longitud){
        agrega(elemento);
      }else{
        Nodo aux = cabeza;
        for(int j = 0; j < i; j++){
          aux = aux.siguiente;
        }
        Nodo nuevo = new Nodo(elemento);
        aux.anterior.siguiente = nuevo;
        nuevo.siguiente = aux;
        nuevo.anterior = aux.anterior;
        aux.anterior = nuevo;
        longitud++;
      }
    }

    /**
    *Busca un elemento en la lista.
    * @param elemento elemento a buscar.
    */
    public Nodo buscaNodo(T elemento){
      Nodo aux = cabeza;
      while(aux != null){
        if(aux.elemento.equals(elemento)){
          return aux;
        }else{
          aux = aux.siguiente;
        }
      }
      return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
      Nodo n = buscaNodo(elemento);
      if(n == null){
        return;
      }else if(cabeza == n){
        eliminaPrimero();
      }else if(rabo != n){
        n.anterior.siguiente = n.siguiente;
        n.siguiente.anterior = n.anterior;
        longitud--;
      }else{
        eliminaUltimo();
      }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
      if(esVacia()){
        throw new NoSuchElementException();
      }else if(cabeza == rabo){
        T elemento = cabeza.elemento;
        limpia();
        return elemento;
      }else{
        T elemento = cabeza.elemento;
        cabeza = cabeza.siguiente;
        cabeza.anterior = null;
        longitud--;
        return elemento;
      }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
      if(esVacia()){
        throw new NoSuchElementException();
      }else if(cabeza == rabo){
        T elemento = rabo.elemento;
        limpia();
        return elemento;
      }else{
        T elemento = rabo.elemento;
        rabo = rabo.anterior;
        rabo.siguiente = null;
        longitud--;
        return elemento;
      }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
      if(buscaNodo(elemento) == null){
        return false;
      }else{
        return true;
      }
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
      Lista<T> newlist = new Lista<T>();
      Nodo aux = cabeza;
      while(aux != null){
        T elemento = aux.elemento;
        newlist.agregaInicio(elemento);
        aux = aux.siguiente;
      }
      return newlist;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
      Lista<T> newlist = new Lista<T>();
      Nodo aux = cabeza;
      T elemento;
      while(aux != null){
        elemento = aux.elemento;
        newlist.agrega(elemento);
        aux = aux.siguiente;
      }
      return newlist;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
     this.longitud = 0;
     this.cabeza = null;
     this.rabo = null;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
      if(cabeza == null)
        throw new NoSuchElementException();
      return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
      if(cabeza == null)
        throw new NoSuchElementException();
      return rabo.elemento;
    }

    public Nodo iNodo(int i){
      Nodo nodo = this.cabeza;
      for( int j=0; j<i; j++ ){
           nodo = nodo.siguiente;
      }
      return nodo;
    }
    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
      if(i < 0 || i >= this.longitud){
        throw new ExcepcionIndiceInvalido();
      }else{
        Nodo aux = iNodo(i);
        return aux.elemento;
      }
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
      Nodo aux = cabeza;
      int i = 0;
      while(aux != null){
        if (aux.elemento.equals(elemento)) {
          return i;
        }else{
          aux = aux.siguiente;
          i++;
        }
      }
      return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
      if (this.esVacia() )
      return "[]";
      String c = "[";
      Nodo aux = this.cabeza;
      while(aux != this.rabo){
          c+= aux.elemento.toString() + ", ";
          aux= aux.siguiente;
          }
      c+= this.rabo.elemento.toString() + "]";
      return c;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if(this.longitud != lista.getLongitud()){
          return false;
        }else{
          Nodo aux = this.cabeza;
          Nodo auxo = lista.cabeza;
          while(aux != null){
            if(!aux.elemento.equals(auxo.elemento)){
              return false;
            }else{
              aux = aux.siguiente;
              auxo = auxo.siguiente;
            }
          }
          return true;
        }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
