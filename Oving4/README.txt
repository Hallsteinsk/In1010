Levering av Oblig 4: 
Av Hallstein Skj�lsvik.

Leverer f�lgende filer:
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

Denne �vingen var veldig g�y! Men stor, og jeg feilberegnet nok litt tiden denne gangen. Jeg fikk fullf�rt oppgave D5. S� det kan skrives ut statistikk. Jeg rakk desverre ikke filh�ndtering. Men jeg tror jeg har lagt opp metoder for � f� til dette, s� det kan sikkert implementeres en annen gang hvis jeg f�r tid. Det ble mye skriving av kode, s� jeg fikk ikke kommentert s� godt som jeg hadde h�pet, men jeg tror mye av koden skal v�re leselig. 

Jeg har gjort noen sm� endringer p� de klassene jeg har laget i oblig 2 og 3. Leger, Legemidler, Pasienter og resepter har blitt "utskrivbare" med mitt nye interface Utskrivbar. Der tvinges alle klassene til � implementere skrivUt. P� den m�ten kan man enkelt iterere over lister og skrive ut info om en instans av en klasse. 

Tanken min er denne gangen at Legesystemet gj�r mye av grovarbeidet i h�ndteringen av alle funksjonene som skal implementeres. Men jeg lagde ogs� en Menu-klasse som inneholder ren utskrift av menyer. Da slipper jeg � ha dette inne i selve systemet. Det ble mer en nok linjer der likevel. Jeg benytter switch case for � navigere inn og ut av menyer, hvor 0 tar bruker en meny tilbake, mens 9 avslutter programmet. 

Jeg beholdt Node som en ekstern klasse, og lagde LenkelisteIterator ogs� som ekstern. Jeg fikk ikke til � lage en LenkelisteIterator som ikke inneholdt typeparameter. Har jeg misforst�tt? Eller m� den ha typeparameter n�r den er ekstern. 

Vedlagt er ogs� noen hierarki-diagram og et klassediagram som jeg tror illustrerer det jeg har tenkt. Er litt usikker p� om UMLen ble helt riktig. 

