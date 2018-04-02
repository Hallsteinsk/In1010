import java.util.Iterator;

class TestLege{

  public static void main(String[] args){

    testSortering();
    testReseptlisten();

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

    for(Lege lege : legeliste){
      System.out.println(lege.hentNavn());
    }

    System.out.println(" ");
  }

  public static void testReseptlisten(){
    Lege paus = new Lege("Dr.Paus");
    System.out.println("Skriver ut en tom liste:");
    paus.skrivUtResepter();

    System.out.println("Legger til resepter");
    Pasient pasient = new Pasient("pas", "123");
    Legemiddel legemiddel = new LegemiddelC("Ibux", 30, 400);
    paus.leggTilResept(new BlaaResept(legemiddel, paus, pasient, 4));
    Pasient pasient2 = new Pasient("pas2", "456");
    Legemiddel legemiddel2 = new LegemiddelC("Paracet", 40, 500);
    paus.leggTilResept(new BlaaResept(legemiddel2, paus, pasient2, 2));
    Pasient pasient3 = new Pasient("pas3", "789");
    Legemiddel legemiddel3 = new LegemiddelC("LM3", 50, 800);
    paus.leggTilResept(new BlaaResept(legemiddel3, paus, pasient3, 4));

    System.out.println("Skriver ut liste med 3 resepter");
    paus.skrivUtResepter();
  }
}
