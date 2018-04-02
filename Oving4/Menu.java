/*Denne klassen inneholder alle menyene som skrives ut i legesystemet.
Det er mer ryddig at disse rene utskriftsmetodene ligger her, saa blir det
ikke saa mange ekstra linjer i legesystemet. Jeg har ikke kommentert metodene
videre da de er ganske selvforklarende, og kommentarer kan gjoere det mer rotete.
*/

class Menu{

  public Menu(){}

  public void printMainMenu(){
    System.out.println(" ");
    System.out.println("Hovedmeny:\nVelg et alternativ(1, 2, 3, 4, eller 9)");
    System.out.println("1. Skriv ut en fulstendig oversikt over personer, leger, legemiddel, og resepter");
    System.out.println("2. Legg til et nytt element.");
    System.out.println("3. Bruk en resept for en pasient.");
    System.out.println("4. Skriv ut statistikk.");
    System.out.println("9. Avslutt.");
  }

  public void printLeggTil(){
    System.out.println(" ");
    System.out.println("Legg til:\nVelg et alternativ(1, 2, 3, 4, 0, eller 9)");
    System.out.println("0. Returner til forrige menu.");
    System.out.println("1. Legg til en Pasient");
    System.out.println("2. Legg til en Lege");
    System.out.println("3. Legg til et Legemiddel");
    System.out.println("4. Legg til en Resept");
    System.out.println("9. For aa avlslutte");
  }

  public void printLeggTilLege(){
    System.out.println(" ");
    System.out.println("0. For aa angre.");
    System.out.println("Hvilken type lege vil du legge til?:\nVelg et alternativ(1, 2, 0, eller 9)");
    System.out.println("1. Legg til en vanlig Lege");
    System.out.println("2. Legg til en Fastlege");
    System.out.println("9. For aa avlslutte");
  }

  public void printLeggTilLegemiddel(){
    System.out.println(" ");
    System.out.println("Hvilken type legemiddel vil du legge til?:\nVelg et alternativ(1, 2, 3, 0, eller 9)");
    System.out.println("0. For aa angre.");
    System.out.println("1. Legg til Legemiddel type A");
    System.out.println("2. Legg til Legemiddel type B");
    System.out.println("3. Legg til Legemiddel type C");
    System.out.println("9. For aa avlslutte");
  }

  public void printLeggTilResept(){
    System.out.println(" ");
    System.out.println("Hvilken farge skal resepten ha?:\nVelg et alternativ(1, 2, 3, 4, 0, eller 9)");
    System.out.println("0. For aa angre.");
    System.out.println("1. Hvit");
    System.out.println("2. Blaa");
    System.out.println("3. Prevensjon");
    System.out.println("4. Militaer");
    System.out.println("9. For aa avlslutte");
  }

  public void printLeggTilIgjen(){
    System.out.println(" ");
    System.out.println("Vil du legge til en ny?:\nVelg et alternativ(0, 1, eller 9)");
    System.out.println("0. Gaa en meny tilbake");
    System.out.println("1. Legg til en ny.");
    System.out.println("9. Avslutt programmet");
  }
}
