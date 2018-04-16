/*
Klasse som inneholder en labyrint. Det vil si et to-dimensjonjalt array med
ruter. I i tillegg har labyrinten en del metoder som gjoer at man kan finne veien
ut av labyrinten og skrive ut et "bilde" av hvordan labyrinten ser ut. Denne klassen
har ogsaa en privat constructor og benytter en static factory metode for aa
konstruere en instans hvor alt er instansiert korrekt.
Behandler kolonne som x-koordinat, og rad som y-koordinat med positiv retning nedover.
Jeg har ogsaa proevd aa gjemme hjelpemetoder med aa gjoere dem private. Tanken
er at disse ikke skal bli tilgjengelig for andre enn denne klassen.
*/

//Imports
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

//Implementasjon av klasse
class Labyrint{

  //Private medlemmer
  private int rader;
  private int kolonner;
  private Rute[][] ruteArray;
  private Liste<String> utveiliste;

  //Privat Constructor
  private Labyrint(Rute[][] ruteArray, int rader, int kolonner){
    this.rader = rader;
    this.kolonner = kolonner;
    this.ruteArray = ruteArray;

    utveiliste = new Lenkeliste<String>();

    //La alle ruter faa vite om sin nabo
    for(int rad=0; rad<rader; rad++){
      for(int kol=0; kol<kolonner; kol++){
        leggTilNaboer(rad, kol);
      }
    }
  }

  //Public metoder

