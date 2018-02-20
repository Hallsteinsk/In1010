//Klassen regneklynge holder styr paa rack med noder.
//benytter ArrayList for aa lagre noder.
//Benytter et heltall maksNoder for aa lagre maks antall noder per rack
//Alle metoder er public, og kan benyttes av andre. Metodene leggTilNode, leggTilFlereNoder,
//og leggTilNoderFraLinjeIFil benyttes for aa utvide regneklyngen. Metodene antProsessorer,
//noderMedNokMinne, og hentAntallRack benyttes for aa teste at regneklyngen virker som den skal.

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Regneklynge{

  //Private variables
  private int maksNoder;
  private ArrayList<Rack> rackListe = new ArrayList<>();

  //Constructor
  //Benytter teknikk presentert i notato om IO for aa lese inn fil.
  public Regneklynge(String filnavn){

    Scanner innFil = null;
    try{
      File fil = new File(filnavn);
      innFil = new Scanner(fil);
    } catch(FileNotFoundException e){
      System.out.println("Fant ingen fil med filnavnet: " + filnavn);
    }

    //Leser foerste linje i fil. Der staar det antallet som er maks noder.
    //Dette lagres i maksNoder. Benytter teknikk presentert i notat om IO.
    maksNoder = Integer.parseInt(innFil.nextLine());

    //Leser gjennom resten av filen for aa opprette noder.
    while(innFil.hasNextLine()){
      leggTilNoderFraLinjeIFil(innFil.nextLine());
    }
  }

  //Public methods

  //Metode for aa legge til en node i et ledig rack i regneklyngen.
  //Dersom det ikke er et ledig rack leggges det til et nytt.
  // @param Node nyNode er referanse til noden som skal legges til
  public void leggTilNode(Node nyNode){ //int minne, int prosessorer){
    for(Rack rack : rackListe){
      if(rack.ledigPlass()){
        rack.leggTilNode(nyNode);
        return;
      }
    }
    rackListe.add(new Rack(maksNoder));
    rackListe.get(rackListe.size()-1).leggTilNode(nyNode);
  }

  //Metode som legger til flere noder. Det opprettes nye nodeobjekter Dersom
  //sendes som parameter til metoden leggTilNode().
  // @param int antall er antallet noder som skal legges til
  // @param int minne er minne som alle de nye nodene skal faa
  // @param int prosessorer er antallet prosessorer de nye nodene skal faa
  public void leggTilFlereNoder(int antall, int minne, int prosessorer){
    for(int i=0; i<antall; i++){
      leggTilNode(new Node(minne, prosessorer));
    }
  }

  //Metode som leser en linje, splitter den og lager nye noder basert paa inholdet.
  //Den foerste delen av linjen bestemmer antallet nye noder, neste tall er minne til
  //de  nye nodene, mens det siste er antallet prosessorer. Denne metoden kaller,
  //leggTilFlereNoder() med informasjon fra linjen som parameter.
  // @param String linje er linjen som inneholder informasjon om nodene som skal legges inn.
  public void leggTilNoderFraLinjeIFil(String linje){
    String[] deler = linje.split(" ");
    int antall = Integer.parseInt(deler[0]);
    int minne = Integer.parseInt(deler[1]);
    int prosessorer = Integer.parseInt(deler[2]);
    leggTilFlereNoder(antall, minne, prosessorer);
  }

  //Metode som finner det totale antallet prosessorer i regneklyngen.
  // @return int: summen av alle prosessorer i klyngen
  public int antProsessorer(){
    int sum = 0;
    for(Rack rack: rackListe){
      sum += rack.antProsessorer();
    }
    return sum;
  }

  //Metode som finner antallet noder som har et minimum av getMinne
  // @param int paakrevdMinne er det minimummet av minne som skal vaere i noeden.
  public int noderMedNokMinne(int paakrevdMinne){
    int sum = 0;
    for(Rack rack: rackListe){
      sum += rack.noderMedNokMinne(paakrevdMinne);
    }
    return sum;
  }

  //Metode som henter ut antall rack i regneklyngen.
  // @return int: antallet rack i regneklyngen
  public int hentAntallRack(){
    return rackListe.size();
  }
}
