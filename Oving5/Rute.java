import java.util.Hashtable;

abstract class Rute{
  //protected medlemmer
  protected int kolonne;
  protected int rad;
  protected Hashtable<String, Rute> naboer;
  protected char tegn;
  protected String type;

  //Constructor
  public Rute(){
    naboer = new Hashtable<String, Rute>();
  }


  public void lagNabo(String retning, Rute rute){
    naboer.put(retning, rute);
  }

  public char tilTegn(){
    return tegn;
  }

  public String type(){
    return type;
  }

}
