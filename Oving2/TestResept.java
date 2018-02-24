//Imports
import java.util.ArrayList;

class TestResept{

  //Delklarerer tomme private lister som skal inneholde verdier som blir testet
  //Disse blir fylt i begynnelsen av main.
  private static ArrayList<LegemiddelC> legemiddelListe = new ArrayList<LegemiddelC>();
  private static ArrayList<Lege> legeListe = new ArrayList<Lege>();
  private static ArrayList<Integer>  pasientIdListe = new ArrayList<Integer>();
  private static ArrayList<Integer> reitListe = new ArrayList<Integer>();
  private static ArrayList<Resept> reseptListe = new ArrayList<Resept>();

  //MAIN
  public static void main(String[] args){

    //Legger verdier og objekter inne i ArrayListene.
    leggTilLegemidler();
    leggTilLeger();
    leggTilPasientId();
    leggTilReit();

    //Benytter verdiene og objektene i listene over til aa opprette resepter som legges inn i en ArrayList
    leggTilResepter();

    //Siden resept nr 2 er en P-resept endres forventet reit fra 9 til 3.
    //Da faar vi testet om P-resept legger in riktig antall maks reit.
    reitListe.set(1, 3);

    //Loop som tester reseptene.
    int indeks = 0;
    for(Resept resept : reseptListe){
      testResept(resept, indeks);
      indeks++;
    }
  }

  //Testprosedyrer

  //Prosedyre som tester et legemiddel.
  // @param Resept resept er den resepten som skal testResept
  // @param int indeks er indeksen som brukes for aa sjekke verdiene i resepten opp mot verdiene i ArrayListne
  public static void testResept(Resept resept, int indeks){
    //Benytter funksjonene og prosedyrene under til aa teste alle verdiene til en resept.
    System.out.println("Tester resept av typen: " + typeResept(resept));
    testReseptId(resept.hentId(), indeks);
    testLegemiddelNavn(resept.hentLegemiddel().hentNavn(), indeks);
    testLegemiddelPris(resept.hentLegemiddel().hentPris(), indeks);
    testLegemiddelVirkestoff(resept.hentLegemiddel().hentVirkestoff(), indeks);
    testLegeNavn(resept.hentLege().hentNavn(), indeks);
    testPasientId(resept.hentPasientId(), indeks);
    testReit(resept.hentReit(), indeks);

    //Bruker resept en gang, og sjekker om reit er redusert
    if(resept.bruk()){
      System.out.println("Bruker resept");
    }else{
      System.out.println("Noe gikk galt. Fikk ikke brukt resept...");
    }
    reduserReitVerdier(indeks);
    testReit(resept.hentReit() , indeks);


    //Legger inn linjeskift mellom hver test
    System.out.println(" ");
  }

  //Funksjon som returnerer en String som forteller hvilken type resept det er
  // @param Resept resept er resepten som skal sjekkes
  // @return String som inneholder informasjon om hvilken type resept det er
  public static String typeResept(Resept resept){
    if(resept instanceof MillitaerResept){
      return "Millitaer";
    }else if(resept instanceof PResept){
      return "P-resept";
    }else if(resept instanceof HvitResept){
      return "Hvit";
    }else if(resept instanceof BlaaResept){
      return "Blaa";
    }else{
      return "Noe har blitt feil. Resept har ingen type...";
    }
  }

  //Prosedyrer som tester at en resept er riktig
  // @param int faktisk er den faktiske verdien til resept-ID
  // @param int indeks angir hvilken ID-nummer vi kan forvente.
  public static void testReseptId(int faktisk, int indeks){
    int forventet = indeks + 1;
    if(faktisk == forventet){
      System.out.println("Reseptens ID er riktig");
    }else{
      System.out.println(String.format("Reseptens ID er feil. Forventet %d, men fikk %d", forventet, faktisk));
    }
  }

  //Prosedyrer som tester at navnet paa legemiddelet i resepten er riktig
  // @param String faktisk er det faktiske navnet til reseptinstansen
  // @param int indeks angir hvor i legemiddellisten vi skal lete etter fasiten.
  public static void testLegemiddelNavn(String faktisk, int indeks){
    String forventet = legemiddelListe.get(indeks).hentNavn();
    if(faktisk.equals(forventet)){
      System.out.println("Legemiddelnavn riktig!");
    }else{
      System.out.println(String.format("Legemiddelnavn feil. Forventet %s, men fikk %s", forventet, faktisk));
    }
  }

  //Prosedyrer som tester at prisen paa legemiddelet i resepten er riktig
  // @param double faktisk er den faktiske prisen til legemiddelet
  // @param int indeks angir hvor i legemiddellisten vi skal lete etter fasiten.
  public static void testLegemiddelPris(double faktisk, int indeks){
    double forventet = legemiddelListe.get(indeks).hentPris();
    if(faktisk == forventet){
      System.out.println("Legemiddelpris riktig!");
    }else{
      System.out.println(String.format("Legemiddelpris feil. Forventet %.2f, men fikk %.2f", forventet, faktisk));
    }
  }

