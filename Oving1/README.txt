Leverer filene: 
-regneklynge.png (Oppgave A)
-Node.java
-Rack.java
-Regneklynge.java
-Hovedprogram.java
-test.txt
-README.txt


Strukturen presentert i regneklynge.png tenker hvordan jeg tenkte fram til oppgave E.
Noder var sm� enkle klasser. Rack holdt styr p� Noder, mens Regneklynge holdt styr p� alle rack. 
For � legge til en ny Node mottar Regneklyngen referansen til et nytt Node-objekt, og sender dette videre 
til et rack med ledig plass. Dersom det ikke finnes noen rack med ledig plass, blir det opprettet et nytt.
Datatypene int ble valgt for � lagre minne og antall prosessorer da disse vanligvis ikke opptrer som desimaltall.
Datatypen ArrayList ble valgt for � h�ndtere lister da disse er fleksible og kan utvides etter hvert som det trengs.
F�r oppgave E tenkte jeg at hovedprogrammet h�ndterte � legge til flere noder, mens regneklyngen la til en
og en. Etterhvert fant jeg ut at det var bedre om regneklyngen selv kunne opprette Node-objekter basert p�
informasjon som ble lest ut av filen. Denne oppgaven leveres med "test.txt" som jeg benyttet til slutt for �
verifisere at programmet fungerte som tiltenkt.