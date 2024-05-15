import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args){

        // 1- factorielle
        System.out.println("1- factorielle de 3 : " + IntStream.range(1,4).reduce(1, (a, b)-> a*b));

        // 2- fibonacci
        System.out.println("2- fibonacci : " + (IntStream.range(1,4).sum()));

        // 3- fizz buzz 50
        System.out.println("3- fizz buzz 50 : ");
        IntStream.range(1, 51).forEach(x -> System.out.println(x + " : " + (x % 2 == 0 ? "Fizz" : x % 3 == 0 ? "Buzz" : " ")));

        // 4- search letters sequence in string
        String citation = "Je suis adroit de la main droite et gauche de la main gauche";
        System.out.println("4- search sequence in string : \"oi\"");
        Arrays.stream(citation.split(" "))
                .forEach( x -> {if (x.contains("oi")) System.out.println(x);});

        // variante : renvoyer une liste
        System.out.println("Variante avec une liste : " + Arrays.stream(citation.split(" ")).filter(x-> x.contains("oi")).toList());

        // 5- search letters sequence in string
        System.out.println("5- search letters in string : 'o' or 'i' ");
        Arrays.stream(citation.split(" "))
                .distinct()
                .forEach( x -> {if (x.contains("o") || x.contains("i")) System.out.println(x);});

        // variante : renvoyer une liste
        System.out.println("Variante avec une liste : " + Arrays.stream(citation.split(" ")).filter(x-> x.contains("oi")).toList());

        // fast comparison -> écart le moins important entre différents nombres
        System.out.println("6 - fast comparison (no double loop) : différence la moins élevés entre plusieurs nombres");
        Integer[] arrToCompare = {4, 5, 8, 9};
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < arrToCompare.length ; i++) {
            result.add(Math.abs(arrToCompare[i] - arrToCompare[i -1]));
        }
        System.out.println("Smaller= " + Collections.min(result));


    }
}