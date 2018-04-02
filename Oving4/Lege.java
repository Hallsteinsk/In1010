import java.util.Iterator;

class Lege implements Comparable<Lege>, Utskrivbar{

  //private variabler
  protected String legeNavn;
  protected Lenkeliste<Resept> reseptliste;

  //Constructor
  public Lege(String legeNavn){
    this.legeNavn = legeNavn;
    reseptliste = new Lenkeliste<Resept>();
  }

  //Public metoder:

  //Metode for aa legge  til en ny resept i reseptliste
  // @param Resept resept er resepten som skal legges til i reseptlisten
  public void leggTilResept(Resept resept){
    reseptliste.leggTil(resept);
  }

  //Metode for aa skrive ut alle resepten i reseptlisten
  public void skrivUtResepter(){
    Iterator<Resept> reseptIterator = reseptliste.iterator();
    while(reseptIterator.hasNext()){
      System.out.println(reseptIterator.next().hentLegemiddel().hentNavn());
    }
    System.out.println(" ");
  }

  //Metoder fra interface

  //Metode som samenlikner navnet til lege-objektet med et annet. Den returnerer en signed int
  //som er negativ, null, eller positiv avhengig av om navnet er mindre, lik
  //eller stoerre. Utnytter at String er comparable og ogsaa implementer compareTo.
  // @param Lege annenLege er et objekt som "this" skal sammenliknes med
  // @return int, som er -1, 0, eller 1, avhengig om objektet er mindre, likt eller stoerre
  public int compareTo(Lege annenLege){
    return this.hentNavn().compareTo(annenLege.hentNavn());
  }

  //Metode som skriver ut all info om legen
  public void skrivUt(){
    System.out.printf("Navn: %s.%n", legeNavn);
  }

  //Getters

  // @return String, navnet til legen
  public String hentNavn(){
    return legeNavn;
  }

  // @return Liste<Resept>, listen med resepter, reseptliste
  public Liste<Resept> hentReseptliste(){
    return reseptliste;
  }



}
