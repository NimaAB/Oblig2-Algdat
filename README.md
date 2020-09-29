# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Nima Abdollahi, S341890, s341890@oslomet.no
* ...

Vi har brukt git til å dokumentere arbeidet vårt. Vi har <16> commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling: 
* Vi har i fellesskap løst alle oppgavene fra og med 1 til og med 8. 

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: 
    ```
    Klassen DobeltLenketListe har attributten antall som vi bruker 
    for å returene en boolean i tom() metoden slik "if(antall == 0) return true;"
    og vi bruker samme attributt for å returne en int antall `return antall;` i metoden antall().

    Klassen DobbeltLenketListe(Array) : I utgangspunktet er hode og hale lik null.
    Først har vi et for-loop som itererer arrayet.
    første element blir satt som hode og hale og hvis det finnes flere elemeter,
    blir de koblet til hverandre hvor siste element blir lik hale.
    ```
* Oppgave 2: 
    ```
    **2a)** toString() metoden er løst slik at den skal passe om listen er tom.
    Etter har vi en StringBuilder objekt som skal legges til verdier i hver iterasjon av while-løkken:
    "current=hode; while(current != hale)" det vil si at vi har en pekker som pekker på hoden 
    av listen og oppdatrers til dens neste node for hver iterasjon frem til hale noden. 
    Og for omvendtString() vi bruker samme logikk, men motsatt vei. fra hale til hode.
    
    **2b)** for legginn(T verdi) vi har først parameter sjekk om "verdi != null" etter på har vi to tilfeller:
    hvis listen er tom, da sier vi at hode og hale pekkere er node(verdi). Den andre tilfillen er om listen har en eller flere noder,
    da sier vi nyNode sin netse er null og sin forrige er hale, hale sin neste er nyNode og hale nå er nyNode.
    ```
    
* Oppgave 4:
	``` 
	Løsningen til indeksTil(verdi) begynner med å sjekke parameteren T verdi
    hvis det null da returnerer vi -1 som et tegn på at det ikke finnes i listen. 
    Etter det begynner vi med å lage en peker på hode og går gjennom noden 
    ved å kalle på pekeren sin neste node for hver iterasjon.
	Og i hver iterasjon sjekker vi om pekeren sin verdi er lik parameter verdien
    hvis det returner vi vår teller variabel i.
    hvis løkken  slutter uten resultat da returnerer den -1. 
	Og metoden "boolean inneholder(T verdi)" sjekker om indeksTil(verdi) er ikke lik -1.
	```
	
* Oppgave 6:
	``` 
	boolean fjern(T verdi) metoden begynner med en parameter sjekk om verdi er ikke null og listen er ikke tom hvis det returnerer false.
	Metoden sjekker tre tilfeller:
		1. Verdien er lik hode.verdi og listen er ikke tom: peker flytes fra hode.
		2. Listen har en node: både hode og hale blir null.
		3. Verdien er lik hale.verdi: flyttes peker fra hale til null og den forrige node er hale.
		4. Ellers(sletter vi en node mellom to andre noder): 
        finner indeksen til denne verdien vi skal slette. Bruker indeksen for å finne en bak og en foran denne noden
        med denne verdien. Og hvis ingen av dem er null så flytter vi neste og forrige pekere over denne noden i midten. 
	
	T fjern(int indeks): parameter kontroll, etterpå  hentes det verdien til noden 
    i indeks ved hjelp av hent(indeks) og lagres i en variabel (toRemove) til slutt bruker
    fjern(toRemove) og returnerer toRemove.
	```

* Oppgave 8:
     ```
     Vi har følget etter instruksjonen fra oppgaven.
     ```





