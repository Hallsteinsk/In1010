/*
Lenkeliste. Klasse som skal fungere som en liste av noder
som inneholder data av typen T. Denne klassen implementerer
grensesnittet Liste<T>. Maaten denne klassen er bygget paa er
sterkt innspirert av IN1010 slides uke 6.
*/

class Lenkeliste<T> implements Liste<T>{

  //Private variabler
  protected Node<T> start;
  protected int antallNoder;

  //Constructor
  public Lenkeliste(){
    start = new Node(null);
    antallNoder = 0;
  }

  //Public grensesnitt:

  //Metode som returnere stoerrelsen til listen (antall noder)
  // @return int antallet noder i listen
  public int stoerrelse(){
    return antallNoder;
  }

  //metode som legger til en ny node paa en bestemt plass
  // @param int pos er plasseringen til den nye noden
  // @param T x er dataen som skal lagres i den nye noden
  public void leggTil(int pos, T x){
    if(pos > antallNoder || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    //Oppretter en referanse til start
    Node<T> node = start;
    //"Blar"  gjennom listen for aa komme fram til riktig posisjon
    for(int i=0; i<pos; i++){
      node = node.neste();
    }
    //Lager ny node med data x av tyopen T.
    Node<T> nyNode = new Node<T>(x);
    //Fikser referansne til noden foer og etter den nye noden
    nyNode.settNeste(node.neste());
    node.settNeste(nyNode);
    //Oeker antallet noder med 1.
    antallNoder++;
  }

  //Metode som legger til et element bakerst i listen
  // @param T x er element som skal legges til
  public void leggTil(T x){
    //Lager en referanse til starten
    Node<T> node = start;
    //Blar seg gjennom alle noder til den siste.
    while(node.neste() != null){
      node = node.neste();
    }
    //Legger til en node etter den siste
    node.settNeste(new Node<T>(x));
    //Oeaker antallet noder med 1.
    antallNoder++;
  }

  //Metode som endrer dataen til en bestemt node med plassering pos i listen.
  // @param int pos er posisjonen til den nye dataen
  // @param T x er dataen som skal legges til
  public void sett(int pos, T x){
    if(pos + 1 > antallNoder || antallNoder == 0 || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    //Lager referanse til den foerste noden
    Node<T> node = start.neste();
    //"Blar"  gjennom lenken for aa komme til den noden som skal faa ny data
    for(int i=0; i<pos; i++){
      node = node.neste();
    }
    //Skriver ny data til noden
    node.settData(x);
  }

  //Metode som henter dataen som er lagret i node nr pos, men sletter ikke noden.
  // @param int pos er posisjonen i listen man skal hente data fra.
  // @return T dataen til noden
  public T hent(int pos){
    if(antallNoder == 0){
      throw new UgyldigListeIndeks(-1);
    }
    if(pos + 1 > antallNoder || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    //Lager en referanse til den foerste noden
    Node<T> node = start.neste();
    for(int i = 0; i < pos; i++){
      node = node.neste();
    }
    return node.hent();
  }

  //Metode som fjerner en node paa en bestemt plassering og returnerer dataen som sto der.
  // @param int pos er nodens plassering i listen
  // @return T er dataen som er lagret i noden
  public T fjern(int pos){
    //Haandterer ugyldig pos
    if(antallNoder == 0){
      throw new UgyldigListeIndeks(-1);
    }
    if(pos + 1 > antallNoder || pos < 0){
      throw new UgyldigListeIndeks(pos);
    }
    //Lager en referanse til starten av listen
    Node<T> node = start;
    //"Blar" gjennom listen for aa komme til noden foer den som skal slettes
    for(int i=0; i<pos; i++){
      node = node.neste();
    }
    //Lagrer data fra noden i variablen data
    Node<T> nesteNode = node.neste();
    T data = nesteNode.hent();
    //Setter nestereferansen til noden etter den som skal slettes
    node.settNeste(node.neste().neste());
    //reduserer antallet noder med en.
    antallNoder--;
    return data;
  }

  //Metode som fjerner den foerste noden, og retunerer dataen til noden
  // @return T dataen som er lagret i noden
  public T fjern(){
    if(antallNoder == 0){
      throw new UgyldigListeIndeks(-1);
    }
    Node<T> node = start.neste();
    start.settNeste(node.neste());
    antallNoder--;
    return node.hent();
  }
}
