//Abstrakt klasse som ikke kan instansieres,
// men kan vaere superklasse til blaa og hvite resepter.
abstract class Resept{

  // protected variabler.
  //(Static int deles av alle resepter og brukes til aa bestemme ID til nye resepter).
  protected static int antall = 0;
  protected int ID;
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient p;
  protected int reit;

  //Constructor
  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    ID = antall;
    antall++;
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.p = p;
    this.reit = reit;
  }


  //Public metoder

  //Metode som bruker resept en gang. Senker reit ved bruk.
  // @return true dersom det er igjen flere bruk av resepten.
  //Returnerer false dersom resept er brukt opp.
  public boolean bruk(){
    if(reit <= 0){
      return false;
    }
    else{
      reit--;
      return true;
    }
  }


  //Getters

  // @return reseptens ID
  public int hentId(){
    return ID;
  }

  // @return reseptens legemiddel
  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  // @return utskrivende lege
  public Lege hentLege(){
    return utskrivendeLege;
  }

  // @return pasientens ID
  public int hentPasientId(){
    return pasientId;
  }

  // @return reit (antal ganger resepten kan brukes.)
  public int hentReit(){
    return reit;
  }


  //Abstrakte metoder (Kun prototyping her. Implementeres i subklasser)

  //Metode for aa sjekke fargen til resepten
  // @return String blaa eller hvit.
  abstract public String farge();

  //Metode som sjekker prisen paa reseptens Legemiddel
  // @return prisen til reseptens Legemiddel
  abstract public double prisAaBetale();
}
