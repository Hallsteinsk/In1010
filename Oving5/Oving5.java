import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;

class Oblig5 {
    public static void main(String[] args) {
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);

                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);

                /*Her la jeg inn en del linjer. Foerst sjekker jeg om labyrintens
                metode finnUtveiFra returnerer en tom string. Skjer dette betyr
                det at ingen utveier ble naadd. Er det ingen utveir faar bruker
                en beskjed om dette. Dereter sjekker jeg om utveier inneholoder
                koordinater, eller en beskjed om at den startet i en sort rute.
                Starter man i en sort rute faar man beskjed om dette, og maa proeve
                nye koordinater. Er det utveier i listen blir man presantert med
                den raskeste, og hvor mange utveier det er fra startruten. Man
                kan velge aa se alle utveine med aa trykke paa 'j'.*/

                if (utveier.stoerrelse() != 0) {
                    if(utveier.hent(0).charAt(0) == '('){
                      System.out.printf("Det er %d veier ut fra (%d, %d). Her er den raskeste:%n",
                        utveier.stoerrelse(), startKol, startRad);
                      System.out.println(utveier.hent(0));
                      System.out.println("Vil du alle utveiene? ('j' for aa se utveiene)");
                      String[] skrivUt = inn.nextLine().split(" ");
                      if(skrivUt[0].equals("j")){
                        int teller = 1;
                        for (String s : utveier) {
                            System.out.printf("%d: %s%n",teller, s);
                            teller++;
                        }
                      }
                    }else{
                      System.out.println(utveier.hent(0));
                    }

                } else {
                    System.out.println("Ingen utveier.");
                }
                System.out.println();
            //La inn denne siden jeg var slurvete med aa skrive inn koordinater,
            // og programmet sluttet ofte med dette unntaket.
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Skriv inn TO koordinater!");
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }

            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
    }
}
