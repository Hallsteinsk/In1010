
//Imports
import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class Operasjonsleder implements Runnable{

  //Private variables
  Monitor monitor;
  String mode;
  HashMap<Integer, ArrayList<Melding>> meldingsKanaler;

  //Constructor
  public Operasjonsleder(Monitor monitor, String mode){
    this.monitor = monitor;
    this.mode = mode;

    meldingsKanaler = new HashMap<Integer, ArrayList<Melding>>();
  }

  /** Thread-metoden
  */
  public void run(){
    try{
      Melding melding;
      melding = monitor.taUtDekryptert();
      while(melding.hentInnhold() != "STOPP"){
        if(!meldingsKanaler.containsKey(melding.hentKanalId())){
          meldingsKanaler.put(melding.hentKanalId(), new ArrayList<Melding>());
        }
        leggTilMelding(melding.hentKanalId(), melding);
        melding = monitor.taUtDekryptert();
      }

      skrivTilFil();

      if(mode.equals("debug")){
        for(ArrayList<Melding> meldinger : meldingsKanaler.values()){
          for(Melding m : meldinger){
            System.out.println(m.hentInnhold());
          }
          System.out.println();
        }
      }

    }catch(InterruptedException e){
      System.out.println("Operasjonsleder stoppet...");
    }
  }

  //
  private void leggTilMelding(int kanal, Melding melding){
    ArrayList<Melding> meldingsliste = meldingsKanaler.get(kanal);
    if(meldingsliste.size() == 0 ||
    melding.hentSekvensNummer() > meldingsliste.get(meldingsliste.size()-1).hentSekvensNummer()){
      meldingsliste.add(melding);
    }else{
      int i = 0;
      while(meldingsliste.get(i).hentSekvensNummer() < melding.hentSekvensNummer()){
        i++;
      }

      meldingsliste.add(meldingsliste.get(meldingsliste.size()-1));
      for(int j=meldingsliste.size()-2; j>i; j--){
        meldingsliste.set(j, meldingsliste.get(j-1));
      }
      meldingsliste.set(i, melding);
    }
  }

  //
  private void skrivTilFil(){
    for(int kanal : meldingsKanaler.keySet()){
      skrivTilFil(kanal);
    }
  }

  /** Metode som skriver innholdet i en kanal til en fil. Veldig inspirert av
  * Trix-oppgave 9.01 CD-samling.
  * @param int kanal er den kanalen i HashMapen som skal skrives til fil.
  */
  private void skrivTilFil(int kanal){
    File utfil = new File(String.format("Kanal:%d", kanal));
    PrintWriter filskriver;
    try{
      try{
        filskriver = new PrintWriter(utfil, "utf-8");
      }catch(UnsupportedEncodingException e){
        System.out.print("Faar ikke til aa encode i utf-8");
        filskriver = new PrintWriter(utfil);
      }

      for(Melding melding : meldingsKanaler.get(kanal)){
        if(!melding.hentInnhold().equals("INGEN FLERE MELDINGER")){
          filskriver.append(melding.hentInnhold() + "\n\n\n");
        }
      }
      filskriver.close();
    }catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }
  }

}
