Innlevering av Oblig 5, Hallstein Skjølsvik

Leverer Følgende filer:
-Oving5.java
-Labyrint.java
-Rute.java
-SortRute.java
-HvitRute.java
-Aapning.java

-Liste.java
-Lenkeliste.java
-Node.java
-LenkelisteIterator.java
-UgyldigListeIndeks.java

-Klassediagram.PNG
-Klassehierarki.PNG
-README.txt

Legger også ved eksempellabyrintene:
-1.in
-2.in
-3.in
-4.in
-5.in
-6.in
-7-in

Veldig gøy Oblig! Synes det meste gikk bra denne gangen. Tror jeg fikk løst alle oppgavene. Jeg tok en avgjørelse som jeg ikke fant forankret i oppgaveteksten, men jeg syns det gir mening at man ikke kan starte å finne en utvei fra en sort rute, da disse er vegger. Derfor la jeg inn at man må prøve igjen dersom man ønsker å starte fra en sort rute. Dersom dette gjør at oppgaven min ikke kommer gjennom tester er det bare å kommentere ut linje 44 til 54, og kommentere inn linje 57 til 61. 

Litt uvant å tenke på at en string blir laget på nytt hver gang man endrer på den. Er kallet "naborute.gaa(utvei);" likt som "naborute.gaa(new String(utvei));"? Siden man dytter referanse til en ny streng, i stedet for den allerede eksisterende utvei-strengen. 

Jeg ble litt skuffet da jeg fant ut at String.comparaTo(String) sammenlikner alfabetisk. Jeg hadde veldig lyst til å benytte SortertLenkeliste for å sorter utveier etter lengde. Jeg endte opp med å benytte en bublesort som hjelpemetode for å få sortert utveiene. 

Jeg endte opp med å finne 108 veier ut fra 3,3 i labyrint 4. Med mine regler får man ikke lov til å starte i 4,4 da dette er en vegg. 

