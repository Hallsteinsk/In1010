import java.util.Iterator;

class TestLege{

  public static void main(String[] args){

    testSortering();
    testReseptlisten();





  }

  public static void testReseptlisten(){
    Lege paus = new Lege("Dr.Paus");
    System.out.println("Skriver ut en tom liste:");
    paus.skrivUtResepter();
    System.out.println("Legger til resepter");
    Pasient pasient = new Pasient("pas", 123);
    Legemiddel legemiddel = new LegemiddelC("Ibux", 30, 400);
    paus.leggTilResept(new Resept())
  }

  public static void testSortering(){
    System.out.println("Tester sortering av legeer i en SortertLenkeliste");
    Liste<Lege> legeliste = new SortertLenkeliste<Lege>();
    legeliste.leggTil(new Lege("A"));
    legeliste.leggTil(new Lege("ABC"));
    legeliste.leggTil(new Lege("AB"));
    legeliste.leggTil(new Lege("ABCDEFG"));
    legeliste.leggTil(new Lege("ABCDE"));
    legeliste.leggTil(new Lege("A"));

    Iterator<Lege>  legeIterator = legeliste.iterator();
    while(legeIterator.hasNext()){
      System.out.println(legeIterator.next().hentNavn());
    }

    System.out.println(" ");
  }
}
