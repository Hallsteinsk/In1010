Innlevering av Oblig 3, Hallstein Skjølsvik.

Filer som følger med i denne innleveringen:
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

I denne obligen valgte jeg å følge tankegangen som ble undervist i plenum uke 6. Jeg har en Lenket liste som implementerer grensesnittet Liste<T>. Den lenkede listen består av noder som inneholder data, og referanse til neste node. Lenkelisten og Node benytter generiske variabler, og kan derfor benyttes til mange formål. Grensesnittet Liste, blir dannet med å benytte Nodens public metoder. 

Stabel utvider, lenkelisten og tilbyr de samme metodene. Likevel er det leggPaa og taAv som realiserer hvordan en stabel fungerer. Jeg forestiller meg et stack med bøker, hvor man kan kun legge og ta av fra toppen. 

Den sorteret lenkelisten tar bort muligheten for å bestmme hvor man skal legge neste node. Den blir 
plassert inn basert på størrelsen til dataen. Minst elementer leggs først, og størts bakerst. Her ble koden litt rotete når man skal smette inn en ny node mellom to nye, men det ser ut til å fungere.  

I klassene Stabel og SortertLenkeliste kunne det vært nyttig å ha en peker til den bakerste noden. Da slipper man å bla gjennom hele listen for å poppe stacken eller sjekke om det nye objektet er større enn alle i listen. Men jeg var veldig usikker på om det var meningen å benytte en dobbellenket liste... Det var i såfall den eneste måten jeg så at jeg kunne løse dette praktisk på. Så jeg lot være å implementere det. I tillegg skulle taAv() benytte interfacet, og da ville ikke det kravet blitt oppfylt om jeg løste det på en annen måte.

Den nye typen unntak "UgyldigListeIndeks" blir kastet dersom man prøver å benytte en indeks som ikke eksisterer i listen. 

Jeg benyttet testene som fulgte med øvingen og alle listene har bestått disse. 

Vedlagt ligger en oversikt over klassehierarkiet. Her har jeg inkludert unntakene som arver fra "RuntimException". Jeg inkluderte ikke hva RuntimException arver fra for det blir kanskje mer uoversiktlig? 
Klassen node er også inkludert. 

Jeg var veldig usikker på om jeg skulle tegne inn at den generiske typen T arver fra "Comparable" i SorterLenkeliste. Er dette vanlig å tegne inn? Hvordan gjør man dette i såfall? 

Gøy Øving! Veldig spennende å bygge egne datastrukturer og teste ut generiske variabler. 