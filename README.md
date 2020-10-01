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
* Glaysa Fernandez, s344047, s344047@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt. Vi har **59** commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

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
    **2a)** toString() metoden er løst slik: Den begynner med å lage en pekker curr fra hode noden, etter på 
    instansierer den en StringJoiner objekt med delimiter", " og strarttegn "[" og sluttegn "]".
    Etter det har vi en while-løkke som fortsetter frem til curr pukker på null. dvs. etter hale.
    Og for hver iterasjon legges det en string av node vedien og så peker den på neste node ved hjelp av neste attributten.
    Og for omvendtString() vi bruker samme logikk, men motsatt vei. fra hale til null.
    
    **2b)** for legginn(T verdi) vi har først parameter sjekk om "verdi != null" etter på har vi to tilfeller:
    hvis listen er tom, da sier vi at hode og hale pekkere er node(verdi). Den andre tilfillen er om listen har en eller flere noder,
    da sier vi nyNode sin netse er null og sin forrige er hale, hale sin neste er nyNode og hale nå er nyNode.
    ```
* Oppgave 3:
    ```
    3a) finnNode(indeks), hent(indeks), oppdater(indeks, nyVerdi)
    - Vi har først implementert finnNode(indeks) hvor vi først sjekker om indeks er lik 0 eller om indeks er lik antall - 1. Hvis en av de to er sant, returnerer vi hode eller hale.
    - Hvis verken av de to er sant, kjører else-blokken. Hvis indeks er mindre enn antall / 2, itererer vi fra hode, men hvis det  er større,  itererer vi fra hale. 
    - Vi har en "Node<T> curr" som hopper over nodene og samtidig en variabel "i" som øker/synker så lenge det er ikke lik indeks. 
      Hvis "i" er lik indeks, returnerer vi noden "curr"
    - "hent(indeks)" - vi har brukt "finnNode(indeks)" her, men den returener verdien istedenfor noden.
    - "oppdater(indeks,nyVerdi)" - vi har først letet etter noden vi ønsker å oppdatere ved hjelp av "finnNode(indeks)"
      og lagret nodens verdi i variablen "gammelVerdi". Vi satt noden sin verdi lik "nyVerdi" og returnert "gammelVerdi"
      Her øker "endringer" variablen.
      
    3b) subliste(fra,til)
    - Vi har først implementert "fraTilKontroll(fra, til)" som sjekker parameterne til subliste(fra,til).
    - Vi har deklarert en tom "liste". Hvis "fra" er lik "til" returnerer vi en tom liste.
    - Ved hjelp av "finnNode(fra)" fant vi noden til "fra" og brukte en while-loop som itererer gjennom listen så lenge "fra" er 
      mindre enn "til". Vi har en "Node<T> curr" satt lik noden til "fra" som hopper over nodene og samtidig ved hjelp av "leggInn(curr.verdi)" lagret vi verdiene til "liste". Til slutt returnerte vi "liste".
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
* Oppgave 5: 
	```
	- leggInn(indeks,verdi) begynner med en parameter kontroll ved hjelp av Objects.requireNonNull(verdi,melding) og en if-setning som sjekker om indeks er mindre enn 0 eller indeks er større eller lik "antall".
	- Vi har deklarert en ny node som har verdien lik som i parameteren kalt "nyNode".
	- Vi har tatt hensyn på 4 tilfeller. 1) Listen er tom. 2) Vi legger "nyNode" bakerst 3) Vi legger "nyNode" forrest og 4) Vi legger "nyNode" mellom to noder.
		1. Vi kaller leggInn(verdi) metoden i denne tilfelle.
		2. Vi setter "nyNode" sin forrige peker lik hale og hale sin neste peker lik "nyNode". Etter det setter vi "nyNode" lik hale.
		3. Her er det samme logikk som i tilfelle 3, forskjellen er at det er hode vi jobber med og at pekere har annerledes tilordninger.
		4. Her finner vi noden vi skal flytte slik at "nyNode" får plass ved hjelp av finnNode(indeks) som vi har lagret i node variablen "current". Noden "current" flytter seg foran og får en ny indeks, mens "nyNode" får "current" sin plass. Den har vi gjort ved å sette pekere til nodene riktig.
	- Her øker variablene "antall" og "endringer".
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
* Oppgave 7:
	```
	- I nullstill() metoden har vi en node kalt "current" satt lik hode.
	- Vi har brukt en while-loop som itererer listen så lenge hode er ikke null. Så lenge hode er ikke null, hode blir satt lik sin neste node. I denne metoden øker vi endringer og antall synker.
	```
* Oppgave 8:
     ```
     Vi har følget etter instruksjonen fra oppgaven.
     ```





