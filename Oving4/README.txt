Levering av Oblig 4: 
Av Hallstein Skjølsvik.

Leverer følgende filer:
-README.txt
-Klassediagram, Legesystem.PNG
-Klassehierarki, Lege, Pasient, Resept, Legemiddel.PNG
-Klassehierarki, Liste, Lenkeliste, Stabel, SortertLenkeliste.PNG

-Hovedprogram.java
-Legesystem.java
-Menu.java

-Utskrivbar.java
-Pasient.java

-Legemiddel.java
-LegemiddelA.java
-LegemiddelB.java
-LegemiddelC.java

-Kommuneavtale.java
-Lege.java
-Fastlege.java

-Resept.java
-BlaaResept.java
-HvitResept.java
-Presept.java
-MillitaerResept.java

-Node.java
-LenkelisteIterator.java
-Liste.java
-Lenkeliste.java
-Stabel.java
-SortertLenkeliste.java
-UgyldigListeIndex.java

Denne øvingen var veldig gøy! Men stor, og jeg feilberegnet nok litt tiden denne gangen. Jeg fikk fullført oppgave D5. Så det kan skrives ut statistikk. Jeg rakk desverre ikke filhåndtering. Men jeg tror jeg har lagt opp metoder for å få til dette, så det kan sikkert implementeres en annen gang hvis jeg får tid. Det ble mye skriving av kode, så jeg fikk ikke kommentert så godt som jeg hadde håpet, men jeg tror mye av koden skal være leselig. 

Jeg har gjort noen små endringer på de klassene jeg har laget i oblig 2 og 3. Leger, Legemidler, Pasienter og resepter har blitt "utskrivbare" med mitt nye interface Utskrivbar. Der tvinges alle klassene til å implementere skrivUt. På den måten kan man enkelt iterere over lister og skrive ut info om en instans av en klasse. 

Tanken min er denne gangen at Legesystemet gjør mye av grovarbeidet i håndteringen av alle funksjonene som skal implementeres. Men jeg lagde også en Menu-klasse som inneholder ren utskrift av menyer. Da slipper jeg å ha dette inne i selve systemet. Det ble mer en nok linjer der likevel. Jeg benytter switch case for å navigere inn og ut av menyer, hvor 0 tar bruker en meny tilbake, mens 9 avslutter programmet. 

Jeg beholdt Node som en ekstern klasse, og lagde LenkelisteIterator også som ekstern. Jeg fikk ikke til å lage en LenkelisteIterator som ikke inneholdt typeparameter. Har jeg misforstått? Eller må den ha typeparameter når den er ekstern. 

Vedlagt er også noen hierarki-diagram og et klassediagram som jeg tror illustrerer det jeg har tenkt. Er litt usikker på om UMLen ble helt riktig. 

