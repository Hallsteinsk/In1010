class SortRute extends Rute{

  //Constructor
  public SortRute(int rad, int kolonne){
    super(rad, kolonne);
    type = "sort";
    tegn = '#';
  }

  //Implementasjon av abstract meteod som returnerer tegnes som beskriver rutens type.
  // @return char, tegnet som beskriver en sort rute grafisk.
  public char tilTegn(){
    return tegn;
  }

  public boolean kanGaaGjennom(){
    return false;
  }

  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    assert true;
  }
}
