/*Klassen node er en link i listen. En node inneholder data og referanse til nabo.
Her har jeg valgt og ha private variabler som inneholder data og refereanser.
Disse kan manipuleres med getters og setters.
*/

class Node<T>{
  //Private variabler
  private Node<T> neste;
  private T data;

  //Constructor
  public Node(T data){
    this.data = data;
    neste = null;
  }

  //Public grensesnitt:

  //Public metode for aa hente ut data fra noden
  // @return T dataen til noden
  public T hent(){
    return data;
  }

  //Metode for aa hente ut referansen til neste Node.
  // @return Node som er den neste noden i rekka
  public Node<T> neste(){
    return neste;
  }

  //Metode som skriver ny data til noden
  // @param T data er dataen som skal skrives til noden
  public void settData(T data){
    this.data = data;
  }

  //Metode som gir refernse til den neste noden i listen
  // @param Node neste er den neste noden
  public void settNeste(Node<T> neste){
    this.neste = neste;
  }
}
