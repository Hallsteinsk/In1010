class Aapning extends HvitRute{

  //Constructor
  public Aapning(int rad, int kolonne){
    super(rad, kolonne);
    type = "aapning";
  }

  //Implementasjon av den abstakte metoden gaa for ruter som er aapning.
  //Her legges denne rutens koordinater inn i utveistrengen foer den lagrer
  //stringen i labyrintens utveiliste. Kallet stopper da vi har naadd maalet.
  @Override
  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    utvei += String.format("(%d, %d)", kolonne, rad);
    labyrint.leggTilUtvei(utvei);
  }
}
