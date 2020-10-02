////////////////// class DobbeltLenketListe //////////////////////////////

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        // Parameter Kontroll
        Objects.requireNonNull(a,"Ugyldig Array: null");

        Node<T> curr; // Node som beholder noden vi er på
        for(T element : a){
            if(element != null){
                if (hode == null) {
                    hode = hale = new Node<>(element);
                } else {
                    curr = new Node<>(element);
                    hale.neste = curr;
                    curr.forrige = hale;
                    hale = curr;
                }
                antall++;
            }
        }
    }

    public Liste<T> subliste(int fra, int til){
        // Parameter Kontoll
        fratilKontroll(antall,fra,til);

        //lager en sub_liste:
        Liste<T> subListe = new DobbeltLenketListe<>();
        if(fra == til) return subListe;
        //henter noden som er på indeks = fra:
        Node<T> curr = finnNode(fra);
        //går fra og med (fra) til (til) og henter verdien fra en og en node for å legge dem inn i subliste:
        while(fra<til){
            subListe.leggInn(curr.verdi);
            curr = curr.neste; //hopper til neste node
            fra++;
        }
        return subListe;
    }
    //helpe metode:
    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }
    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        // Parameter Kontroll
        Objects.requireNonNull(verdi, "Verdien kan ikke være null");


        // Hvis listen er tomt
        if(hode==null){
            hode = new Node<>(verdi);
            hale = hode;
        }
        // Hvis listen har et element eller flere:
        else{
            Node<T> nyHale = new Node<>(verdi);
            nyHale.forrige = hale;
            hale.neste = nyHale;
            hale = nyHale;
            hale.neste=null;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        //parameter kontroll:
        Objects.requireNonNull(verdi,"Verdi kan ikke være null!");
        if(indeks<0||indeks>=antall+1) throw new IndexOutOfBoundsException("index:" + indeks);
        //int nyIndeks = (indeks==antall)?indeks-1:indeks;


        //lager en ny node med verdi -> T verdi:
        Node<T> nyNode = new Node<>(verdi);

        //Listen har et element eller tom:
        boolean erLagtTil = false;
        if(hode==null) {
            erLagtTil = leggInn(verdi);
        }
        if(!erLagtTil) {
            //for indeks i start posisjonen:
            if (indeks == 0) {
                nyNode.forrige = null;
                nyNode.neste = hode;
                hode.forrige = nyNode;
                hode = nyNode;
            }
            //hvis index <= antall:
            else{
                Node<T> current = finnNode(indeks-1);
                if(current==hale){
                    nyNode.forrige = hale;
                    nyNode.neste = null;
                    hale.neste = nyNode;
                    hale = nyNode;
                }else{
                    nyNode.neste=current.neste;
                    nyNode.forrige=current;
                    current.neste.forrige=nyNode;
                    current.neste=nyNode;
                }
            }
            antall++;
            endringer++;
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi)!=-1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    private Node<T> finnNode(int indeks) {
        int grense = antall / 2;
        Node<T> returnVerdi = null;

        if(indeks == 0){
            returnVerdi = hode;
        } else if(indeks == antall - 1){
            returnVerdi = hale;
        } else {
            if(indeks <= grense){
                int i = 0;
                Node<T> curr = hode;
                while(i <= grense){
                    if(i == indeks){
                        returnVerdi = curr;
                    }
                    curr = curr.neste;
                    i++;
                }
            } else {
                int i = antall - 1;
                Node<T> curr = hale;
                while(i > grense){
                    if(i == indeks){
                        returnVerdi = curr;
                    }
                    curr = curr.forrige;
                    i--;
                }
            }
        }
        return returnVerdi;
    }

    @Override
    public int indeksTil(T verdi) {
       if(verdi == null) return -1;
       Node<T> curr = hode;
       int i =0;
       while(i<antall){
           if(curr.verdi.equals(verdi)){
               return i;
           }
           curr = curr.neste;
           i++;
       }
       return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        // Parameter Kontroll
        Objects.requireNonNull(nyverdi,"Error: Ny er lik null!");
        indeksKontroll(indeks,false);

        Node<T> gammelNode = finnNode(indeks);
        T gammelVerdig = gammelNode.verdi;
        gammelNode.verdi = nyverdi;
        endringer++;
        return gammelVerdig;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null) return false;
        if(hode == null) return false;

        // Hvis vi fjerner fra første plassen
        if(verdi.equals(hode.verdi) && hode.neste != null){
            hode = hode.neste;
            hode.forrige = null;
            antall--;
            endringer++;
            return true;
        }

        // Hvis det finnes kun et element
        else if(antall == 1){
            hode = null;
            hale = null;
            antall--;
            endringer++;
            return true;
        }

        // Hvis vi fjerner noden i siste plassen
        else if(verdi.equals(hale.verdi)){
            hale = hale.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }

        // Hvis vi fjerner noden mellom to noder
        else {
            int toRemove = indeksTil(verdi);
            Node<T> p = finnNode(toRemove - 1);
            Node<T> r = finnNode(toRemove + 1);
            if(p != null && r != null){
                p.neste = r;
                r.forrige = p;
                antall--;
                endringer++;
                return true;
            }
        }

        return false;
    }

    @Override
    public T fjern(int indeks) {
        // Parameter Kontroll
        indeksKontroll(indeks,false);

        T toRemove = hent(indeks);
        fjern(toRemove);
        return toRemove;
    }

    @Override
    public void nullstill() {

        Node<T> current = hode;
        while(current != null){
            current = null;
            current = hode.neste;
            hode = current;
            endringer++;
        }
        antall = 0;

    }

    @Override
    public String toString() {
        StringJoiner sj=new StringJoiner(", ","[","]");
        Node<T> current = hode;
        while(current!=null){
            sj.add(current.verdi.toString());
            current = current.neste;
        }
        return sj.toString();
    }

    public String omvendtString() {
        StringJoiner sj=new StringJoiner(", ","[","]");
        Node<T> current = hale;
        while(current!=null){
            sj.add(current.verdi.toString());
            current = current.forrige;
        }
        return sj.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if(endringer != iteratorendringer)
                throw new ConcurrentModificationException("Endringer er ikke oppdatert riktig");
            if(!hasNext())
                throw new NoSuchElementException("Listen har nådd siste noden");

            fjernOK = true;
            T returVerdi = denne.verdi;
            denne = denne.neste;
            return returVerdi;
        }

        @Override
        public void remove(){
            if(tom() || !fjernOK)
                throw new IllegalStateException("The list i empty");
            if(endringer!=iteratorendringer)
                throw new ConcurrentModificationException("Changes are"+
                        " not equal.");

            fjernOK = false;
            //listen har et element:
            if (hode == hale) {
                hode = hale = null;
            }
            //denne har gaatt over hale:
            else if (denne==null) {
                hale.forrige.neste = null;
                hale = hale.forrige;
            }
            //hvis pekeren er hode eller hode sin neste node:
            else if (denne == hode.neste) {
                denne.forrige = null;
                hode = denne;
            }else if(denne==hode){
                hode = denne.neste;
                denne.neste.forrige=null;
                denne = null;

            }
            //ellers saa hode er ingen av tilfellene over og denne peker paa et
            //tilfeldig node i midten:
            else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }

            iteratorendringer++;
            endringer++;
            antall--;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


