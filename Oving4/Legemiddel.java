class Legemiddel implements Utskrivbar{

  //protected variables
  protected String navn;
  protected int id;
  protected double pris;
  protected double virkestoff;
  private static int antall = 0;

  //Constructor
  public Legemiddel(String navn, double pris, double virkestoff){
    this.id = antall;
    antall++;
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }

  //Interface-metoder
  public void skrivUt(){
    System.out.printf("Navn: %s, ID: %d, Pris: %.2f, Virkestoff: %.2fmg.%n", navn, id, pris, virkestoff);
  }

  //Getters
  public String hentNavn(){
    return navn;
  }

  public int hentId(){
    return id;
  }

  public double hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }
}
