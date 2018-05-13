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

  private Labyrint labyrint;
  private Ruteknapp[][] ruteknappArray;
  private Text infoTekst, viserUtveiNr;
  private Liste<String> utveier;
  private int utveinummer;
  private Button neste, forrige;

  private GridPane lagLabyrint(){
    this.ruteknappArray = new Ruteknapp[labyrint.hentRader()][labyrint.hentKolonner()];
    String lab = labyrint.toString();
    char tegn;
    GridPane rutenett = new GridPane();
    int rad = 0, kol = 0;
    rutenett.setGridLinesVisible(true);
    for(int i=0; i<lab.length(); i++){
      tegn = lab.charAt(i);
      if(tegn == '\n'){
        kol = 0;
        rad++;
      }else{
        ruteknappArray[rad][kol] = new Ruteknapp(rad, kol, tegn);
        ruteknappArray[rad][kol].setOnAction(new RuteknappKlikkBehandler(this));
        rutenett.add(ruteknappArray[rad][kol], kol, rad);
        kol++;
      }
    }
    return rutenett;
  }

  private HBox lagNextPrev(){
    neste = new Button(">");
    forrige = new Button("<");
    viserUtveiNr = new Text(" ");
    HBox hBox = new HBox(20);
    hBox.getChildren().addAll(forrige, viserUtveiNr, neste);
    return hBox;
  }

  public void nullStillLabyrint(){
    infoTekst.setText("Trykk paa en hvit rute");
    for(int i=0; i<labyrint.hentRader(); i++){
      for(int j=0; j<labyrint.hentKolonner(); j++){
        if(ruteknappArray[i][j].hentTegn() == '.'){
          ruteknappArray[i][j].settHvit();
        }
      }
    }
  }

  public void finnUtvei(int rad, int kol){
    utveier = labyrint.finnUtveiFra(kol, rad);
    if(utveier.stoerrelse() > 0){
      utveinummer = 0;
      visUtvei();
      infoTekst.setText(String.format("Viser utvei fra (%d, %d)", kol, rad));
      viserUtveiNr.setText(String.format("Utvei nr %d av %d", utveinummer+1, utveier.stoerrelse()));
    }else{
      nullStillLabyrint();
      infoTekst.setText(String.format("Ingen utvei fra (%d, %d), =(", kol, rad));
    }
  }

  private void visUtvei(){
    boolean[][] utvei = losningStringTilTabell(
    utveier.hent(utveinummer), labyrint.hentKolonner(), labyrint.hentRader());
    nullStillLabyrint();
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

  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage teater){

    File labyrintFil = new FileChooser().showOpenDialog(teater);
    if(labyrintFil != null){
      try{
        labyrint = Labyrint.lesFraFil(labyrintFil);
      }catch(FileNotFoundException e){
        System.out.println("Fant ikke fil...");
      }
    }


    infoTekst = new Text("Trykk paa en hvit rute");
    infoTekst.setFont(new Font(20));

    VBox vBox = new VBox();
    ScrollPane scroll = new ScrollPane();
    scroll.setContent(lagLabyrint());
    scroll.setPrefSize(500, 750);
    if(labyrint != null){
      vBox.getChildren().add(infoTekst);
      vBox.getChildren().add(lagNextPrev());
      vBox.getChildren().add(scroll);
    }else{
      infoTekst.setText("Har ingen labyrint..");
      vBox.getChildren().add(infoTekst);
    }



    Scene scene = new Scene(vBox, 600, 800);

    teater.setScene(scene);
    teater.show();
  }
}
