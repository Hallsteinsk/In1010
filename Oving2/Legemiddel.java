class Legemiddel{

  protected String navn;
  protected int ID;
  protected double pris;
  protected double virkestoff;
  private static int antall = 0;

  public Legemiddel(String navn, double pris, double virkestoff){
    antall++;
    this.navn = navn;
    this.ID = antall;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }

  //Getters
  public String hentNavn(){
    return navn;
  }

  public int hentId(){
    return ID;
  }

  public double hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }
}
