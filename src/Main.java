import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Integer[] arr_tom = {};
        Integer[] arr_int = {1,2,3};
        String[] arr_str = {"Ole", null, "Per", "Kari", null};

        DobbeltLenketListe<Integer> liste_tom = new DobbeltLenketListe<>(arr_tom);
        DobbeltLenketListe<Integer> liste_int = new DobbeltLenketListe<>(arr_int);
        Liste<String> liste_str = new DobbeltLenketListe<>(arr_str);
        DobbeltLenketListe<Integer> liste_enEl=new DobbeltLenketListe<>(new Integer[]{1});

        //Tester toString og omvendt metoden:
        System.out.println(Arrays.toString(arr_int));
        System.out.println("ToString fra dListe: " + liste_int.toString());
        System.out.println("ToString fra dListe: " + liste_int.omvendtString());

        System.out.println("Tom array: " + liste_tom.toString());
        System.out.println("Et element: " + liste_enEl.toString());

        System.out.println("Et element omvendt: " + liste_enEl.omvendtString());
        System.out.println("tom liste: " + liste_tom.omvendtString());

        // Tester antall() og tom()
        System.out.println(liste_tom.antall() + " " + liste_tom.tom());
        System.out.println(liste_str.antall() + " " + liste_str.tom());

        // Tester fjern(indeks) og fjern(verdi)
        System.out.println("Original Array: " + liste_str.toString());

        liste_str.fjern(0);
        System.out.println("Etter fjerning av indeksen 0: " + liste_str.toString());

        liste_str.fjern("Kari");
        System.out.println("Etter fjerning av verdien Kari: " + liste_str.toString());

        // Testeter nullstill()
        System.out.println("Listen før nullstilling: " + liste_int.toString());
        liste_int.nullstill();
        System.out.println("Listen etter nullstilling: " + liste_int.toString());
        //Test for each:

        String[] navn = { "Lars" , "Anders" , "Bodil" , "Kari" , "Per" , "Berit" };
        Liste<String> liste = new DobbeltLenketListe<>(navn);
        liste.forEach(s -> System. out .print(s + " " ));
        System. out .println();
        for (String s : liste) System. out .print(s + " " );

        DobbeltLenketListe<Integer> ltest = new DobbeltLenketListe<>();

        ltest.leggInn(0, 4);  // ny verdi i tom liste
        ltest.leggInn(0, 2);  // ny verdi legges forrest
        ltest.leggInn(2, 6);  // ny verdi legges bakerst
        ltest.leggInn(1, 3);  // ny verdi nest forrest
        ltest.leggInn(3, 5);  // ny verdi nest bakerst
        ltest.leggInn(0, 1);  // ny verdi forrest
        ltest.leggInn(6, 7);

        System.out.println(ltest.toString());
        ltest.nullstill();
        System.out.println(ltest.toString());

    }
}
