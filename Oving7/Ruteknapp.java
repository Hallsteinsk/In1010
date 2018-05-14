/*Labyrinten bestaar av ruter. Her har jeg laget en klasse for ruter som
bygger paa klassen button. Derfor har den faatt navnet ruteknapp. Knapper
er lette aa trykke paa og trigge EventHandlere. I tillegg faar bruker en diskre
tilbakemelding om at en knapp er trykket paa med at den faar en svak outline.
En Ruteknapp er klar over hvilken rad og kolonne den har, og hvilken type den er
(sort/hvit), siden den inneholder tegnet fra labyrintfilen. Rad, kolonne, og
tegn kan hentes ut med gettermetoder.
Fargen til ruteknappen kan ogsaa settes med setters.
*/

import javafx.scene.control.Button;

class Ruteknapp extends Button{
  private char tegn;
  private int rad;
  private int kol;

  //Constructor
  public Ruteknapp(int rad, int kol, char tegn){
    super();
    this.rad = rad;
    this.kol = kol;
    this.tegn = tegn;
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
