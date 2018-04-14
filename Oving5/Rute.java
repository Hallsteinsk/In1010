abstract class Rute{
  //protected medlemmer
  protected int kolonne;
  protected int rad;
  protected Liste<Rute> naboer;
  protected char tegn;
  protected String type;

  //Constructor
  public Rute(int rad, int kolonne){
    naboer = new Lenkeliste<Rute>();
    this.rad = rad;
    this.kolonne = kolonne;

  }

  //Metode for aa legge til en ny nabo i listen over naboer.
  // @param Rute rute er den nye ruten som skal legges inn i listen
  public void leggTilNabo(Rute rute){
    naboer.leggTil(rute);
  }

  //Metode som henter ut typen rute. Her kan typene sort, hvit, eller aapning returneres
  // @return String som inneholoder hvilken type rute det er.
  public String type(){
    return type;
  }

  //metode som starter aa finne en utvei. Den kaller gaa() til alle sine naboer.
  //Deretter gaar kallet rekursivt videre gjennom alle hvite ruter helt til det finner
  //en utvei eller en blindvei som ender i kun sorte ruter. Dersom man proever aa finne
  //veien ut fra en sort rute vil man faa beskjed om at det ikke gaar. Man maa
  //starte i en hvit rute.
  // @param Labyrint labyrint er labyrintent som skal mottaa en liste med alle utveier.
  //Det er tenkt at den labyrinten som inneholder ruten starter kallet og faar
  //utveien i sin utveiliste.
  public void finnUtvei(Labyrint labyrint){
    if(kanGaaGjennom()){
      for(Rute naborute: naboer){
        naborute.gaa(this, String.format("(%d, %d)-->", kolonne, rad), labyrint);
      }
    }else{
      labyrint.leggTilUtvei(String.format(
      "Kan ikke starte i rute (%d, %d), da denne er sort. Proev en annen startrute.",
      kolonne, rad));
    }
  }

  //Abstract metoder som kun prototypes her. Implementasjon kommer i underklasser

  //Metode for aa returnere rutens type som tegn
  // @return char, tegnet til ruten
  abstract public char tilTegn();

  //Metode som sjekker om man kan gaa gjennom ruten (om den er svart eller hvit)
  // @return boolen som er true hvis man kan gaa gjennom ruten (den er hvit), eller
  //fasle hvis man ikke kan gaa gjennom (den er sort).
  abstract public boolean kanGaaGjennom();

  //Metode som gaar til naboer. Denne benynttes for aa finne et utvei. Sorte ruters
  //sender ikke kallet videre, mens en aapning stopper aa gaa.
  // @param Rute rute er en referanse til en rute som man ikke skal gaa videre til.
  //Her er det tenkt a sende en referanse til seg selv, slik man ikke gaar bakover.
  // @param String utvei er den forelopige veien ut. utvei inneholder alle tidligere besoekte ruter
  // @Labyrint labyrint er referanse til labyrinten som startet kallet for a finne en utvei.
  //En aapning lenger til hele utveistrenegen i labyrinten sin utveiliste.
  abstract public void gaa(Rute rute, String utvei, Labyrint labyrint);

}
