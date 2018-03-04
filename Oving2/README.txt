Levering av �ving2 i IN1010. Hallstein Skj�lsvik
Filer som er inkludert:
-Legemiddel.java 	-LegemiddelA.java	-LegemiddelB.java	-LegemiddelC.java
-Resept.java		-Hvitresept.java	-MillitaerResept.java	-PResept.java	-BlaaResept.java
-Lege.java		-FastLege.java		-Kommuneavtale.java
-TestLegemiddel.java	-TestResept.java	-Hovedprogram.java
-KlassehierarkiLegemiddel.png			-KlassehierarkiLege.png	
-KlassehierarkiResept.png			-Datastruktur.png	
-Readme.txt


Her har jeg pr�vd � l�se oppgaven etter beste evne. Testprogrammene mine ble litt store, og inneholder en del st�tteprosedyrer. Er det lurt � ha s� mange prosedyrer? Jeg inns� at mye av testkoden kunne blitt gjenbrukt hadde jeg strukturert det p� en annen m�te. Er det vanlig � opprette testobjekter, som man kan kalle i st�rre integrasjonstester? 

Her er noen valg jeg har gjort:
Legemiddel:
Alle legemiddler deler en statisk int som inneholder totalt antall legemiddler. Denne �kes i konstruktor, og benyttes som ID da denne er unik. 

Test av legemiddler: 
Her lagde jeg en testprosdeyre som testet om alle verider til et legemiddel samsvarte med det jeg mente det burde v�re, og skrev resultatet ut i terminal. 

Resept:
Her pr�vde jeg � dele opp metoder mest mulig mellom superklasser og underklasser. P� den m�ten ble det ikke s� mye ny informasjon i en subklasse. Det var en del mer behagelig � tenke p� abstrakte metoder, for da ble det mer felles metoder for super og sub. 

Test av Resept.
Her tester jeg alle variablene til en resept og alle variablene til legemidlet som resepten peker til. De testprosedyrene ble litt massive, s� jeg pr�vde � dele det opp i mange mindre biter, slik at main og testResept var oversiktlige. Jeg benyttet ArrayList for � holde p� testverdier. resultatet av testene ble skrevet ut til terminalen.

Lege:
Denne ble ikke s� stor. Jeg lagde FastLege som arvet fra Lege og benyttet seg av grensesnittet Kommuneavtalen.

Integrasjonstest:
Her testet jeg ikke mot fasitvariabler, da dette allrede var gjort i de to foreg�ende testene. I stedet lagde jeg en instans av alle subklasser, (og Lege). Deretter testet jeg om alle metoder fungerte. Da mange metoder var getters syns jeg det var greit � skrive ut resultatet til terminalen.

Datastrukturtegning:
I denne datastrukturtegningen inkluderte jeg ikke metoder til objektene, kun verdier. Jeg syntes det ble veldig mye rot med alle pilene som indikerte hvordan objektene var lenket sammen, s� jeg pr�vde � simplifisere med � ikke ta med metoder. Likevel ble det en del rot =/  Kanskje det hadde v�rt lurere � sette det opp p� en annen m�te? Indikerer statiske int med en stiplet Integer-objekt boks. Er dette en fornuftig m�te � representere static int? 