  //Statisk metode som leser en fil og "bygger" labyrinten basert paa innholdet i filen.
  //I foelge oppgavetekst heter dette static factory method. Denne metoden benytter
  //den private Constructor og lager selve labyrinten.
  // @param File fil er filen som skal aapnes. Denne inneholder en labyrint
  // @return Labyrint, er en referanse til labyrinten denne metoden lager.
  public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
    try{
      Scanner sc = new Scanner(fil);

      //Henter den foerste linjene i labyrintfilen, og finner dimensjonene til labyrinten.
      int rader = Integer.parseInt(sc.next());
      int kolonner = Integer.parseInt(sc.next());
      sc.nextLine();

      //Opretter en referanse til et tomt array med dimensjonene til labyrinten
      Rute[][] ruteArray = new Rute[rader][kolonner];
      //Deklarerer et String-buffer
      String buffer;

      //Gaar gjennnom hver linje i labyrintfilen og laster strengen inn i buffer
      for(int rad=0; rad<rader; rad++){
        buffer = sc.nextLine();
        //Blar gjennom buffer-strengen og henter ut hver char.
        for(int kol=0; kol<kolonner; kol++){
          //Oppretter ruteobjekter av riktig type og putter dem inn i ruteArray
          if(buffer.charAt(kol) == '.'){
            if(erAapning(rader, kolonner, rad, kol)){
              ruteArray[rad][kol] = new Aapning(rad, kol);
            }else{
              ruteArray[rad][kol] = new HvitRute(rad, kol);
            }
          }else if(buffer.charAt(kol) == '#'){
            ruteArray[rad][kol] = new SortRute(rad, kol);
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

  //Metode som returnere et "bilde" av labyrinten i String format
  // @return String, er en tekststreng som inneholder labyrinten.
  public String toString(){
    //Lager en tom streng til aa holde bildet.
    String labyrint = new String();

    //Benytter enhanced for loop til aa iterere gjennom ruteArray og lagre
    //alle ruters tegn inn i labyrint-strengen.
    for(Rute[] rad: ruteArray){
      for(Rute kol : rad){
        labyrint += kol.tilTegn();
      }
      //legger inn linjeksift etter hver rad
      labyrint += '\n';
    }

    //returnerer det ferdige bildet i stirng format
    return labyrint;
  }

  //Metode som finner veien ut fra labyrinten fra en spesifikk startrute
  //Her har jeg bestetm at man kun faar lov til aa starte i en hvit rute. Dersom
  //man proever aa starte i en sort rute vil returlisten kun inneholde en String
  //som varsler om at man maa proeve igjen.
  // @param int kolonne er startrutens kolonne (eller x-koordinat)
  // @param int rad er startrutens rad (eller y-koordinat)
  // @return Liste<String> er en liste som inneholder alle utveine fra startruten
  //eller en beskjed om at man ikke kan starte fra en sort rute.
  public Liste<String> finnUtveiFra(int kolonne, int rad){
    //Soerger for at man begynner aa lese inn utveier i en helt tom liste.
    utveiliste.toem();

    //Kaller metoden for aa finne utvei i ruteklassen med denne labyrinten som referanse
    ruteArray[rad][kolonne].finnUtvei(this);

    //Sorterer listen etter lengden på utveiene inn
    sorterUtveiliste();

    return utveiliste;
  }

  //Metode som en aapning benytter for aa legge til utveistrengen til utveilisten
  // @param String utvei er utveien som skal legges til
  public void leggTilUtvei(String utvei){
    utveiliste.leggTil(utvei);
  }


  //Private hjelpemetoder

  //Metode som sjekker om en hvit rute med gitte koordinater er en aapning eller ikke
  //Denne er statisk da den blir kalt av Static Factory metoden.
  // @param int rader er maks antall rader i labyrinten
  // @param int kolonner er maks antall kolionner i labyrinten
  // @param int rad er den aktuelle raden den hvite ruten ligger paa
  // @param int kol er den aktuelle kolonnen den hvite ruten ligger paa
  // @return boolean, true hvis ruten er en aapneing, falske hvis ikke
  private static boolean erAapning(int rader, int kolonner, int rad, int kol){
    if(rad == 0 || rad == rader -1){
      return true;
    }else if(kol == 0 || kol == kolonner -1){
      return true;
    }else{
      return false;
    }
  }

  //Metode som legger til referanse til sine naboer i alle retninger den akutelle ruten har naboer.
  //Metoden sjekker om ruten i et hjoerne eller paa en kant, og legger til naboer deretter.
  //@param int rad er raden til den ruten som skal faa referanser til sine naboer
  //@param int kol er kolonnen til den ruten som skal faa referanser til sine naboer
  private void leggTilNaboer(int rad, int kol){
    if(rad>0){
      ruteArray[rad][kol].leggTilNabo(ruteArray[rad-1][kol]);
    }
    if(rad<rader-1){
      ruteArray[rad][kol].leggTilNabo(ruteArray[rad+1][kol]);
    }
    if(kol>0){
      ruteArray[rad][kol].leggTilNabo(ruteArray[rad][kol-1]);
    }
    if(kol<kolonner-1){
      ruteArray[rad][kol].leggTilNabo(ruteArray[rad][kol+1]);
    }
  }

  //Metode som sorterer utveilisten. her sorteres utveine etter lengden på Stringen.
  //Benytter en enkel bublesort, da listene er forholdsvis "smaa".
  private void sorterUtveiliste(){
    for(int i=0; i<utveiliste.stoerrelse()-1; i++){
      for(int j=0; j<utveiliste.stoerrelse()-1-i; j++){
        if(utveiliste.hent(j).length() > utveiliste.hent(j+1).length()){
          byttUtveilisteIndex(j, j+1);
        }
      }
    }
  }

  //Metode som bytter verdien til to noder i utveilsten. Dette er en enkel swap
  //som blir benyttet i sorteringsalgoritmen "sorterUtveiliste()"
  // @param int a er indeksen til det foerste elementet som skal swappes
  // @param int b er indeksen til det andre elementet som skal swappes
  private void byttUtveilisteIndex(int a, int b){
    String tmp = utveiliste.hent(a);
    utveiliste.sett(a, utveiliste.hent(b));
    utveiliste.sett(b, tmp);
  }
}
