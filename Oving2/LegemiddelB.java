class LegemiddelB extends Legemiddel{

  protected int styrke;

  public LegemiddelB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //Getters
  public int hentVanedannendeStyrke(){
    return styrke;
  }
}
