/*

Behandler rad som x-koordinat, og kolonne som y-koordinat;

*/

//Imports
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

//Implementasjon av klasse
class Labyrint{

  //Private medlemmer
  private int rader;
  private int kolonner;
  private Rute[][] ruteArray;

  //Privat Constructor
  private Labyrint(Rute[][] ruteArray, int rader, int kolonner){
    this.rader = rader;
    this.kolonner = kolonner;
    this.ruteArray = ruteArray;

    //La alle ruter faa vite om sin nabo

  }

  //Public metoder

  //Metode som returnere et "bilde" av labyrinten i String format
  // @return String, er en tekststreng som inneholder labyrinten.
  public String toString(){
    String labyrint = new String();
    for(int kol=0; kol<kolonner; kol++){
      for(int rad=0; rad<rader; rad++){
        labyrint += ruteArray[rad][kol].tilTegn();
      }
      labyrint += '\n';
    }
    return labyrint;
  }

  //Statisk metode som leser en fil og "bygger" labyrinten basert paa innholdet i filen.
  //I foelge oppgavetekst heter dette static factory method. Denne metoden benytter
  //den private Constructor og lager selve labyrinten.
  // @param File fil er filen som skal aapnes. Denne inneholder en labyrint
  // @return Labyrint, er en referanse til labyrinten denne metoden lager.
  public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
    try{
      Scanner sc = new Scanner(fil);

      //Henter den foerste linjene i labyrintfilen, og finner dimensjonene til labyrinten.
      int kolonner = Integer.parseInt(sc.next());
      int rader = Integer.parseInt(sc.next());
      sc.nextLine();

      //Opretter en referanse til et tomt array med dimensjonene til labyrinten
      Rute[][] ruteArray = new Rute[rader][kolonner];
      //Deklarerer et String-buffer
      String buffer;

      //Gaar gjennnom hver linje i labyrintfilen og laster strengen inn i buffer
      for(int kol=0; kol<kolonner; kol++){
        buffer = sc.nextLine();
        //Blar gjennom buffer-strengen og henter ut hver char.
        for(int rad=0; rad<rader; rad++){
          //Oppretter ruteobjekter av riktig type og putter dem inn i ruteArray
          if(buffer.charAt(rad) == '.'){
            ruteArray[rad][kol] = new HvitRute();
              /*if(erAapning(rader, kolonner, rad, kol)){
                ruteArray[rad][kol] = new Aapning();
              }else{
                ruteArray[rad][kol] = new HvitRute();
              }*/
          }else if(buffer.charAt(rad) == '#'){
            ruteArray[rad][kol] = new SortRute();
          }else{
            System.out.println("Feil under lesing av labyrint!");
          }
        }
      }

      //Lager nytt labyrintobjekt og returnerer dette
      Labyrint labyrint = new Labyrint(ruteArray, rader, kolonner);
      return labyrint;

    //Kaster FileNotFoundException videre hvis dette oppstaar.
    }catch(FileNotFoundException e){
      throw e;
    }
  }
}
