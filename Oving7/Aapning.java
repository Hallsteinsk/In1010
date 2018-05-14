/* En aapning er en hvit rute som ligger paa kanten av en labrint. Derfor blir
den hvite ruten en aapning man kan gaa ut av. Derfor er gaa()-metoden forskjellig.
Kallet vil stoppe her, og hele utveien legges inn i labyrintens utveiliste.
Bortsett ifra dette er en aapning ganske lik en hvit rute.
*/

class Aapning extends HvitRute{

  //Constructor
  public Aapning(int rad, int kolonne){
    super(rad, kolonne);
    type = "aapning";
  }

  //Implementasjon av den abstakte metoden gaa for ruter som er aapning.
  //Her legges denne rutens koordinater inn i utveistrengen foer den lagrer
  //stringen i labyrintens utveiliste. Kallet stopper da vi har naadd maalet.
  // @param Rute rute er en referanse til en rute som man ikke skal gaa videre til.
  // @param String utvei er den forelopige veien ut. utvei inneholder alle tidligere besoekte ruter
  // @Labyrint labyrint er referanse til labyrinten som startet kallet for a finne en utvei.
  @Override
  public void gaa(Rute forrige, String utvei, Labyrint labyrint){
    utvei += String.format("(%d, %d)", kolonne, rad);
    labyrint.leggTilUtvei(utvei);
  }

  //Her overskrives metoden finnUtvei. Dette var en bug jeg oppdaget i oblig7.
  //Dersom man starter i en aappning vil den finne en annen vei ut, enn seg selv.
  //Derfor overskrives metoden her slik at kun denne ruten dukker opp
  // i utveilisten.
  // @param Labyrint labyrint er den labyrinten utvein skal finnes fra, og motta
  // utveilisten.
  @Override
  public void finnUtvei(Labyrint labyrint){
    labyrint.leggTilUtvei(String.format("(%d, %d)", kolonne, rad));
    super.finnUtvei(labyrint);
  }

}
