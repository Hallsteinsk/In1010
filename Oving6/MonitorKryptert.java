/*

Benytter ArrayList som en koe. Telegrafister legger inn bakerst, mens
kryptografer henter ut den foerste meldingen.
Hvis listen med meldinger er tom maa en kryptograf vente til en Telegrafist
legger inn en melding.

*/

//Imports
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

class MonitorKryptert{

  //Private variabler
  private ArrayList<Melding> meldinger;
  private ReentrantLock laas;
  private Condition ikkeTom;
  private String mode;
  private volatile int aktiveTelegrafister;

  //Constructor
  public MonitorKryptert(String mode, final int antallTelegrafister){
    this.mode = mode;
    aktiveTelegrafister = antallTelegrafister;
    meldinger = new ArrayList<Melding>();
    laas = new ReentrantLock();
    ikkeTom = laas.newCondition();
  }

  /** Metode som legger inn en kryptert melding i meldingslisten.
   * Denne benyttes av Telegrafister. Dersom en telegrafist signaliserer at den
   * ikke har flere meldinger reduseres aktiveTelegrafister med en.
   * @param Melding melding er meldingen som telegrfisten legger inn i meldinger
   */
  public void leggTil(Melding melding) throws InterruptedException{
    laas.lock();
    try{
      if(melding.hentInnhold().equals("INGEN FLERE MELDINGER")){aktiveTelegrafister--;}
      meldinger.add(melding);
      ikkeTom.signalAll();
    }finally{
      laas.unlock();
    }
  }

  /** Metode som tar ut en kryptert melding fra meldingslisten.
   * Denne metoden benyttes av Kryptografer. I denne metoden vil en kryptograf
   * stoppe og vente hvis ArrayListen med meldinger er tom. Hvis listen er tom
   * og alle telegrafistene har stoppet sitt arbeid returneres en spessiel melding
   * som ber kryptografen om a stoppe.
   * tomOgIngenAktiveTelegrafister-sjekken gjoeres inne i laasen slik at en traad
   * som faar klarsignal til aa hente ut en melding ikke blir avbrutt av en annen
   * kryptograf. Hadde den vaert utenfor laasen kunne den foerste traaden endt
   * opp med aa hente ut fra en tom liste, og hengt i .await() til evig tid.
   * @return Melding er meldingen som kryptografen tar ut
   */
   public Melding taUt() throws InterruptedException{
     laas.lock();
     try{
       if(tomOgIngenAktiveTelegrafister()){
         return new Melding("STOPP", 0, 0);
       }else{
          while(tom()){ikkeTom.await();}
          return meldinger.remove(0);
       }
     }finally{
       if(mode.equals("debug")){System.out.printf("Elementer i meldinger: %d%n", meldinger.size());}
       laas.unlock();
     }
   }


   //Private hjelpemetoder

   /** Metode som sjekker om ArrayListen med meldinger er tom. Denne eksisterer
   * kun for aa gjoere monitoren mer lesbar.
   * @return boolean, true hvis arraylisten er tom, false hvis ikke.
   */
   private boolean tom(){
     return meldinger.isEmpty();
   }

   /** Denne metoden passer paa at kryptografene ikke laaser seg fast. Dersom
   * alle telegrfistene er ferdige og ArrayListen med meldinger er tom kan en
   * kryptograf-traad henge i det evige hvis den ikke mottar beskjed om aa stoppe.
   * Derfor utfoeres denne metoden som sjekker om nettom listen er tom og alle
   * telegrafister har stoppet.
   * @return boolean, true hvis meldinger er tom, og ingen aktive telegrafister,
   * false hvis ikke.
   */
   private boolean tomOgIngenAktiveTelegrafister(){
     if(mode.equals("debug")){System.out.printf("Aktive telegrafister: %d%n", aktiveTelegrafister);}
     if(tom() && aktiveTelegrafister == 0){
       return true;
     }else{
       return false;
     }
   }
}
