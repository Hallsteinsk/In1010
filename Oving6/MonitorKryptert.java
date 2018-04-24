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

  //Constructor
  public MonitorKryptert(String mode){
    this.mode = mode;
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
       while(tom()){ikkeTom.await();}
       if(mode.equals("debug")){System.out.printf("Elementer i meldinger: %d%n", meldinger.size());}
       return meldinger.remove(0);
     }finally{
       laas.unlock();
     }
   }

   //Private hjelpemetoder


   private boolean tom(){
     return meldinger.isEmpty();
   }
}
