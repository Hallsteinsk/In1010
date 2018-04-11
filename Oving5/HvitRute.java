class HvitRute extends Rute{

  //Constructor
  public HvitRute(int rad, int kolonne){
    super(rad, kolonne);
    tegn = '.';
    type = "hvit";
  }

  //Implementasjon av abstracte meteoder
  //Metode som returnerer tegnes som beskriver rutens type.
  // @return char, tegnet som beskriver en hvit rute grafisk.
  public char tilTegn(){
    return tegn;
  }

  public boolean kanGaaGjennom(){
    return true;
  }

  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    if(!utvei.contains(String.format("(%d, %d)", kolonne, rad))){
      utvei += String.format("(%d, %d)-->", kolonne, rad);
      for(Rute naborute: naboer){
        if(naborute != forrige){
          naborute.gaa(this, utvei, labyrint);
        }
      }
    }
  }
}
