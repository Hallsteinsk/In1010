import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class TestProgram{

  public static void main(String[] args){
    File fil = new File("3.in");

    try{
      Labyrint lab = Labyrint.lesFraFil(fil);
      System.out.println(lab.toString());

      System.out.println();
      /*
      System.out.println(lab.finnUtveiFra(5,3));
      System.out.println();
      System.out.println(lab.finnUtveiFra(5,1));
      */
    }catch(FileNotFoundException e){
      System.out.println("File not found!");
    }


  }
}
