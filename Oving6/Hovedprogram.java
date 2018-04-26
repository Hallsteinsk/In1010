
import java.util.ArrayList;

class Hovedprogram{

  final static int ANTALL_TELEGRAFISTER = 3;
  final static int ANTALL_KRYPTOGRAFER= 5;
  static String mode;

  public static void main(String[] args){
    //Bestemmer om programmet skal kjoeres i debug-mode
    try{
      mode = args[0];
      if(mode.equals("debug")){
        System.out.println("Kjoerer i debug-mode.");
      }else{
        System.out.println("Kjoerer ikke i debug-mode.");
        mode = "a";
      }
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Kjoerer ikke i debug-mode.");
      mode = "b";
    }

    //Lager Operasjonssentral og henter ut kanaler, akkurat som oppgaveteksten beskriver
    Operasjonssentral ops = new Operasjonssentral(ANTALL_TELEGRAFISTER);
    Kanal[] kanaler = ops.hentKanalArray();

    //Lager monitor for krypterte beskjeder
    MonitorKryptert monitorK = new MonitorKryptert(mode, ANTALL_TELEGRAFISTER);

    //Lager en ArrayList med telegrfister, hvor hver telegrfist faar sin kanal.
    ArrayList<Telegrafist> telegrafister = new ArrayList<>();
    for(Kanal kanal : kanaler){
      telegrafister.add(new Telegrafist(kanal, monitorK, mode));
    }

    //Lager Kryptografer
    ArrayList<Kryptograf> kryptografer = new ArrayList<>();
    for(int i=0; i<ANTALL_KRYPTOGRAFER; i++){
      kryptografer.add(new Kryptograf(monitorK, mode));
    }

    //Starter telegrfister
    for(Telegrafist telegrafist : telegrafister){
      new Thread(telegrafist).start();
    }

    //Starter Kryptografer
    for(Kryptograf kryptograf : kryptografer){
      new Thread(kryptograf).start();
    }
  }
}
