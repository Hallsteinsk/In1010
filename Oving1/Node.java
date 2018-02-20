//Klassen Node lagrer info om en node. Der er minne og antall prosessorer lagret i heltall.
//Metodene getMinne og getProsessorer benyttes for aa teste klassen. 

class Node{

  //private variabler
  private int minne;
  private int prosessorer;

  //Constructor
  public Node(int minne, int prosessorer){
    this.minne = minne;
    this.prosessorer = prosessorer;
  }

  //Getters
  public int getMinne(){
    return minne;
  }

  public int getProsessorer(){
    return prosessorer;
  }
}
