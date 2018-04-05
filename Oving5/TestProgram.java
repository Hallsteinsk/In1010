import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class TestProgram{

  public static void main(String[] args){
    File fil = new File("1.in");

    try{
      Labyrint lab = Labyrint.lesFraFil(fil);
      System.out.println(lab.toString());
    }catch(FileNotFoundException e){
      System.out.println("File not found!");
    }






  }
}
