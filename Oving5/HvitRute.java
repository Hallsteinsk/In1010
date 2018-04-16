/* En hvit rute er en rute man kan gaa gjennom. Derfor vil implementasjonen av
de abstakte metodene reflektere dette. Saerlig metoden gaa som rekursivt sender
gaa-kallet videre.
*/

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

  //Metode som returnere sant da man kan gaa gjennom en hvit rute.
  // @retunr boolean, som er satt til true da dette er en hvit rute.
  public boolean kanGaaGjennom(){
    return true;
  }

  //Implementasjonen av den abstrakte metoden gaa for hvite ruter.
  //Metoden kaller gaa rekursivt paa alle sine naboer, med unntak av ruten som
  //sendte kallet. Her har jeg lagt inn en liten if-sjekk foerst som ser etter
  //om denne ruten har blitt besoekt tidligere. Hvis den har dette er vi i en
  //syklus, og kallet stopper. Utveistringen blir ogsaa utvidet med denne rutens
  //koordinater foer stingen sendes videre til naboene.
  // @param Rute rute er en referanse til en rute som man ikke skal gaa videre til.
  // @param String utvei er den forelopige veien ut. utvei inneholder alle tidligere besoekte ruter
  // @Labyrint labyrint er referanse til labyrinten som startet kallet for a finne en utvei.
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
