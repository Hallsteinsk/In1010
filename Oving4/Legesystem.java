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
    hovedLoekke();
  }

  //metode som initialiserer legesystemet
  public void initialiserProgram(){
    brukerInput = new Scanner(System.in);
    menu = new Menu();
    legeliste = new SortertLenkeliste<Lege>();
    pasientliste = new Lenkeliste<Pasient>();
    legemiddelliste = new Lenkeliste<Legemiddel>();
    reseptliste = new Lenkeliste<Resept>();
  }

  //Hovedloekken i legesystemet.
  // @return int. Returnerer 9 for aa avlustte programmet
  public int hovedLoekke(){
    int alternativ;
    System.out.println(" ");
    while(true){
      menu.printMainMenu();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 1: if(skrivOversikt() == 9){return 0;}else{break;}
        case 2: if(leggTil() == 9){return 0;}else{break;}

        case 3: System.out.println("3");
                break;

        case 4: System.out.println("4");
                break;

        case 5: System.out.println("5");
                break;

        case 9: System.out.println("9");
                return 9;

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
        return alternativ;
      }catch(InputMismatchException e){
        System.out.println("Feil input. Skriv inn et heltall");
        //clears input buffer
        brukerInput.next();
        continue;
      }
    }
  }

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
    String navn, foedselsnummer;
    int alternativ = 1;
    while(alternativ == 1){
      System.out.printf("Skriv inn pasientnavn: ");
      navn = hentBrukerString();
      System.out.printf("Skriv inn foedselsnummer: ");
      foedselsnummer = hentBrukerString();
      leggTilPasient(navn, foedselsnummer);
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

  //Metode som lar brujer velge hvilken type lege som skal legges til.
  // @return int.9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilLege(){
    int alternativ;
    System.out.println(" ");
    while(true){
      menu.printLeggTilLege();
      alternativ = hentBrukerInt();
      switch(alternativ){
        case 0: return 0;
        case 1: if(leggTilVanligLege() == 9){return 9;}else{break;}
        case 2: if(leggTilFastlege() == 9){return 9;}else{break;}
        case 9: return 9;
        default: System.out.println("Du skrev inn et tall som ikker et alternativ. Proev igjen.\n");
      }
    }
  }

  //Metode som henter inn navn til en ny lege.
  // @return int.9 avslutter programmet. Andre tall utenom 1 gaar en meny tilbake
  public int leggTilVanligLege(){
    String navn;
    int alternativ = 1;
    while(alternativ == 1){
      System.out.printf("Skriv inn navnet til legen: ");
      navn = hentBrukerString();
      leggTilVanligLege(navn);
      menu.printLeggTilIgjen();
      alternativ = hentBrukerInt();
    }
    return alternativ;
  }

  //Overlagret metode. Denne legger til en ny Lege i legelisten.
  // @param String navn, er navnet til den ny legen
  public void leggTilPasient(String navn){
    pasientliste.leggTil(new Lege(navn));
  }

}
