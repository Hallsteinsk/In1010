class PResept extends HvitResept{

  //protected variabler
  private double kronerRabbat = 116;



  //Constructor: Tar inn Reit her, men den overskrives av antallReit i superklassens Constructor
  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    //Kaller Constructor til superklasse. Benynner et "Magic number" for a bestemme at reit er lik 3.
    super(legemiddel, utskrivendeLege, p, 3);
  }

  //Public metoder

  //Metode som returnerer prisen legemiddelet koster med denne resepten.
  //Denne metoden skriver over prisAaBetale-metoden til HvitResept.
  // @return doble prisen som skal betales
  @Override
  public double prisAaBetale(){
    double pris = legemiddel.hentPris() - kronerRabbat;
    if(pris < 0){
      return 0;
    }
    else{
      return pris;
    }
  }

}
