package no.oslomet.cs.algdat;
public class Main {
    public static void main(String[] args){
        Integer[] arr_tom = {};
        Integer[] arr_int = {1,2,3};
        Liste<Integer> liste_tom = new DobbeltLenketListe<>(arr_tom);
        Liste<Integer> liste_int = new DobbeltLenketListe<>(arr_int);

        ////////// METODE SJEKK: antall() og tom() /////////////////////////////////////////////////////////////////////
        System.out.println("Liste tom: Antall = " + liste_tom.antall());
        System.out.println("Liste int: Antall = " + liste_int.antall());

    }
}
