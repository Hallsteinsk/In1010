class LegemiddelB extends Legemiddel{

  protected int styrke;

  public LegemiddelB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //interface-metode
  @Override
  public void skrivUt(){
    System.out.printf("Navn: %s, ID: %d, Pris: %.2f, Virkestoff: %.2fmg, Styrke: %d.%n", navn, id, pris, virkestoff, styrke);
  }

  //Getters
  public int hentVanedannendeStyrke(){
    return styrke;
  }
}
