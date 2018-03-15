class Pasient{
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
}
