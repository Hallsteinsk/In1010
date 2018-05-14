/*Denne EventHandleren trigges naar en rutekanpp trykkes inn. Da skal
utveiene fra den aktuelle knappen vises. Her hentes kolonne og rad fra
ruteknappen og saa kalles finnUtvei i labrintAppen.
Trykker man paa en sort knapp skal labyrinten nullstilles.
*/

import javafx.event.*;

class RuteknappKlikkBehandler implements EventHandler<ActionEvent>{

  private LabyrintApp labyrintApp;
  private Ruteknapp knappTrykket;

  //Constructor
  public RuteknappKlikkBehandler(LabyrintApp labyrintApp){
    this.labyrintApp = labyrintApp;
  }

  @Override
  public void handle(ActionEvent e){
    knappTrykket = (Ruteknapp)e.getSource();
    if(knappTrykket.hentTegn() != '#'){
      labyrintApp.finnUtvei(knappTrykket.hentRad(), knappTrykket.hentKolonne());
    }else{
      labyrintApp.nullStillLabyrint();
    }
  }
}
