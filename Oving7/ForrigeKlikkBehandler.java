/*EventHandler som haandterer at man trykker paa forrige-knappen.
Den benytter seg av visForrige-metoden til LabyrintAppen dem tilhører.
*/

import javafx.event.*;

class ForrigeKlikkBehandler implements EventHandler<ActionEvent>{

  private LabyrintApp labyrintApp;

  //Constructor
  public ForrigeKlikkBehandler(LabyrintApp labyrintApp){
    this.labyrintApp = labyrintApp;
  }

  @Override
  public void handle(ActionEvent e){
    labyrintApp.visForrige();
  }
}
