class Aapning extends HvitRute{

  //Constructor
  public Aapning(int rad, int kolonne){
    super(rad, kolonne);
    type = "aapning";
  }

  @Override
  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    utvei += String.format("(%d, %d)", kolonne, rad);
    labyrint.leggTilUtvei(utvei);
  }
}
