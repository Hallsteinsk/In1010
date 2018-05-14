//Imports
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;


public class LabyrintApp extends Application{

  //Variabler og objekter som er globalt tilgjengelig inne i LabyrintAppen
  private Labyrint labyrint;
  private Ruteknapp[][] ruteknappArray;
  private Text infoTekst, viserUtveiNr;
  private Liste<String> utveier;
  private int utveinummer;
  private Button neste, forrige;


  //Metoder som hjelper til med aa sette opp layout og lager fx-objekter.

  /** Metode som lager et grid pane med alle ruteknappene til labyrinten.
  * Det legges en referanse til hverruteknappe inn i arrayet ruteknappArray.
  * Alle ruteknapper blir knyttet til en klikkbehandler.
  * @return GridPane som inneholder alle ruteknappene.
  */
  private GridPane lagLabyrint(){
    this.ruteknappArray = new Ruteknapp[labyrint.hentRader()][labyrint.hentKolonner()];
    String lab = labyrint.toString();
    char tegn;
    GridPane rutenett = new GridPane();
    int rad = 0, kol = 0;
    rutenett.setGridLinesVisible(true);
    RuteknappKlikkBehandler ruteklikk = new RuteknappKlikkBehandler(this);
    for(int i=0; i<lab.length(); i++){
      tegn = lab.charAt(i);
      if(tegn == '\n'){
        kol = 0;
        rad++;
      }else{
        ruteknappArray[rad][kol] = new Ruteknapp(rad, kol, tegn);
        ruteknappArray[rad][kol].setOnAction(ruteklikk);
        rutenett.add(ruteknappArray[rad][kol], kol, rad);
        kol++;
      }
    }
    return rutenett;
  }

  /** Metode som setter opp en horisontal boks med to knapper som fungerer som
  * 'next' og 'previous', samt en tekst som indikerer hvor mange utveier det finnes
  * og hvilken som vises naa.
  * Knappene blir satt opp med hver sin klikkbehandler.
  * @return HBox som inneholder to knapper og informasjon om hvilken utvei som vises.
  */
  private HBox lagNextPrev(){
    neste = new Button(">");
    neste.setOnAction(new NesteKlikkBehandler(this));
    forrige = new Button("<");
    forrige.setOnAction(new ForrigeKlikkBehandler(this));
    viserUtveiNr = new Text(" ");
    HBox hBox = new HBox(20);
    hBox.getChildren().addAll(forrige, viserUtveiNr, neste);
    return hBox;
  }


  //Metoder som benyttes for aa tekne opp og fjerne utveier.

  /* Metode som finner og viser utvei fra en ruteknapp. Denne blir aktivert
  * av en klikkbehandler.
  * @param int rad er raden til ruteknappen man skal finne utvei fra
  * @param int kol er kolonnen til ruteknappen man skal finne utvei fra.
  */
  public void finnUtvei(int rad, int kol){
    utveier = labyrint.finnUtveiFra(kol, rad);
    if(utveier.stoerrelse() > 0){
      utveinummer = 0;
      visUtvei();
      infoTekst.setText(String.format("Viser utvei fra (%d, %d)", kol, rad));
    }else{
      nullStillLabyrint();
      infoTekst.setText(String.format("Ingen utvei fra (%d, %d), =(", kol, rad));
    }
  }

  /* Hjelpemetode som viser utveien som ligger med indeks 'utveinummer'.
  * Her benyttes "losningStringTilTabell" for aa generere et array for aa
  * representere en utvei. Dersom man oensker aa se en annen utvei endrer man
  * indeksen 'utveinummer'
  */
  private void visUtvei(){
    boolean[][] utvei = losningStringTilTabell(
    utveier.hent(utveinummer), labyrint.hentKolonner(), labyrint.hentRader());
    nullStillLabyrint();
    viserUtveiNr.setText(String.format("Utvei nr %d av %d", utveinummer+1, utveier.stoerrelse()));
    for(int r=0; r<labyrint.hentRader(); r++){
      for(int k=0; k<labyrint.hentKolonner(); k++){
        if(utvei[r][k]){
          ruteknappArray[r][k].settBlaa();
        }
      }
    }
  }

  /**
   * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
   * av losningstien.
   * Denne metoden er hentet fra In1010 vaar 2018 sine nettsider. Den var vedlagt
   * som et forslag til en maaate aa loese oppgavene paa. Jeg copy/pastet den
   * inn her.
   * @param losningString String-representasjon av utveien
   * @param bredde        bredde til labyrinten
   * @param hoyde         hoyde til labyrinten
   * @return              2D-representasjon av rutene der true indikerer at
   *                      ruten er en del av utveien.
   */
  private boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while (m.find()) {
          int x = Integer.parseInt(m.group(1));
          int y = Integer.parseInt(m.group(2));
          losning[y][x] = true;
      }
      return losning;
  }

  /*Metode som "resetter" labyrinten. Dvs at alle utveier fjernes, og hvite
  * ruter settes tilbake til hvit.
  */
  public void nullStillLabyrint(){
    infoTekst.setText("Trykk paa en hvit rute");
    viserUtveiNr.setText("                 ");
    for(int i=0; i<labyrint.hentRader(); i++){
      for(int j=0; j<labyrint.hentKolonner(); j++){
        if(ruteknappArray[i][j].hentTegn() == '.'){
          ruteknappArray[i][j].settHvit();
        }
      }
    }
  }

  //Metode som viser den forrige utveien
  public void visForrige(){
    if(utveinummer > 0){
      utveinummer--;
      visUtvei();
    }
  }

  //Metode som viser den neste utveien
  public void visNeste(){
    if(utveinummer < utveier.stoerrelse() - 1){
      utveinummer++;
      visUtvei();
    }
  }

  /*LabyrintAppen sin start-metode. Her bygges alle elementer inn i en vertikal
  * boks. Oeverst er det en liten beskjed til bruker, saa kommer
  * previous/next-funksjonaliteten. Til slutt vises labyrintetn i en ScrollPane.
  * VBoxen settes inn i en scene. Store labyrinter faar ikke plass her,
  * saa man maa scrolle for aa se alt innholdet.
  * @param Stage teater er Stagen som skal vises frem.
  */
  public void start(Stage teater){

    //Starter med aa la bruker velge labyrintfil i filvelger.
    File labyrintFil = new FileChooser().showOpenDialog(teater);
    if(labyrintFil != null){
      try{
        labyrint = Labyrint.lesFraFil(labyrintFil);
      }catch(FileNotFoundException e){
        System.out.println("Fant ikke fil...");
      }
    }

    //BYgger opp en VBox med alle elementene til applikasjonen.
    infoTekst = new Text("Trykk paa en hvit rute");
    infoTekst.setFont(new Font(20));

    VBox vBox = new VBox();
    ScrollPane scroll = new ScrollPane();
    if(labyrint != null){
      vBox.getChildren().add(infoTekst);
      vBox.getChildren().add(lagNextPrev());
      scroll.setContent(lagLabyrint());
      vBox.getChildren().add(scroll);
    }else{
      infoTekst.setText("Har ingen labyrint...");
      vBox.getChildren().add(infoTekst);
    }

    //Legger inn VBox i scenen, og scenen i teater.
    Scene scene = new Scene(vBox);
    teater.setScene(scene);
    teater.show();
  }

  //Main
  public static void main(String[] args){
    launch(args);
  }

}
