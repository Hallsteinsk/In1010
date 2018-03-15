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
}
