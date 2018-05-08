//Unntak som blir kastet hvis man gir en ugyldig indeks til en liste
class UgyldigListeIndeks extends RuntimeException{
  UgyldigListeIndeks(int indeks){
    super("Ugyldig indeks" + indeks);
  }
}
