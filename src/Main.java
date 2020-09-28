import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Integer[] arr_tom = {};
        Integer[] arr_int = {1,2,3};
        String[] arr_str = {"Ole", null, "Per", "Kari", null};

        Liste<Integer> liste_tom = new DobbeltLenketListe<>(arr_tom);
        DobbeltLenketListe<Integer> liste_int = new DobbeltLenketListe<>(arr_int);
        Liste<String> liste_str = new DobbeltLenketListe<>(arr_str);
        DobbeltLenketListe<Integer> liste_enEl=new DobbeltLenketListe<>(new Integer[]{1});



        //Temp Test:
        System.out.println(Arrays.toString(arr_int));
        System.out.println("ToString fra dListe: " + liste_int.toString());
        System.out.println("ToString fra dListe: " + liste_int.omvendtString());

        System.out.println("Tom array: " + liste_tom.toString());
        System.out.println("Et element: " + liste_enEl.toString());
        ////////// METODE SJEKK: antall() og tom() /////////////////////////////////////////////////////////////////////
        System.out.println(liste_tom.antall() + " " + liste_tom.tom());
        System.out.println(liste_str.antall() + " " + liste_str.tom());

    }
}
