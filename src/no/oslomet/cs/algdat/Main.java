package no.oslomet.cs.algdat;
public class Main {
    public static void main(String[] args){
        Integer[] arr_tom = {};
        Integer[] arr_int = {1,2,3};
        String[] arr_str = {"Ole", null, "Per", "Kari", null};

        Liste<Integer> liste_tom = new DobbeltLenketListe<>(arr_tom);
        Liste<Integer> liste_int = new DobbeltLenketListe<>(arr_int);
        Liste<String> liste_str = new DobbeltLenketListe<>(arr_str);

        ////////// METODE SJEKK: antall() og tom() /////////////////////////////////////////////////////////////////////
        System.out.println(liste_tom.antall() + " " + liste_tom.tom());
        System.out.println(liste_str.antall() + " " + liste_str.tom());

    }
}
