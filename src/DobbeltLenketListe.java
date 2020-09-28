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
        Objects.requireNonNull(verdi, "Verdien kan ikke være null");
        boolean returVerdi = false;

        // if listen er tomt
        if(tom()){
            hode = new Node<>(verdi);
            hale = hode;
            antall++;
            returVerdi = true;
        }
        // if listen har et element
        else if(antall == 1){
            hale = new Node<>(verdi);
            hode.neste = hale;
            hale.forrige = hode;
            antall++;
            returVerdi = true;
        } else {
            Node<T> nyHale = new Node<>(verdi);
            nyHale.forrige = hale;
            hale.neste = nyHale;
            hale = nyHale;
            antall++;
            returVerdi = true;
        }

        return returVerdi;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
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

    private Node<T> finnNode(int indeks){
        if(indeks < 0 || indeks >= antall) throw new IndexOutOfBoundsException("Arrayet av størrelse " + antall + " med en indeks: " + indeks);
        int grense = antall / 2;

        Node<T> returnVerdi = null;
        if(indeks == 0){
            returnVerdi = hode;
        } else if(indeks == antall - 1){
            returnVerdi = hale;
        } else{
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
            }else{
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
       if(verdi==null) return -1;
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
        Objects.requireNonNull(nyverdi,"Error: Ny er lik null!");
        Node<T> gammelNode = finnNode(indeks);
        T gammelVerdig = gammelNode.verdi;
        gammelNode.verdi = nyverdi;
        endringer++;
        return gammelVerdig;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if(hode==null){
            return "[]";
        }
        if(hale==hode){
            return "["+hode.verdi+"]";
        }
        StringBuilder listStr=new StringBuilder("[");
        Node<T> current = hode;
        while(current!=hale){
            listStr.append(current.verdi).append(", ");
            current = current.neste;
        }
        listStr.append(hale.verdi).append("]");
        return listStr.toString();
    }

    public String omvendtString() {
        if(hode ==  hale || hale == null){
            return toString();
        }
        StringBuilder listStr=new StringBuilder("[");
        Node<T> current = hale;
        while(current!=hode){
            listStr.append(current.verdi).append(", ");
            current = current.forrige;
        }
        listStr.append(hode.verdi).append("]");
        return listStr.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


