class Kryptograf implements Runnable{

  private Monitor monitor;
  private String mode;

  public Kryptograf(Monitor monitor, String mode){
    this.monitor = monitor;
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
      String innhold;
      String dekryptertTekst;
      while(true){
        kryptert = monitor.taUtKryptert();
        innhold = kryptert.hentInnhold();
        if(innhold.equals("STOPP")){
          break;
        }else if(innhold.equals("INGEN FLERE MELDINGER")){
          monitor.leggTilDekryptert(new Melding(innhold, kryptert.hentKanalId(), kryptert.hentSekvensNummer()));
        }else{
          dekryptertTekst = Kryptografi.dekrypter(kryptert.hentInnhold());
          monitor.leggTilDekryptert(new Melding(dekryptertTekst, kryptert.hentKanalId(), kryptert.hentSekvensNummer()));
          if(mode.equals("debug")){System.out.printf("Dekryptert melding: %s%n", dekryptertTekst);}
        }

      }
    }catch(InterruptedException e){
      System.out.println("Kryptograf Stoppet...");
    }
  }
}
