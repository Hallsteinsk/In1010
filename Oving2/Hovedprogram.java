/*Program som tester alle klasser og subklasser i oving2. Det opprettes en instans av
alle underklasser. Leger og legemiddel lenkes til resepter. Alle metoder blir testet,
og alle variabler blir skrevet ut til terminalen*/

//Imports
import java.util.ArrayList;

class Hovedprogram{

  private static ArrayList<Legemiddel> legemiddelListe = new ArrayList<Legemiddel>();
  private static ArrayList<Lege> legeListe = new ArrayList<Lege>();
  private static ArrayList<Resept> reseptListe = new ArrayList<Resept>();

  //MAIN
  public static void main(String[] args){

    //Fyller listene med objekter
    fyllLegemiddelListe();
    fyllLegeliste();
    fyllReseptliste();

    //Skriver ut alle variabler til terminalvinduet
    skrivLegemidler();
    skrivLeger();
    //Her blir ogsaa metodene "bruk", og prisAaBetaleListe testet
    skrivResepter();
  }

  //Prosedyrer som fyller lister med objekter:

  //Prosedyre som fyller legemiddelListe med legemidler
  private static void fyllLegemiddelListe(){
    legemiddelListe.add(new LegemiddelA("Valium", 400, 10, 4));
    legemiddelListe.add(new LegemiddelB("Ibux", 200, 200, 22));
    legemiddelListe.add(new LegemiddelC("Zyrtec", 120, 150));
  }

  //Prosedyre som fyller legeListe med leger
  private static void fyllLegeliste(){
    legeListe.add(new Lege("Lege Jensen"));
    legeListe.add(new Fastlege("Lege Andersen", 554));
  }

  //Prosedyre som fyller reseptListe med resepter
  private static void fyllReseptliste(){
    reseptListe.add(new MillitaerResept(legemiddelListe.get(0), legeListe.get(0), 123, 5));
    reseptListe.add(new PResept(legemiddelListe.get(1), legeListe.get(1), 456, 7));
    reseptListe.add(new HvitResept(legemiddelListe.get(2), legeListe.get(0), 789, 4));
    reseptListe.add(new BlaaResept(legemiddelListe.get(0), legeListe.get(1), 111, 2));
  }

  //Prosedyrer som skriver ut all info i leggTilResepter

  //Prosedyre som skriver ut all info om legemidlene i legemiddelListe
  public static void skrivLegemidler(){
    System.out.println("Skriver ut alle legemiddel:");
    for(Legemiddel legemiddel : legemiddelListe){
      System.out.println(String.format("Navn: %s", legemiddel.hentNavn()));
      System.out.println(String.format("ID: %d", legemiddel.hentId()));
      System.out.println(String.format("Pris: %.2f", legemiddel.hentPris()));
      System.out.println(String.format("mg virkestoff: %.2f", legemiddel.hentVirkestoff()));
      if(legemiddel instanceof LegemiddelA){
        LegemiddelA lA = (LegemiddelA)legemiddel;
        System.out.println(String.format("Styrke: %d", lA.hentStyrke()));
      }
      if(legemiddel instanceof LegemiddelB){
        LegemiddelB lB = (LegemiddelB)legemiddel;
        System.out.println(String.format("Styrke: %d", lB.hentVanedannendeStyrke()));
      }
      System.out.println(" ");
    }
  }

  //Prosedyre som skriver ut all info om legene i legeListe
  public static void skrivLeger(){
    System.out.println("Skriver ut alle legene:");
    for(Lege lege : legeListe){
      System.out.println(String.format("Navn: %s", lege.hentNavn()));
      if(lege instanceof Fastlege){
        Fastlege fL = (Fastlege)lege;
        System.out.println(String.format("Styrke: %d", fL.hentAvtalenummer()));
      }
      System.out.println(" ");
    }
  }

  //Prosedyre som skrier ut info om reseptene i reseptListe. Lenken til utskrivende lege
  //og legemiddel blir testet med at navnet til legen og legemiddelet skal skrives ut.
  public static void skrivResepter(){
    System.out.println("Skriver ut alle Reseptene:");
    for(Resept resept : reseptListe){
      System.out.println(String.format("Resept-ID: %d", resept.hentId()));
      System.out.println(String.format("Farge: %s", resept.farge()));
      System.out.println(String.format("Utskrivende lege: %s", resept.hentLege().hentNavn()));
      System.out.println(String.format("Legemiddel: %s", resept.hentLegemiddel().hentNavn()));
      System.out.println(String.format("Pasient-ID: %s", resept.hentPasientId()));
      System.out.println(String.format("Pris aa betale: %.2f", resept.prisAaBetale()));
      brukResept(resept);
      System.out.println(" ");
    }
  }

  //Funksjon som bruker resept en gang.Den skriver ut antall reit foer og etter bruk
  // @param Resept resept er den resepten som skal brukes
  public static void brukResept(Resept resept){
      System.out.println(String.format("Ganger igjen paa resept foer bruk: %d", resept.hentReit()));
      resept.bruk();
      System.out.println("Bruker resept en gang.");
      System.out.println(String.format("Ganger igjen paa resept etter bruk: %d", resept.hentReit()));
  }
}
