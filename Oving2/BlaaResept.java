class BlaaResept extends Resept{

  private double prosentRabatt = 75; //%

  //Constructor
  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  //Public metoder
  public String farge(){
    return "Blaa";
  }

  public double prisAaBetale(){
    double pris = legemiddel.hentPris() * (100 - prosentRabatt)/100;
    return pris;
  }


}
