class Kryptograf implements Runnable{

  private MonitorKryptert kMonitor;
  //private MonitorDekryptert dMonitor;
  private String mode;

  public Kryptograf(MonitorKryptert kMonitor, /*MonitorDekryptert dMonitor,*/ String mode){
    this.kMonitor = kMonitor;
    //this.dMonitor = dMonitor;
    this.mode = mode;
  }

  /** Thread-metode.
  * Denne traaden gaar i en loekke som leser inn meldinger fra monitoren, og
  * dekrypterer dem. Den dekrypterte meldingen puttes inn i en monitor for dekrypterte
  * meldinger. Dersom det ikke er flere meldinger i monitor og alle
  * telegrfistene har stoppet mottar traaden beskjeden "STOPP", og traaden
  * avsluttes.
  */
  @Override
  public void run(){
    try{
      Melding kryptert;
      String dekryptertTekst;
      while(true){
        kryptert = kMonitor.taUt();
        if(kryptert.hentInnhold().equals("STOPP")){break;}
        dekryptertTekst = Kryptografi.dekrypter(kryptert.hentInnhold());
        //TODO: dMonitor.leggTil(new Melding(dekryptertTekst, kryptert.hentKanalId(), kryptert.hentSekvensNummer());
        if(mode.equals("debug")){System.out.printf("Dekryptert melding: %s%n", dekryptertTekst);}
      }
    }catch(InterruptedException e){
      System.out.println("Kryptograf Stoppet...");
    }
  }
}
