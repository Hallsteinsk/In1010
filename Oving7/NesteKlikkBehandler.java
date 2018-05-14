/*EventHandler som haandterer at man trykker paa neste-knappen.
Den benytter seg av visNeste-metoden til LabyrintAppen den tilhører.
*/

import javafx.event.*;

class NesteKlikkBehandler implements EventHandler<ActionEvent>{

  private LabyrintApp labyrintApp;

  //Constructor
  public NesteKlikkBehandler(LabyrintApp labyrintApp){
    this.labyrintApp = labyrintApp;
  }

  @Override
  public void handle(ActionEvent e){
    labyrintApp.visNeste();
  }
}
