class TestLegemiddel{

  public static void main(String[] args){

    //Oppretter instanser av legemiddelene
    LegemiddelA legemiddelA = new LegemiddelA("Ibumetin", 80, 800, 4);
    LegemiddelB legemiddelB = new LegemiddelB("Paracet", 37, 400, 3);
    LegemiddelC legemiddelC = new LegemiddelC("Sannasol", 50, 3);

    //Benytter testprosedyre for aa teste legemiddelobjektene
    System.out.println("Test av legemiddel A");
    testLegemiddel(legemiddelA, 1, "Ibumetin", 80, 800, 4);
    System.out.println(" ");

    System.out.println("Test av legemiddel B");
    testLegemiddel(legemiddelB, 2, "Paracet", 37, 400, 3);
    System.out.println(" ");

    System.out.println("Test av legemiddel C");
    testLegemiddel(legemiddelC, 3, "Sannasol", 50, 3, 0);
    System.out.println(" ");
  }

  //Prosedyre som inneholder enhetstest for objekter av underklassene til legemiddel. Benytter teknikk presentert i slide om enhetstesting.
  //Dersom legemiddel er av type A eller B blir det opprettet en ny referanse som faar typekastet referansen til legemiddel.
  //Den typekastete referansen kan benyttes for aa teste metoder som ikke er inkludert i superklassen legemiddel.
  // @param Legemiddel legemiddel er en referanse til legemiddelobjektet som skal testes
  // @param int fId er forventet ID
  // @param String fNavn er forventet Navn
  // @param double fPris er forventet Pris
  // @param double fVirke er forventet mengde virkestoff
  // @param int fStyrke er forventet styrke. Legemiddel C har 0 i styrke
  public static void testLegemiddel(Legemiddel legemiddel, int fId, String fNavn, double fPris, double fVirke, int fStyrke){
    if(legemiddel.hentId() == fId){System.out.println("ID riktig.");}
      else{System.out.println(String.format("ID feil. Forventet %d fikk: %d", fId, legemiddel.hentId()));}
    if(legemiddel.hentNavn() == fNavn){System.out.println("Navn riktig.");}
      else{System.out.println(String.format("Navn feil. Forventet %s fikk: %s", fNavn, legemiddel.hentNavn()));}
    if(legemiddel.hentPris() == fPris){System.out.println("Pris riktig.");}
      else{System.out.println(String.format("Pris feil. Forventet %.2f fikk: %.2f", fPris, legemiddel.hentPris()));}
    if(legemiddel.hentVirkestoff() == fVirke){System.out.println("Virkestoff riktig.");}
      else{System.out.println(String.format("Virkestoff feil. Forventet %.1f fikk: %.1f", fVirke, legemiddel.hentVirkestoff()));}

    //Opretter ny referansen av typen legemiddelA for aa teste styrke. Benytter teknikk presentert i slides for uke 3.
    if(legemiddel instanceof LegemiddelA){
      LegemiddelA legemiddelA = (LegemiddelA)legemiddel;
      if(legemiddelA.hentStyrke() == fStyrke){System.out.println("Styrke er riktig");}
        else{System.out.println(String.format("Styrke feil. Forventet %d fikk: %d", fStyrke, legemiddelA.hentStyrke()));}
    }

    //Opretter ny referanse av typen LegemiddelB for aa teste Vanedannende styrke. Benytter teknikk presentert i slides for uke 3.
    if(legemiddel instanceof LegemiddelB){
      LegemiddelB legemiddelB = (LegemiddelB)legemiddel;
      if(legemiddelB.hentVanedannendeStyrke() == fStyrke){System.out.println("Vanedannende styrke er riktig");}
        else{System.out.println(String.format("Vanedannende styrke feil. Forventet %d fikk: %d", fStyrke, legemiddelB.hentVanedannendeStyrke()));}
    }
  }
}
