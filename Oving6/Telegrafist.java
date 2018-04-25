class Telegrafist implements Runnable{

  private Kanal kanal;
  private MonitorKryptert monitor;
  private String mode;
  private int kanalId;
  private int sekvensnummer;

  //Constructor
  public Telegrafist(Kanal kanal, MonitorKryptert monitor, String mode){
    this.mode = mode;
    this.kanal = kanal;
    this.monitor = monitor;
    kanalId = kanal.hentId();
    sekvensnummer = 1;
    if(mode.equals("debug")){System.out.println("Ny telegrfist");}
  }

  /** Thread-metode
  */
  @Override
  public void run(){
    try{
      String melding = kanal.lytt();
      while(melding != null){
        //if(mode.equals("debug")){System.out.printf("KanalID: %d, sekvensnummer: %d%n",
        //kanalId, sekvensnummer);}
        monitor.leggTil(new Melding(melding, kanalId, sekvensnummer));
        sekvensnummer++;
        melding = kanal.lytt();
        //if(mode.equals("debug")){Thread.sleep(500);}
      }
      monitor.leggTil(new Melding("INGEN FLERE MELDINGER", kanalId, sekvensnummer));
    }catch(InterruptedException e){
      System.out.printf("Telegrafist som lytter til kanal %d stoppet paa sekvensnummer %d.%n",
      kanalId, sekvensnummer);
    }
  }
}
