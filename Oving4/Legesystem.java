/*System som haandtere lister med leger, pasienter, legemiddel og resepter.
Systemet kan skrive ut alle lister, skrive ut statistikk, bruke resepter, samt
haandtere inleggelse av nye elementer.
*/

import java.util.Scanner;
import java.util.InputMismatchException;

class Legesystem{

  //private lister
  private Liste<Lege> legeliste;
  private Liste<Pasient> pasientliste;
  private Liste<Legemiddel> legemiddelliste;
  private Liste<Resept> reseptliste;

  //private objekter
  private Scanner brukerInput;
  private Menu menu;

  //Constructor
  public Legesystem(){
    initialiserProgram();
    hovedMenu();
  }

  //metode som initialiserer legesystemet
  public void initialiserProgram(){
    //Oppretter objekter av Scanner og Menu
    brukerInput = new Scanner(System.in);
    menu = new Menu();
    //Allokerer plass paa heap for listene
    legeliste = new SortertLenkeliste<Lege>();
    pasientliste = new Lenkeliste<Pasient>();
    legemiddelliste = new Lenkeliste<Legemiddel>();
    reseptliste = new Lenkeliste<Resept>();

    //Legger inn noen "dummy-pasienter", slik at systemet ikke starter tomt.
    pasientliste.leggTil(new Pasient("Anne", "12121212121"));
    pasientliste.leggTil(new Pasient("Johnny", "32323232323"));
    pasientliste.leggTil(new Pasient("Lilly", "45454545454"));
    pasientliste.leggTil(new Pasient("Albert", "67676767676"));

    legeliste.leggTil(new Lege("Dr. Hansen"));
    legeliste.leggTil(new Lege("Dr. Jensen"));
    legeliste.leggTil(new Lege("Dr. Wilson"));
    legeliste.leggTil(new Lege("Dr. Hylle"));

    legemiddelliste.leggTil(new LegemiddelA("Prozac", 40, 200, 4));
    legemiddelliste.leggTil(new LegemiddelB("Ibux", 30, 250, 5));
    legemiddelliste.leggTil(new LegemiddelC("Paracet", 50, 400));
    legemiddelliste.leggTil(new LegemiddelA("Predizol", 450, 75, 8));
    legemiddelliste.leggTil(new LegemiddelB("Paralgin Forte", 65, 400, 5));
    legemiddelliste.leggTil(new LegemiddelC("Placebo", 10, 0));

    reseptliste.leggTil(new BlaaResept(legemiddelliste.hent(0), legeliste.hent(0), pasientliste.hent(0), 3));
    reseptliste.leggTil(new PResept(legemiddelliste.hent(1), legeliste.hent(1), pasientliste.hent(1), 5));
    reseptliste.leggTil(new MillitaerResept(legemiddelliste.hent(2), legeliste.hent(2), pasientliste.hent(2), 2));
    reseptliste.leggTil(new HvitResept(legemiddelliste.hent(3), legeliste.hent(3), pasientliste.hent(3), 3));
    reseptliste.leggTil(new PResept(legemiddelliste.hent(4), legeliste.hent(0), pasientliste.hent(1), 5));
    reseptliste.leggTil(new MillitaerResept(legemiddelliste.hent(5), legeliste.hent(1), pasientliste.hent(2), 2));

  }

