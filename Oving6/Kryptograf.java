class Kryptograf implements Runnable{

  private MonitorKryptert kMonitor;
  //private MonitorDekryptert dMonitor;
  private String mode;

  public Kryptograf(MonitorKryptert kMonitor, /*MonitorDekryptert dMonitor,*/ String mode){
    this.kMonitor = kMonitor;
    //this.dMonitor = dMonitor;
    this.mode = mode;
  }

  public void run(){
    try{
      Melding kryptert = kMonitor.taUt();
      while(!kryptert.hentInnhold().equals("INGEN FLERE MELDINGER")){
        String dekryptertTekst = Kryptografi.dekrypter(kryptert.hentInnhold());
        if(mode.equals("debug")){System.out.printf("Dekryptert melding: %s%n", dekryptertTekst);}
        kryptert = kMonitor.taUt();
      }
    }catch(InterruptedException e){
      System.out.println("Kryptograf Stoppet...");
    }
  }
}
