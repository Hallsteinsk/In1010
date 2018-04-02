class Pasient implements Utskrivbar{
  //Private variables
  private String navn;
  private String fodselsnummer;
  private int id;
  private Stabel<Resept> reseptStabel = new Stabel<>();

  //Statisk variabel som holder orden paa hvor mange paseinter det er totalt. Benyttes for aa gi unik ID
  static int antall = 0;

  //Constructor
  public Pasient(String navn, String fodselsnummer){
    this.navn = navn;
    this.fodselsnummer = fodselsnummer;
    this.id = antall;
    antall++;
  }

  //Interface-metoder
  public void skrivUt(){
    System.out.printf("Navn: %s, Foedselsnummer: %s, ID: %d.%n", navn, fodselsnummer, id);
  }

  //Getters

  public String hentNavn(){
    return navn;
  }

  public int hentId(){
    return id;
  }

  public String hentFodselsnummer(){
    return fodselsnummer;
  }

  public Stabel<Resept> hentReseptStabel(){
    return reseptStabel;
  }

  //Setters

  public void leggTilResept(Resept resept){
    reseptStabel.leggPaa(resept);
  }
}
