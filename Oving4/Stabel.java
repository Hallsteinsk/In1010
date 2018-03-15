class Stabel<T> extends Lenkeliste<T>{

  public Stabel(){
    super();
  }

  //Metode som legger til et element bakertst
  // @param T x er dataen som skal leggest til i stablen
  public void leggPaa(T x){
    //Benytter metode i super som allerede gjoer dette
    super.leggTil(x);
  }

  //Metode som henter ut det bakerste elementet i stablen
  // @return T er dataen i den bakerste noden
  public T taAv(){
    T data = super.fjern(super.stoerrelse() - 1);
    return data;
  }

}
