class HvitResept extends Resept{

  //Constructor
  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  //Public metoder

  //Metode som returnerer fargen paa reseptens
  // @return String som inneholder fargen "Hvit"
  public String farge(){
    return "Hvit";
  }

  //Metode som finner prisen til reseptens legemiddel
  // @return double prisen til reseptens legemiddel.
  public double prisAaBetale(){
    double pris = legemiddel.hentPris();
    return pris;
  }
}
