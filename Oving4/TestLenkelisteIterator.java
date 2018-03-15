import java.util.Iterator;

class TestLenkelisteIterator{

  static Liste<String> liste = new Lenkeliste<>();

  public static void main(String[] args){
    leggTilListeElementer();
    itererGjennomListe();
    testFjerning();
  }

  public static void leggTilListeElementer(){
    liste.leggTil("A");
    liste.leggTil("B");
    liste.leggTil("C");
    liste.leggTil("D");
  }

  public static void itererGjennomListe(){
    Iterator<String> iterator = liste.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next());
    }
  }

  public static void testFjerning(){
    Iterator<String> iterator = liste.iterator();
    while(iterator.hasNext()){
      String next = iterator.next();
      if(next == "C"){
        iterator.remove();
      }
    }
    System.out.println(" ");
    itererGjennomListe();
  }
}
