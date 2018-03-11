Innlevering av Oblig 3, Hallstein Skj�lsvik.

Filer som f�lger med i denne innleveringen:
-Liste.java
-Node.java
-Lenkeliste.java
-Stabel.java
-SortertLenkeliste.java
-UgyldigListeIndeks.java
-TestLenkeliste.java
-TestStabel.java
-TestSortertLenkeliste.java
-Klassehierarki.PNG

I denne obligen valgte jeg � f�lge tankegangen som ble undervist i plenum uke 6. Jeg har en Lenket liste som implementerer grensesnittet Liste<T>. Den lenkede listen best�r av noder som inneholder data, og referanse til neste node. Lenkelisten og Node benytter generiske variabler, og kan derfor benyttes til mange form�l. Grensesnittet Liste, blir dannet med � benytte Nodens public metoder. 

Stabel utvider, lenkelisten og tilbyr de samme metodene. Likevel er det leggPaa og taAv som realiserer hvordan en stabel fungerer. Jeg forestiller meg et stack med b�ker, hvor man kan kun legge og ta av fra toppen. 

Den sorteret lenkelisten tar bort muligheten for � bestmme hvor man skal legge neste node. Den blir 
plassert inn basert p� st�rrelsen til dataen. Minst elementer leggs f�rst, og st�rts bakerst. Her ble koden litt rotete n�r man skal smette inn en ny node mellom to nye, men det ser ut til � fungere.  

Den nye typen unntak "UgyldigListeIndeks" blir kastet dersom man pr�ver � benytte en indeks som ikke eksisterer i listen. 

Jeg benyttet testene som fulgte med �vingen og alle listene har best�tt disse. 

Vedlagt ligger en oversikt over klassehierarkiet. Her har jeg inkludert unntakene som arver fra "RuntimException". Jeg inkluderte ikke hva RuntimException arver fra for det blir kanskje mer uoversiktlig? 
Klassen node er ogs� inkludert. 

Jeg var veldig usikker p� om jeg skulle tegne inn at den generiske typen T arver fra "Comparable" i SorterLenkeliste. Er dette vanlig � tegne inn? Hvordan gj�r man dette i s�fall? 

G�y �ving! Veldig spennende � bygge egne datastrukturer og teste ut generiske variabler. 