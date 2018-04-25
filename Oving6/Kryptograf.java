class Kryptograf implements Runnable{

  private MonitorKryptert kMonitor;
  //private MonitorDekryptert dMonitor;
  private String mode;

  public Kryptograf(MonitorKryptert kMonitor, /*MonitorDekryptert dMonitor,*/ String mode){
    this.kMonitor = kMonitor;
    //this.dMonitor = dMonitor;
    this.mode = mode;
  }

  @Override
  public void run(){
    try{
      Melding kryptert;
      String dekryptertTekst;
      while(!kMonitor.tomOgIngenAktiveTelegrafister()){
        kryptert = kMonitor.taUt();
        dekryptertTekst = Kryptografi.dekrypter(kryptert.hentInnhold());
        if(mode.equals("debug")){System.out.printf("Dekryptert melding: %s%n", dekryptertTekst);}
        kryptert = kMonitor.taUt();
        if(kryptert.hentInnhold().equals("INGEN FLERE MELDINGER")){
          aktiveTelegrafister--;
          if(mode.equals("debug")){System.out.printf("INGEN FLERE MELDINGER. Aktvie telegrfister: %d%n"
          , aktiveTelegrafister);}
        }
      }
    }catch(InterruptedException e){
      System.out.println("Kryptograf Stoppet...");
    }
  }
}
