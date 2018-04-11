//Imports
import java.util.Hashtable;

abstract class Rute{
  //protected medlemmer
  protected int kolonne;
  protected int rad;
  protected Liste<Rute> naboer;
  protected char tegn;
  protected String type;

  //Constructor
  public Rute(int rad, int kolonne){
    naboer = new Lenkeliste<Rute>();
    this.rad = rad;
    this.kolonne = kolonne;

  }

  //Metode for aa legge til en ny nabo i listen over naboer.
  // @param Rute rute er den nye ruten som skal legges inn i listen
  public void leggTilNabo(Rute rute){
    naboer.leggTil(rute);
  }

  public String type(){
    return type;
  }

  public void finnUtvei(Labyrint labyrint){
    for(Rute naborute: naboer){
      naborute.gaa(this, String.format("(%d, %d)-->", kolonne, rad), labyrint);
    }
  }



  //Abstract metoder som kun prototypes her. Implementasjon kommer i underklasser

  //Metode for aa returnere rutens type som tegn
  // @return char, tegnet til ruten
  abstract public char tilTegn();

  //Metode som sjekker om man kan gaa gjennom ruten (om den er svart eller hvit)
  abstract public boolean kanGaaGjennom();

  //Metode som gaar til naboer. Denne benynttes for aa finne et utvei. Sorte ruters
  //sender ikke kallet videre, mens en aapning stopper aa gaa.
  abstract public void gaa(Rute rute, String utvei, Labyrint labyrint);


}
