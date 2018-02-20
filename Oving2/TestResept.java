//Imports
import java.util.ArrayList;

class TestResept{

  private static ArrayList<LegemiddelC> legemiddelListe = new ArrayList<LegemiddelC>();
  private static ArrayList<Lege> legeListe = new ArrayList<Lege>();
  private static ArrayList<Integer>  pasientIdListe = new ArrayList<Integer>();
  private static ArrayList<Integer> reitListe = new ArrayList<Integer>();
  private static ArrayList<Resept> reseptListe = new ArrayList<Resept>();

  public static void main(String[] args){

    //Legger verdier og objekter inne i ArrayListene.
    leggTilLegemidler();
    leggTilLeger();
    leggTilPasientId();
    leggTilReit();
    //Benytter verdiene og objektene i listene over til aa opprette resepter som legges inn i en ArrayList
    leggTilResepter();

    //Loop som tester reseptene.
    int indeks = 0;
    for(Resept resept : reseptListe){
      testResept(resept, indeks);
      indeks++;
    }
  }

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

  public static void testReseptId(int faktisk, int indeks){
    int forventet = indeks + 1;
    if(faktisk == forventet){
      System.out.println("Reseptens ID er riktig");
    }else{
      System.out.println(String.format("Reseptens ID er feil. Forventet %d, men fikk %d", forventet, faktisk));
    }
  }

  public static void testLegemiddelNavn(String faktisk, int indeks){
    String forventet = legemiddelListe.get(indeks).hentNavn();
    if(faktisk.equals(forventet)){
      System.out.println("Legemiddelnavn riktig!");
    }else{
      System.out.println(String.format("Legemiddelnavn feil. Forventet %s, men fikk %s", forventet, faktisk));
    }
  }

  public static void testLegemiddelPris(double faktisk, int indeks){
    double forventet = legemiddelListe.get(indeks).hentPris();
    if(faktisk == forventet){
      System.out.println("Legemiddelpris riktig!");
    }else{
      System.out.println(String.format("Legemiddelpris feil. Forventet %.2f, men fikk %.2f", forventet, faktisk));
    }
  }

  public static void testLegemiddelVirkestoff(double faktisk, int indeks){
    double forventet = legemiddelListe.get(indeks).hentVirkestoff();
    if(faktisk == forventet){
      System.out.println("Virkestoff i legemiddel riktig!");
    }else{
      System.out.println(String.format("Virkestoff feil. Forventet %.2f, men fikk %.2f", forventet, faktisk));
    }
  }

  public static void testLegeNavn(String faktisk, int indeks){
    String forventet = legeListe.get(indeks).hentNavn();
    if(faktisk.equals(forventet)){
      System.out.println("Legenavn er riktig!");
    }else{
      System.out.println(String.format("Legenavn er feil. Forventet %s, men fikk %s", forventet, faktisk));
    }
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

  public static void leggTilPasientId(){
    pasientIdListe.add(123);
    pasientIdListe.add(456);
    pasientIdListe.add(789);
    pasientIdListe.add(147);
  }

  public static void leggTilReit(){
    reitListe.add(5);
    reitListe.add(9);
    reitListe.add(4);
    reitListe.add(5);
  }

  //Prosedyre som legger til resepter i reseptListe. Det blir lagt til en av hver type (subklasse) resept
  //Reseptene henter Legemiddel og Lege fra henholdsvis legemiddelListe og legeListe
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
