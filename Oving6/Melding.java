class Melding{

  //Private variabler
  private String innhold;
  private int kanalId;
  private int sekvensnummer;



  //constructor
  public Melding(String innhold, int kanalId, int sekvensnummer){
    this.innhold = innhold;
    this.kanalId = kanalId;
    this.sekvensnummer = sekvensnummer;

  }

  //Getters
  public String hentInnhold(){
    return innhold;
  }

  public int hentKanalId(){
    return kanalId;
  }

  public int hentSekvensNummer(){
    return sekvensnummer;
  }
}
