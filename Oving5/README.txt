Innlevering av Oblig 5, Hallstein Skj�lsvik

Leverer F�lgende filer:
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

Legger ogs� ved eksempellabyrintene:
-1.in
-2.in
-3.in
-4.in
-5.in
-6.in
-7-in

Veldig g�y Oblig! Synes det meste gikk bra denne gangen. Tror jeg fikk l�st alle oppgavene. Jeg tok en avgj�relse som jeg ikke fant forankret i oppgaveteksten, men jeg syns det gir mening at man ikke kan starte � finne en utvei fra en sort rute, da disse er vegger. Derfor la jeg inn at man m� pr�ve igjen dersom man �nsker � starte fra en sort rute. Dersom dette gj�r at oppgaven min ikke kommer gjennom tester er det bare � kommentere ut linje 44 til 54, og kommentere inn linje 57 til 61. 

Litt uvant � tenke p� at en string blir laget p� nytt hver gang man endrer p� den. Er kallet "naborute.gaa(utvei);" likt som "naborute.gaa(new String(utvei));"? Siden man dytter referanse til en ny streng, i stedet for den allerede eksisterende utvei-strengen. 

Jeg ble litt skuffet da jeg fant ut at String.comparaTo(String) sammenlikner alfabetisk. Jeg hadde veldig lyst til � benytte SortertLenkeliste for � sorter utveier etter lengde. Jeg endte opp med � benytte en bublesort som hjelpemetode for � f� sortert utveiene. 

Jeg endte opp med � finne 108 veier ut fra 3,3 i labyrint 4. Med mine regler f�r man ikke lov til � starte i 4,4 da dette er en vegg. 

