class Hovedprogram{

  public static void main(String[] args){

    Regneklynge abel = new Regneklynge("test.txt");

    //Testing,

    System.out.println("Antall noder med minst 32GB minne: " + abel.noderMedNokMinne(32));
    System.out.println("Antall noder med minst 64GB minne: " + abel.noderMedNokMinne(64));
    System.out.println("Antall noder med minst 128GB minne: " + abel.noderMedNokMinne(128));
    System.out.println("Antall noder med minst 256GB minne: " + abel.noderMedNokMinne(256));
    System.out.println("Antall noder med minst 512GB minne: " + abel.noderMedNokMinne(512));
    System.out.println("Antall noder med minst 1024GB minne: " + abel.noderMedNokMinne(1024));
    System.out.println("Antall prosessorer: " + abel.antProsessorer());

    System.out.println("Antall rack: " + abel.hentAntallRack());
  }
}
