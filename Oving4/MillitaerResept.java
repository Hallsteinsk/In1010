class MillitaerResept extends HvitResept{

  //protected variabler
  private double prosentRabatt = 100; //%

  //Constructor
  public MillitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    //Kaller Constructor til superklasse
    super(legemiddel, utskrivendeLege, p, reit);
  }

  //Public metoder

  //Metode som finner prisen til reseptens legemiddel etter at rabatt er trukket fra
  //Denne metoden skriver over prisAaBetale-metoden til HvitResept.
  // @return double prisen til reseptens legemiddel.
  @Override
  public double prisAaBetale(){
    double pris = legemiddel.hentPris() * (100 - prosentRabatt)/100;
    return pris;
  }
}
