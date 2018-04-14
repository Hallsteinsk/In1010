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

  //Metode som returnere falsk da man ikke kan gaa gjennom en sort rute.
  // @retunr boolean, som er satt til false da dette er en sort rute.
  public boolean kanGaaGjennom(){
    return false;
  }

  //Implementasjon av den abstrakte medtoden gaa for sorte ruter. Her skal
  //kallet stoppe, derfor gjoer ikke denne metoden noe. Skrev inn assert true,
  //saa har man noe aa stoppe programmet paa hvis man vil debugge. 
  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    assert true;
  }
}
