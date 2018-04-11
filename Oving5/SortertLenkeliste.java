/* Klasse SortertLenkeliste utvider Lenkeliste, og krever at alle datatyper som
legges inn er sorterbare (Comparable).
*/
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  //Constructor
  public SortertLenkeliste(){
      super();
  }

  //Interface:

  //Metode som legger til et nytt element i listen. Denne metoden legger elementet
  //inn paa riktig plass. Det minste  elementet er foerst, og det stoerste til slutt.
  // @param T x er dataen som skal legges til listen.
  @Override
  public void leggTil(T x){
    //Iterereingsreferanse som starter paa start
    Node<T> node = this.start;
    //Den nye noden
    Node<T> nyNode = new Node<T>(x);
    //legger den nye noden forrerst hvis listen er tom
    if(this.stoerrelse() == 0){
      node.settNeste(nyNode);
    }else{
      //Blar gjennom alle elementer og finner den nye nodens plass
      for(int i=0; i<this.stoerrelse(); i++){
        if(x.compareTo(node.neste().hent()) < 0){
          nyNode.settNeste(node.neste());
          node.settNeste(nyNode);
          break;
        }else{
          node = node.neste();
        }
      }
    }
    //Det nye elementet er stoerre en alle i lista. Den nye noden settes bakerst
    node.settNeste(nyNode);

    //Oeker antallet noder.
    antallNoder++;
  }

  //Metode som fjerner det stoerste (bakerste) elementet i lista og returnerer
  //dataen som sto der. Overskrier fjern() i Lenkeliste.
  // @return T dataen som var lagret i den bakerste noden.
  @Override
  public T fjern(){
    if(antallNoder == 0){
      throw new UgyldigListeIndeks(-1);
    }
    Node<T> node = start.neste();
    for(int i=0; i<this.stoerrelse()-1; i++){
      node = node.neste();
    }
    //reduserer antallet noder, og returnerer dataen i noden som blir slettet.
    antallNoder--;
    return node.hent();
  }

  //Metode som overskriver grensesnittet for aa hindre at man setter inne et
  //element paa et vilkaarlig sted. Dermed forblir listen sortert.
  @Override
  public void sett(int pos, T x){
    throw new UnsupportedOperationException();
  }

  //Metode som overskriver grensesnittet for aa hindre at man setter inne et
  //element paa et vilkaarlig sted. Dermed forblir listen sortert.
  @Override
  public void leggTil(int pos, T x){
    throw new UnsupportedOperationException();
  }

}
