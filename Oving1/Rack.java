//Klassen Rack lagrer holder styr paa noder.
//Noder lagres i en ArrayList. Maks antall noder per rack lagres i heltallet maksNoder.
//Alle metoder er public. Metodene leggTilNode og ledigPlass benyttes naar det skal
//legges til noder. antProsessorer, og noderMedNokMinne benyttes for aa teste om
//racket fungerer som det skal.

import java.util.ArrayList;

class Rack{

  //private variables
  private int maksNoder;
  private ArrayList<Node> nodeListe = new ArrayList<>();

  //Constructor
  public Rack(int maksNoder){
    this.maksNoder = maksNoder;
  }

  //Public metoder

  //Metode som legger til en ny node i racket
  // @param Node nyNode er referanse til noden som skal legges til
  public void leggTilNode(Node nyNode){
    nodeListe.add(nyNode);
  }

  //Metode som sjekker om det er ledig plass i rack
  // @return boolean: sant hvis det er ledig plass i racket
  public boolean ledigPlass(){
    if(nodeListe.size() < maksNoder){
      return true;
    }
    else{
      return false;
    }
  }

  //Metode som finner det totale antallet prosessorer i racket.
  // @return int: summen av alle prosessorer i rack
  public int antProsessorer(){
    int sum = 0;
    for(Node node: nodeListe){
        sum += node.getProsessorer();
    }
    return sum;
  }

  //Metode som finner antallet noder som har et minimum av minne
  // @param int paakrevdMinne er det minimummet av minne som skal vaere i noden.
  // @return int: antallet noder i rack med nok minne
  public int noderMedNokMinne(int paakrevdMinne){
    int sum = 0;
    for(Node node: nodeListe){
      if(node.getMinne() >= paakrevdMinne){
        sum++;
      }
    }
    return sum;
  }
}