  //Hovedmenu i legesystemet.
  // @return int. Returnerer 9 for aa avlustte programmet
  public int hovedMenu(){
    int alternativ;
    System.out.println(" ");
    while(true){
      menu.printMainMenu();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 1: if(skrivOversikt() == 9){return 0;}else{break;}
        case 2: if(leggTil() == 9){return 0;}else{break;}
        case 3: brukResept(); break;
        case 4: skrivStatistikk(); break;
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
      }
    }
  }

  //Metode som leser en int ifra bruker. benytter try/catch for aa fange unntak
  // @return int. Heltallet som bruker skriver inn.
  public int hentBrukerInt(){
    int alternativ;
    while(true){
      try{
        alternativ = brukerInput.nextInt();
        brukerInput.nextLine();
        return alternativ;
      }catch(InputMismatchException e){
        System.out.println("Feil input. Skriv inn et heltall");
        //clears input buffer
        brukerInput.next();
        continue;
      }
    }
  }

  //Metode som leser en String ifra bruker. benytter try/catch for aa fange unntak
  // @return String. Tekststrengen som bruker skriver inn.
  public String hentBrukerString(){
    String tekst;
    while(true){
      try{

        tekst = brukerInput.nextLine();
        return tekst;
      }catch(InputMismatchException e){
        System.out.println("Feil input. Skriv inn en tekststreng.");
        //clears input buffer
        brukerInput.next();
        continue;
      }
    }
  }

  //Metode som leser en double ifra bruker. benytter try/catch for aa fange unntak
  // @return double. Tallet som bruker skriver inn.
  public double hentBrukerDouble(){
  double tall;
    while(true){
      try{
        tall = brukerInput.nextDouble();
        return tall;
      }catch(InputMismatchException e){
        System.out.println("Feil input. Skriv inn et tall");
        //clears input buffer
        brukerInput.next();
        continue;
      }
    }
  }

  //Metode som skrivr ut en oversikt over alle elementer som er lagret i systemet.
  // @return int. Returneres 9 vil programmet slutte. Returneres noe annet
  //gaar man tilbake til hovedmeny.
  public int skrivOversikt(){
    System.out.println("Liste over alle pasientene i systemet:");
    for(Pasient pasient: pasientliste){
      pasient.skrivUt();
    }
    System.out.println(" ");

    System.out.println("Liste over alle legene i systemet:");
    for(Lege lege: legeliste){
      lege.skrivUt();
    }
    System.out.println(" ");

    System.out.println("Liste over alle legemidlene i systemet:");
    for(Legemiddel legemiddel: legemiddelliste){
      legemiddel.skrivUt();
    }
    System.out.println(" ");

    System.out.println("Liste over alle reseptene i systemet:");
    for(Resept resept: reseptliste){
      resept.skrivUt();
    }
    System.out.println(" ");

    System.out.println("Skriv inn 0 for aa returnere til hovedmeny, skriv 9 for aa avslutte programmet.");
    return hentBrukerInt();
  }

  //metode som tar brukeren til en ny meny. Her skal bruker velge hvilken type
  //element som skal legges til(Pasient, Lege, Legemiddel eller Resept)
  // @return int, 9 hvis programmet skal avlsuttes, 0 for aa gaa en meny tilbake
  public int leggTil(){
    int alternativ;
    System.out.println(" ");
    while(true){
      menu.printLeggTil();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 0: return 0;
        case 1: if(leggTilPasient() == 9){return 9;}else{break;}
        case 2: if(leggTilLege() == 9){return 9;}else{break;}
        case 3: if(leggTilLegemiddel() == 9){return 9;}else{break;}
        case 4: if(leggTilResept() == 9){return 9;}else{break;}
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
      }
    }
  }

  //Metode som henter inn navn og foedselsnummer til en ny pasient.
  // @return int.9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilPasient(){
    //Lokale variabler for denne metoden
    String navn, foedselsnummer;
    int alternativ = 1;
    while(alternativ == 1){
      System.out.printf("Skriv inn pasientnavn: ");
      navn = hentBrukerString();
      System.out.printf("Skriv inn foedselsnummer: ");
      foedselsnummer = hentBrukerString();
      leggTilPasient(navn, foedselsnummer);
      //Lar bruker faa mulighet til aa skrive inn enda en pasient, eller gaa ut.
      menu.printLeggTilIgjen();
      alternativ = hentBrukerInt();
    }
    return alternativ;
  }

  //Overlagret metode. Denne legger til en ny pasient i pasientlisten.
  // @param String navn, er navnet til den nye pasienten
  // @param String foedselsnummer er foedselsnummeret til pasienten
  public void leggTilPasient(String navn, String foedselsnummer){
    pasientliste.leggTil(new Pasient(navn, foedselsnummer));
  }

  //Metode som lar bruker velge hvilken type lege som skal legges til.
  // @return int.9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilLege(){
    //Lokale variabler for denne metoden
    int alternativ = 99, avtalenummer = 0;
    String navn, type = null;
    System.out.println(" ");
    //Loekke som gaar helt til brukerk har skrevet inn et "Gyldig tall"
    while(alternativ == 99){
      menu.printLeggTilLege();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 0: return 0;
        case 1: type = "vanlig"; break;
        case 2: type = "fast"; break;
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
                  break;
      }
    }
    //Setter alternativ til 1 slik at innlesningsloekken starter.
    alternativ = 1;
    while(alternativ == 1){
      System.out.printf("Skriv inn navnet til legen: ");
      navn = hentBrukerString();
      if(type == "fast"){
        System.out.printf("Skriv inn fastlegens avtalenummer: ");
        avtalenummer = hentBrukerInt();
      }
      leggTilLege(navn, type, avtalenummer);
      menu.printLeggTilIgjen();
      alternativ = hentBrukerInt();
    }
    return alternativ;
  }

  //Overlagret metode. Denne legger til en ny Lege i legelisten.
  // @param String navn, er navnet til den ny legen
  // @param String type er typen lege som skal legges til (Fast eller "vanlig")
  // @param int avtalenummer er avtalenummeret til fastlegen.
  public void leggTilLege(String navn, String type, int avtalenummer){
    switch(type){
      case "vanlig" : legeliste.leggTil(new Lege(navn));
                      break;

      case "fast" : legeliste.leggTil(new Fastlege(navn, avtalenummer));
                      break;
    }
  }

  //Metode som lar bruker velge hvilken type legemiddel som skal legges til.
  // @return int.9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilLegemiddel(){
    //Lokale variabler for denne metoden
    int alternativ = 99, styrke = 0;
    char type = '0';
    String navn;
    double pris, virkestoff;
    System.out.println(" ");
    //Loekke som gaar helt til brukerk har skrevet inn et "Gyldig tall"
    while(alternativ == 99){
      menu.printLeggTilLegemiddel();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 0: return 0;
        case 1: type = 'a'; break;
        case 2: type = 'b'; break;
        case 3: type = 'c'; break;
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
                  break;
      }
    }
    //Setter alternativ til 1 slik at innlesningsloekken starter.
    alternativ = 1;
    while(alternativ == 1){
      //Leser inn verdier
      System.out.printf("Skriv inn navnet til legemidlet: ");
      navn = hentBrukerString();
      System.out.printf("Skriv inn prisen til legemidlet: ");
      pris = hentBrukerDouble();
      System.out.printf("Skriv inn mengden virkestoff: ");
      virkestoff = hentBrukerDouble();
      if(type == 'a' || type == 'b'){
        System.out.printf("Skriv inn styrken til legemidlet: ");
        styrke = hentBrukerInt();
      }
      //Legger til det nye legemidlet i listen med aa benytte den overlagrede metoden.
      leggTilLegemiddel(navn, type, pris, virkestoff, styrke);
      //Gir bruker alternativet aa slutte eller legge inn et nytt legemiddel av samme type
      menu.printLeggTilIgjen();
      alternativ = hentBrukerInt();
    }
    return alternativ;
  }

  //Overlagret metode som legger til et nytt legemiddel i legemiddellisten
  // @param String navn er navnet til legemidlet
  // @param char type er typen legemiddel (a, b, eller c)
  // @param double pris er prisen til legemiddelet
  // @param double virkestoff er legemidlets virkestoff i mg
  // @param int styrke er styrken til legemidlet
  public void leggTilLegemiddel(String navn, char type, double pris, double virkestoff, int styrke){
    switch(type){
      case 'a': legemiddelliste.leggTil(new LegemiddelA(navn, pris, virkestoff, styrke));
                break;

      case 'b': legemiddelliste.leggTil(new LegemiddelB(navn, pris, virkestoff, styrke));
                break;

      case 'c': legemiddelliste.leggTil(new LegemiddelC(navn, pris, virkestoff));
                break;
    }
  }

  //Metode som lar bruker velge hvilken type resept som skal legges til.
  // @return int. 9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilResept(){
    //Lokale variabler for denne metoden
    int alternativ = 99, pasientId, reit = 0;
    String legemiddelNavn, legeNavn, type = null;
    boolean kanLeggeTil;
    Legemiddel legemiddel;
    Lege lege;
    Pasient pasient;
    System.out.println(" ");
    //Loekke som gaar helt til brukerk har skrevet inn et "Gyldig tall"
    while(alternativ == 99){
      menu.printLeggTilResept();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 0: return 0;
        case 1: type = "hvit"; break;
        case 2: type = "blaa"; break;
        case 3: type = "prevensjon"; break;
        case 4: type = "militaer"; break;
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
                  break;
      }
    }
    //Setter alternativ til 1 slik at innlesningsloekken starter.
    alternativ = 1;
    while(alternativ == 1){
      System.out.printf("Skriv inn navnet til legemidlet: ");
      legemiddelNavn = hentBrukerString();
      System.out.printf("Skriv inn navnet til utskrivende lege: ");
      legeNavn = hentBrukerString();
      System.out.printf("Skriv inn id til pasienten: ");
      pasientId = hentBrukerInt();
      if(type != "prevensjon"){
        System.out.printf("Skriv inn antall reit");
        reit = hentBrukerInt();
      }
      //Sjekker om legemidlet, legen og pasienten finnes. Hvis ikke er det ikke
      //lov aa lage denne resepten.
      legemiddel = finnLegemiddel(legemiddelNavn);
      lege = finnLege(legeNavn);
      pasient = finnPasient(pasientId);
      kanLeggeTil = true;

      //Gir beskjed til bruker at resepten ikke kan lages, og setter kanLeggeTil til false
      if(legemiddel == null){
        System.out.println("Kan ikke opprette resept. Legemiddel finnes ikke i system");
        kanLeggeTil = false;
      }
      if(lege == null){
        System.out.println("Kan ikke opprette resept. Lege finnes ikke i system");
        kanLeggeTil = false;
      }
      if(pasient == null){
        System.out.println("Kan ikke opprette resept. Pasient finnes ikke i system");
        kanLeggeTil = false;
      }

      //Dersom legemidlet, leegen og pasienten eksisterer kan resepten lages
      //med den overlagrede metoden
      if(kanLeggeTil){
        leggTilResept(type, legemiddel, lege, pasient, reit);
      }
      //gir bruker mulighet til aa legge til en ny resept av samme type
      menu.printLeggTilIgjen();
      alternativ = hentBrukerInt();
    }
    return alternativ;
  }


  //Metoder som leter gjennom legemiddellisten etter et legemiddel med et sepsfikt navn
  // @param String navn er navnet til legemidlet som skal finnes
  // @return Legemiddel, er en referanse til legemidlet hvis det finnes. Hvis ikker
  //returneres null.
  Legemiddel finnLegemiddel(String navn){
    for(Legemiddel l : legemiddelliste){
      if(l.hentNavn().equals(navn)){
        return l;
      }
    }
    return null;
  }

  //Metoder som leter gjennom legelisten etter en lege med et sepsfikt navn
  // @param String navn er navnet til legen som skal finnes
  // @return Lege, er en referanse til legen hvis den finnes. Hvis ikke
  //returneres null.
  Lege finnLege(String navn){
    for(Lege l : legeliste){
      if(l.hentNavn().equals(navn)){
        return l;
      }
    }
    return null;
  }

  //Metoder som leter gjennom pasientlisten etter et pasient med en sepsfik ID
  // @param int id er IDen til pasienten som skal finnes
  // @return Pasient, er en referanse til pasienten hvis den finnes. Hvis ikke
  //returneres null.
  Pasient finnPasient(int id){
    for(Pasient p : pasientliste){
      if(p.hentId() == id){
        return p;
      }
    }
    return null;
  }

  //Overlagret metode som legger til en resept.
  // @param String type er typen resept (hvit, blaa, prevensjon, eller militaer)
  // @param Legemiddel legemiddel er en referanse til legemdilet som resepten inneholder
  // @param Lege lege er en referanse til den utskrivende legen
  // @param Pasient pasient er pasienten som skal faa resepten
  // @param int reit er resepens reit.
  public void leggTilResept(String type, Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
    switch(type){
      case "hvit" : reseptliste.leggTil(new HvitResept(legemiddel, lege, pasient, reit));
                    break;

      case "blaa" : reseptliste.leggTil(new BlaaResept(legemiddel, lege, pasient, reit));
                    break;

      case "prevensjon" : reseptliste.leggTil(new PResept(legemiddel, lege, pasient, reit));
                          break;

      case "militaer" : reseptliste.leggTil(new MillitaerResept(legemiddel, lege, pasient, reit));
                        break;
    }
  }

  //Metode som bruker en resept. Her skrives menu og brukergrensesnitt ut i henholde til oppgave D4
  public void brukResept(){
    int pasient, resept, i = 0, j = 0;
    System.out.println("Hvilken pasient vil du se resepter for?");
    //Skriver ut alle pasienter i pasientliste med bruk av ehnhanced for-loop (Iterator)
    for(Pasient p : pasientliste){
      System.out.printf("%d: %s (fnr %s)%n", i, p.hentNavn(), p.hentFodselsnummer());
      i += 1;
    }
    pasient = hentBrukerInt();
    System.out.println(" ");
    System.out.printf("Valgt pasient: %s (fnr %s)%n", pasientliste.hent(pasient).hentNavn(), pasientliste.hent(pasient).hentFodselsnummer());
    System.out.println("Hvilken resept vil du bruke?");
    //Skriver ut den valgte pasientens resepter
    for(Resept r : pasientliste.hent(pasient).hentReseptStabel()){
      System.out.printf("%d: %s (%d reit)%n", j, r.hentLegemiddel().hentNavn(), r.hentReit());
      j += 1;
    }
    resept = hentBrukerInt();
    //bruker resepten, hvis det er reit igjen. Hvis ikke skrives det ut en melding til
    //bruker om at resept er oppbrukt.
    if(pasientliste.hent(pasient).hentReseptStabel().hent(resept).bruk()){
      System.out.printf("Brukte resept paa %s. Antall gjenvaerende reit: %d%n",
      pasientliste.hent(pasient).hentReseptStabel().hent(resept).hentLegemiddel().hentNavn(),
      pasientliste.hent(pasient).hentReseptStabel().hent(resept).hentReit());
    }else{
        System.out.printf("Kunne ikke bruke resept paa %s (ingen gjenvaerende reit).%n", pasientliste.hent(pasient).hentReseptStabel().hent(resept).hentLegemiddel().hentNavn());
    }
  }

  //Metode som skriver ut statisktikk i henhold til oppgave D5.
  public void skrivStatistikk(){
    //Skriver ut totalt antall resepter paa vanedannende resepter
    int antVanedannende = 0;
    for(Resept r : reseptliste){
      if((r.hentLegemiddel() instanceof LegemiddelA) || (r.hentLegemiddel() instanceof LegemiddelB)){
        antVanedannende += 1;
      }
    }
    System.out.printf("Totalt utskrevne resepter paa vanedannende legemidler er: %d%n", antVanedannende);

    //Skriver ut antallet vanedannende resepter til militaere
    int antMilVanedannende = 0;
    for(Resept r : reseptliste){
      if(r instanceof MillitaerResept){
        if((r.hentLegemiddel() instanceof LegemiddelA) || (r.hentLegemiddel() instanceof LegemiddelB)){
          antMilVanedannende += 1;
        }
      }
    }
    System.out.printf("Antall vanedannende resepter skrevet ut til militaere: %d%n", antMilVanedannende);

    //Skriver ut statistikk paa mulig misbruk. Antar her at narkotisk narkotiske legemidler er type A
    //Lister opp leger som har skrevet ut legemiddel av typen A.
    System.out.println("Foelgende leger har skrevet ut resepter paa narkotiske legemidler:");
    for(Lege l : legeliste){
      for(Resept r : l.hentReseptliste()){
        if(r.hentLegemiddel() instanceof LegemiddelA){
          System.out.printf("-%s%n", l.hentNavn());
          break;
        }
      }
    }
    System.out.println(" ");

    //Skriver ut alle pasienter med resept paa narkotiske legemdiler og, antallet resepter
    int antNarko = 0;
    boolean skrivNavn = false;
    System.out.println("Foelgende pasienter har resept paa narkotiske midler:");
    for(Pasient p : pasientliste){
      for(Resept r : p.hentReseptStabel()){
        if(r.hentLegemiddel() instanceof LegemiddelA){
          skrivNavn = true;
          antNarko += 1;
        }
      }
      if(skrivNavn){
        System.out.printf("-%s, %d resepter%n", p.hentNavn(), antNarko);
        skrivNavn = false;
        antNarko = 0;
      }
    }
  }
}
