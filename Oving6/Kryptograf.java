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
      while(true){
        kryptert = kMonitor.taUt();
        if(kryptert.hentInnhold().equals("STOPP")){
          if(mode.equals("debug")){System.out.println("Stopp");
          break;
          }
        }

        dekryptertTekst = Kryptografi.dekrypter(kryptert.hentInnhold());
        //if(mode.equals("debug")){System.out.printf("Dekryptert melding: %s%n", dekryptertTekst);}
      }
    }catch(InterruptedException e){
      System.out.println("Kryptograf Stoppet...");
    }
  }
}
