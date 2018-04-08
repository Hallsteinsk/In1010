import java.util.Iterator;

class LenkelisteIterator<T> implements Iterator<T>{

  //Referanse til den noden som skal evalueres med grensesnittet next, hasNext (og remove)
  private Node<T> nodeRef;
  private Node<T> prevRef;

  public LenkelisteIterator(Node<T> startRef){
    this.nodeRef = startRef;
  }

  //Interface
  public boolean hasNext(){
      if(nodeRef.neste() == null){
        return false;
      }else{
        return true;
      }
  }

  public T next(){
    prevRef = nodeRef;
    nodeRef = nodeRef.neste();
    return nodeRef.hent();
  }

  public void remove(){
    prevRef.settNeste(nodeRef.neste());
    nodeRef = prevRef;
  }

}
