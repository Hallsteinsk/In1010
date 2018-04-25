/*

Benytter ArrayList som en koe. Telegrafister legger inn bakerst, mens
kryptografer henter ut den foerste meldingen.
Hvis listen med meldinger er tom maa en kryptograf vente til en Telegrafist
legger inn en melding.

*/

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

class MonitorKryptert{

  private ArrayList<Melding> meldinger;
  private ReentrantLock laas;
  private Condition ikkeTom;
  private String mode;
  private volatile int aktiveTelegrafister;

  //Constructor
  public MonitorKryptert(String mode, int antallTelegrafister){
    this.mode = mode;
    aktiveTelegrafister = antallTelegrafister;
    meldinger = new ArrayList<Melding>();
    laas = new ReentrantLock();
    ikkeTom = laas.newCondition();
  }

  /** Metode som legger inn en kryptert melding i meldingslisten.
   * Denne benyttes av Telegrafister
   * @param Melding melding er meldingen som telegrfisten legger inn i meldinger
   */
  public void leggTil(Melding melding) throws InterruptedException{
    laas.lock();
    try{
      if(melding.hentInnhold().equals("INGEN FLERE MELDINGER")){System.out.println("HOY"); aktiveTelegrafister--;}
      //if(mode.equals("debug")){System.out.printf("Aktive telegrafister: %d%n", aktiveTelegrafister);}
      meldinger.add(melding);
      ikkeTom.signalAll();
    }finally{
      laas.unlock();
    }
  }

  /** Metode som tar ut en kryptert melding fra meldingslisten.
   * Denne metoden benyttes av Kryptografer
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


   private boolean tom(){
     return meldinger.isEmpty();
   }

   private boolean tomOgIngenAktiveTelegrafister(){
     if(mode.equals("debug")){System.out.printf("Aktive telegrafister: %d%n", aktiveTelegrafister);}
     if(tom() && aktiveTelegrafister == 0){
       return true;
     }else{
       return false;
     }
   }
}
