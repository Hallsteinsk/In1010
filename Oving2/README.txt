Levering av Øving2 i IN1010. Hallstein Skjølsvik
Filer som er inkludert:
-Legemiddel.java 	-LegemiddelA.java	-LegemiddelB.java	-LegemiddelC.java
-Resept.java		-Hvitresept.java	-MillitaerResept.java	-PResept.java	-BlaaResept.java
-Lege.java		-FastLege.java		-Kommuneavtale.java
-TestLegemiddel.java	-TestResept.java	-Hovedprogram.java
-KlassehierarkiLegemiddel.png			-KlassehierarkiLege.png	
-KlassehierarkiResept.png			-Datastruktur.png	
-Readme.txt


Her har jeg prøvd å løse oppgaven etter beste evne. Testprogrammene mine ble litt store, og inneholder en del støtteprosedyrer. Er det lurt å ha så mange prosedyrer? Jeg innså at mye av testkoden kunne blitt gjenbrukt hadde jeg strukturert det på en annen måte. Er det vanlig å opprette testobjekter, som man kan kalle i større integrasjonstester? 

Her er noen valg jeg har gjort:
Legemiddel:
Alle legemiddler deler en statisk int som inneholder totalt antall legemiddler. Denne økes i konstruktor, og benyttes som ID da denne er unik. 

Test av legemiddler: 
Her lagde jeg en testprosdeyre som testet om alle verider til et legemiddel samsvarte med det jeg mente det burde være, og skrev resultatet ut i terminal. 

Resept:
Her prøvde jeg å dele opp metoder mest mulig mellom superklasser og underklasser. På den måten ble det ikke så mye ny informasjon i en subklasse. Det var en del mer behagelig å tenke på abstrakte metoder, for da ble det mer felles metoder for super og sub. 

Test av Resept.
Her tester jeg alle variablene til en resept og alle variablene til legemidlet som resepten peker til. De testprosedyrene ble litt massive, så jeg prøvde å dele det opp i mange mindre biter, slik at main og testResept var oversiktlige. Jeg benyttet ArrayList for å holde på testverdier. resultatet av testene ble skrevet ut til terminalen.

Lege:
Denne ble ikke så stor. Jeg lagde FastLege som arvet fra Lege og benyttet seg av grensesnittet Kommuneavtalen.

Integrasjonstest:
Her testet jeg ikke mot fasitvariabler, da dette allrede var gjort i de to foregående testene. I stedet lagde jeg en instans av alle subklasser, (og Lege). Deretter testet jeg om alle metoder fungerte. Da mange metoder var getters syns jeg det var greit å skrive ut resultatet til terminalen.

Datastrukturtegning:
I denne datastrukturtegningen inkluderte jeg ikke metoder til objektene, kun verdier. Jeg syntes det ble veldig mye rot med alle pilene som indikerte hvordan objektene var lenket sammen, så jeg prøvde å simplifisere med å ikke ta med metoder. Likevel ble det en del rot =/  Kanskje det hadde vært lurere å sette det opp på en annen måte? Indikerer statiske int med en stiplet Integer-objekt boks. Er dette en fornuftig måte å representere static int? 

