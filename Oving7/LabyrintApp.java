import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;


public class LabyrintApp extends Application{

  private Labyrint labyrint;
  private Ruteknapp[][] ruteknappArray;
  private Text infoTekst;

  class Ruteknapp extends Button{
    private char tegn;
    private int rad;
    private int kol;

    public Ruteknapp(int rad, int kol, char tegn){
      super(" ");
      this.rad = rad;
      this.kol = kol;
      this.tegn = tegn;
      this.setPrefSize(40, 40);

      if(tegn == '.'){
        this.setStyle("-fx-base: #ffffff");
        //this.setStyle("-fx-border-color: #111111; -fx-border-width: 1px;");
      }else if(tegn == '#'){
        this.setStyle("-fx-base: #000000");
      }
    }

    public void finnUtvei(){
      Liste<String> utveier = labyrint.finnUtveiFra(kol, rad);
      boolean[][] besteUtvei = losningStringTilTabell(
      utveier.hent(0), labyrint.hentKolonner(), labyrint.hentRader());

      for(int r=0; r<labyrint.hentRader(); r++){
        for(int k=0; k<labyrint.hentKolonner(); k++){
          if(besteUtvei[r][k]){
            ruteknappArray[r][k].settUtvei();
          }
        }
      }
    }

    public void settUtvei(){
      if(tegn != '#'){
        this.setStyle("-fx-base: #0000ff;");
      }
    }

    public void settHvit(){
      if(tegn != '#'){
        this.setStyle("-fx-base: #ffffff;");
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

  }

  class KlikkBehandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent e){
      ((Ruteknapp)e.getSource()).finnUtvei();
    }
  }

  /*
  public Scene lagStartskjerm(){
    Button velgFil = new Button("Velg labyrint");
    Button avslutt = new Button("Avslutt");
    GridPane knapper = new GridPane();
    knapper.add(velgFil, 0, 0);
    kanpper.add(avslutt, 0, 1);

    return new Scene(kanpper, 400, 400);
  }*/

  public GridPane lagLabyrint(){
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
        ruteknappArray[rad][kol].setOnAction(new KlikkBehandler());
        rutenett.add(ruteknappArray[rad][kol], kol, rad);
        kol++;
      }
    }
    return rutenett;
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

    GridPane kulisser = new GridPane();
    if(labyrint != null){
      kulisser.add(infoTekst, 0, 0);
      kulisser.add(lagLabyrint(), 0, 1);
    }else{
      infoTekst.setText("Har ingen labyrint..");
      kulisser.add(infoTekst, 0, 0);
    }



    Scene scene = new Scene(kulisser);

    teater.setScene(scene);
    teater.show();
  }
}
