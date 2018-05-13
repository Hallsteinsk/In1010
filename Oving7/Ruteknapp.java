import javafx.scene.control.Button;

class Ruteknapp extends Button{
  private char tegn;
  private int rad;
  private int kol;
  //private LabyrintApp labyrintApp;

  //Constructor
  public Ruteknapp(int rad, int kol, char tegn){
    super();
    this.rad = rad;
    this.kol = kol;
    this.tegn = tegn;
    //this.labyrintApp = labyrintApp;
    this.setPrefSize(10, 10);

    if(tegn == '.'){
      settHvit();
    }else if(tegn == '#'){
      settSort();
    }
  }

  //Metoder som setter fargen til ruteknappen:
  public void settBlaa(){
    if(tegn != '#'){
      this.setStyle("-fx-base: #0000ff;");
    }
  }

  public void settHvit(){
    if(tegn != '#'){
      this.setStyle("-fx-base: #ffffff;");
    }
  }

  public void settSort(){
    this.setStyle("-fx-base: #000000;");
  }

  //Getter metoder:
  public char hentTegn(){
    return tegn;
  }

  public int hentRad(){
    return rad;
  }

  public int hentKolonne(){
    return kol;
  }

}