  //Prosedyrer som tester at mengden virkestoff i legemiddelet til resepten er riktig
  // @param double faktisk er den faktiske mengden virkestoff
  // @param int indeks angir hvor i legemiddellisten vi skal lete etter fasiten.
  public static void testLegemiddelVirkestoff(double faktisk, int indeks){
    double forventet = legemiddelListe.get(indeks).hentVirkestoff();
    if(faktisk == forventet){
      System.out.println("Virkestoff i legemiddel riktig!");
    }else{
      System.out.println(String.format("Virkestoff feil. Forventet %.2f, men fikk %.2f", forventet, faktisk));
    }
  }

  //Prosedyrer som tester at navnet til den utskrivende legen er riktig
  // @param String faktisk er det faktiske navnet til utskrivende lege paa resepten
  // @param int indeks angir hvor i legeListen vi skal lete etter fasiten.
  public static void testLegeNavn(String faktisk, int indeks){
    String forventet = legeListe.get(indeks).hentNavn();
    if(faktisk.equals(forventet)){
      System.out.println("Legenavn er riktig!");
    }else{
      System.out.println(String.format("Legenavn er feil. Forventet %s, men fikk %s", forventet, faktisk));
    }
  }

  //Prosedyre som tester at pasient-ID i resepten er riktig
  // @param int faktisk er den faktiske ID i reseptens
  // @param in indeks bestemmer hvor i pasientIdListe vi finner fasiten
  private static void testPasientId(int faktisk, int indeks){
    int forventet = pasientIdListe.get(indeks);
    if(faktisk == forventet){
      System.out.println("Pasient-ID er riktig");
    }else{
      System.out.println(String.format("Pasient-ID er feil. Forventet %d, men fikk %d", forventet, faktisk));
    }
  }

  //Prosedyre som tester at reseptens reit er riktig
  //Logikk som haandterer at maks antall reit i P-resept ligger i prosedyren testResept
  // @param int faktisk er den faktiske reit til resepten
  // @param in indeks bestemmer hvor i reitListe vi finner fasiten
  private static void testReit(int faktisk, int indeks){
    int forventet = reitListe.get(indeks);
    if(faktisk == forventet){
      System.out.println("Reit er riktig");
    }else{
      System.out.println(String.format("Reit er feil. Forventet %d, men fikk %d", forventet, faktisk));
    }
  }

  //Prosedyre som reduserer alle reit med 1 i reitListe
  // @ param int indeks er indeksen til det den reiten som skal reduseres.
  private static void reduserReitVerdier(int indeks){
    reitListe.set(indeks, reitListe.get(indeks) - 1);
  }



  //Prosedyrer som legger til verdier i ArrayListene.

  //Prosedyre som legger til Legemiddler i legemiddelListe
  public static void leggTilLegemidler(){
    legemiddelListe.add(new LegemiddelC("Valium", 349, 10));
    legemiddelListe.add(new LegemiddelC("Morfin", 900, 50));
    legemiddelListe.add(new LegemiddelC("Zyrtec", 120, 5));
    legemiddelListe.add(new LegemiddelC("Medisin", 200, 100));
  }

  //Prosedyre som legger til Leger i legeListe
  public static void leggTilLeger(){
    legeListe.add(new Lege("Larsen lege"));
    legeListe.add(new Lege("Hansen lege"));
    legeListe.add(new Lege("Svendson lege"));
    legeListe.add(new Lege("Legeson lege"));
  }

  //prosedyre som legger til pasient-ID i pasientIdListe
  public static void leggTilPasientId(){
    pasientIdListe.add(123);
    pasientIdListe.add(456);
    pasientIdListe.add(789);
    pasientIdListe.add(147);
  }

  //Prosedyre som legger til reit i reitListe
  public static void leggTilReit(){
    reitListe.add(5);
    reitListe.add(9);
    reitListe.add(4);
    reitListe.add(5);
  }

  //Prosedyre som legger til resepter i reseptListe. Det blir lagt til en av hver type (subklasse) resept
  //Reseptene henter Legemiddel, Lege, pasient-ID, og reit fra henholdsvis legemiddelListe, legeListe, pasientIdListe, og reitListe
  public static void leggTilResepter(){
    int i = 0;
    reseptListe.add(new MillitaerResept(legemiddelListe.get(i), legeListe.get(i), pasientIdListe.get(i), reitListe.get(i)));
    i++;
    reseptListe.add(new PResept(legemiddelListe.get(i), legeListe.get(i), pasientIdListe.get(i), reitListe.get(i)));
    i++;
    reseptListe.add(new HvitResept(legemiddelListe.get(i), legeListe.get(i), pasientIdListe.get(i), reitListe.get(i)));
    i++;
    reseptListe.add(new BlaaResept(legemiddelListe.get(i), legeListe.get(i), pasientIdListe.get(i), reitListe.get(i)));
  }
}
