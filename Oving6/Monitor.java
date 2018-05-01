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

class Monitor{

  //Private variabler
  private ArrayList<Melding> krypterteMeldinger;
  private ArrayList<Melding> dekrypterteMeldinger;
  private ReentrantLock laas;
  private Condition kryptertIkkeTom;
  private Condition dekryptertIkkeTom;
  private String mode;
  private volatile int aktiveTelegrafister;
  private volatile int aktiveKryptografer;

  //Constructor
  public Monitor(String mode, final int antallTelegrafister, final int antallKryptografer){
    this.mode = mode;
    aktiveTelegrafister = antallTelegrafister;
    aktiveKryptografer = antallKryptografer;
    krypterteMeldinger = new ArrayList<Melding>();
    dekrypterteMeldinger = new ArrayList<Melding>();
    laas = new ReentrantLock();
    kryptertIkkeTom = laas.newCondition();
    dekryptertIkkeTom = laas.newCondition();
  }

  /** Metode som legger inn en kryptert melding i meldingslisten.
   * Denne benyttes av Telegrafister. Dersom en telegrafist signaliserer at den
   * ikke har flere meldinger reduseres aktiveTelegrafister med en.
   * @param Melding melding er meldingen som telegrfisten legger inn i meldinger
   */
  public void leggTilKryptert(Melding melding) throws InterruptedException{
    laas.lock();
    try{
      if(melding.hentInnhold().equals("INGEN FLERE MELDINGER")){aktiveTelegrafister--;}
      krypterteMeldinger.add(melding);
      kryptertIkkeTom.signal();
    }finally{
      laas.unlock();
    }
  }

  /** Metode som legger inn en dekryptert melding i ArrayListen med dekrypterte
  * meldinger.
  * @param Melding melding, er den dekrypterte meldingen som skal legges til.
  */
  public void leggTilDekryptert(Melding melding) throws InterruptedException{
    laas.lock();
    try{
      dekrypterteMeldinger.add(melding);
      dekryptertIkkeTom.signal();
    }finally{
      laas.unlock();
    }
  }

  /** Metode som tar ut en kryptert melding fra listen med krypterte meldinger.
   * Denne metoden benyttes av Kryptografer. I denne metoden vil en kryptograf
   * stoppe og vente hvis ArrayListen med kryppterte meldinger er tom. Hvis listen er tom
   * og alle telegrafistene har stoppet sitt arbeid returneres en spessiel melding
   * som ber kryptografen om a stoppe.
   * tomOgIngenAktiveTelegrafister-sjekken gjoeres inne i laasen slik at en traad
   * som faar klarsignal til aa hente ut en melding ikke blir avbrutt av en annen
   * kryptograf. Hadde den vaert utenfor laasen kunne den foerste traaden endt
   * opp med aa hente ut fra en tom liste, og hengt i .await() til evig tid.
   * @return Melding er meldingen som kryptografen tar ut
   */
   public Melding taUtKryptert() throws InterruptedException{
     laas.lock();
     try{
       if(tomOgIngenAktiveTelegrafister()){
         kryptertIkkeTom.signal();
         aktiveKryptografer--;
         return new Melding("STOPP", 0, 0);
       }else{
          while(kryptertTom()){
            kryptertIkkeTom.await();
            if(tomOgIngenAktiveTelegrafister()){
              aktiveKryptografer--;
              return new Melding("STOPP", 0, 0);
            }
          }
          return krypterteMeldinger.remove(0);
          }
     }finally{
       if(mode.equals("debug")){System.out.printf("Elementer i krypterteMeldinger: %d%n", krypterteMeldinger.size());}
       laas.unlock();
     }
   }

   /** Metode som tar ut en dekryptert melding fra listen med dekrypterte meldinger.
   * Denne virker i prinsippet paa samme maate som taUtKryptert(), men denne
   * gangen er det en operasjonsleder som skal hente ut en dekryptert melding.
   * @return Melding, en dekryptert melding.
   */
   public Melding taUtDekryptert() throws InterruptedException{
     laas.lock();
     try{
       if(tomOgIngenAktiveKryptografer()){
         dekryptertIkkeTom.signal();
         return new Melding("STOPP", 0, 0);
       }else{
         while(dekryptertTom()){
           dekryptertIkkeTom.await();
           if(tomOgIngenAktiveKryptografer()){
             return new Melding("STOPP", 0, 0);
           }
         }
         return dekrypterteMeldinger.remove(0);
       }
     }finally{
       if(mode.equals("debug")){System.out.printf("Elementer i dekrypterteMeldinger: %d%n", dekrypterteMeldinger.size());}
       laas.unlock();
     }
   }


   //Private hjelpemetoder

   /** Metode som sjekker om ArrayListen med dekrypterte meldinger er tom.
   * Denne eksisterer kun for aa gjoere monitoren mer lesbar.
   * @return boolean, true hvis arraylisten er tom, false hvis ikke.
   */
   private boolean kryptertTom(){
     return krypterteMeldinger.isEmpty();
   }

   /** Metode som sjekker om ArrayListen med dekrypterte meldinger er tom.
   * @return boolean, true hvis arraylisten er tom, false hvis ikke.
   */
   private boolean dekryptertTom(){
     return dekrypterteMeldinger.isEmpty();
   }

   /** Denne metoden passer paa at kryptografene ikke laaser seg fast. Dersom
   * alle telegrfistene er ferdige og ArrayListen med meldinger er tom kan en
   * kryptograf-traad henge i det evige hvis den ikke mottar beskjed om aa stoppe.
   * Derfor utfoeres denne metoden som sjekker om nettom listen er tom og alle
   * telegrafister har stoppet.
   * @return boolean, true hvis krypterteMeldinger er tom, og ingen aktive telegrafister,
   * false hvis ikke.
   */
   private boolean tomOgIngenAktiveTelegrafister(){
     if(mode.equals("debug")){System.out.printf("Aktive telegrafister: %d%n", aktiveTelegrafister);}
     if(kryptertTom() && aktiveTelegrafister == 0){
       return true;
     }else{
       return false;
     }
   }

   /** Metode som fungerer paa samme maate som tomOgIngenAktiveTelegrafister, men
   * denne passer paa aktive kryptografer. Siden det er kun illustrert en operasjonsleder
   * i oppgaveteksten er det kanskje ikke saa noedvedndig med thread-safe metoder,
   * men det kan vaere greit aa legge det inn i tilfelle flere skal faa tilgang
   * til de dekrypterte meldingene.
   * @return boolean, true hvis dekrypterteMeldinger er tom og alle Kryptografer
   * er ferdige. False hvis ikke.
   */
   private boolean tomOgIngenAktiveKryptografer(){
     if(dekryptertTom() && aktiveKryptografer == 0){
       return true;
     }else{
       return false;
     }
   }
}
