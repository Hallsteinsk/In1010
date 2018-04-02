class Fastlege extends Lege implements Kommuneavtale{

  //Private variabler
  private int avtaleNummer;

  //Constructor
  public Fastlege(String legeNavn, int avtaleNummer){
    super(legeNavn);
    this.avtaleNummer = avtaleNummer;
  }


  //Interface-metoder

  //Metode som returnerer avtalenummeret til Fastlegen
  // @return int avtalenummeret
  public int hentAvtalenummer(){
    return avtaleNummer;
  }

  //Metode som skriver ut all info om legen
  @Override
  public void skrivUt(){
    System.out.printf("Navn: %s, AvtaleNummer: %d.%n", legeNavn, avtaleNummer);
  }
}
