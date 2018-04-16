/*En sort rute er labyrintens "vegg". Denne kan man ikke gaa gjennnom og
implementasjonen av de abstakte klassene reflekterer dette.
*/

class SortRute extends Rute{

  //Constructor
  public SortRute(int rad, int kolonne){
    super(rad, kolonne);
    type = "sort";
    tegn = '#';
  }

  //Implementasjon av abstakte meteoder

  //Metode som returnerer tegnes som beskriver rutens type.
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
  // @param Rute rute er en referanse til en rute som man ikke skal gaa videre til.
  // @param String utvei er den forelopige veien ut. utvei inneholder alle tidligere besoekte ruter
  // @Labyrint labyrint er referanse til labyrinten som startet kallet for a finne en utvei.
  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    assert true;
  }
}
