class LegemiddelA extends Legemiddel{

  protected int styrke;

  public LegemiddelA(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //Getters
  public int hentStyrke(){
    return styrke;
  }
}
