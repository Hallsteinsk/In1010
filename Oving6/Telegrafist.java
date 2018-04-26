/** Klassen Telegrafist skal simulere en telegrafist som lytter til en kanal
* etter krypterte meldinger. Meldinger sendes videre til en monitor hvor
* kryptografer kan dekryptere dem.
*
* Denne klassen implemnterer Runnable og klassens hovedfunksjon er aa fungere
* som en traad. Dette blir gjort med den overskrevne metoden run.
*
* Det er ogsaa lagt inn en "mode" variabel som ble benytter under debugging.
* Man kan se bort ifra denne i det ferdige programmet.
*/

class Telegrafist implements Runnable{

  //Private variabler
  private Kanal kanal;
  private Monitor monitor;
  private String mode;
  private int kanalId;
  private int sekvensnummer;

  //Constructor
  public Telegrafist(Kanal kanal, Monitor monitor, String mode){
    this.mode = mode;
    this.kanal = kanal;
    this.monitor = monitor;
    kanalId = kanal.hentId();
    sekvensnummer = 1;
    if(mode.equals("debug")){System.out.println("Ny telegrfist");}
  }

  /** Thread-metode
  * Denne traden lytter til telegrafisten sin kanal og lager en melding med
  * den krypterte teksten. Meldingen legges inn i en monitor for krypterte meldinger.
  * Nar det ikke er flere meldinger i kanalen mottar traaden null. Da legges
  * meldingen "INGEN FLERE MELDINGER" inn i monitoren, og traaden avsluttes.
  */
  @Override
  public void run(){
    try{
      String melding = kanal.lytt();
      while(melding != null){
        monitor.leggTilKryptert(new Melding(melding, kanalId, sekvensnummer));
        sekvensnummer++;
        melding = kanal.lytt();
      }
      monitor.leggTilKryptert(new Melding("INGEN FLERE MELDINGER", kanalId, sekvensnummer));
    }catch(InterruptedException e){
      System.out.printf("Telegrafist som lytter til kanal %d stoppet paa sekvensnummer %d.%n",
      kanalId, sekvensnummer);
    }
  }
}
