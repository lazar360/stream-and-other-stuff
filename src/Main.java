import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {

        // 1- factorielle 5
        System.out.println("1- factorielle de 5 : " + IntStream.range(1, 6).reduce(1, (a, b) -> a * b));

        // 2- somme des nombres jusqu'à 3
        System.out.println("2- somme des nombres jusqu'à 3 : " + (IntStream.range(1, 4).sum()));

        // 3- fizz buzz 50
        System.out.println("3- fizz buzz 50 : ");
        IntStream.range(1, 51).forEach(x -> System.out.println(x + " : " + (x % 2 == 0 ? "Fizz" : x % 3 == 0 ? "Buzz" : " ")));

        // variante : renvoyer une liste
        List<String> listFizz = IntStream.range(1, 100)
        .mapToObj(i -> i % 3 == 0 && i % 5 == 0 ? "FizzBuzz" :
                i % 3 == 0 ? "Fizz" :
                        i % 5 == 0 ? "Buzz" : String.valueOf(i))
        .toList();
        System.out.println(listFizz);

        // 4- search letters sequence in string
        String citation = "Je suis adroit de la main droite et gauche de la main gauche";
        System.out.println("4- search sequence in string : \"oi\"");
        Arrays.stream(citation.split(" "))
                .forEach(x -> {
                    if (x.contains("oi")) System.out.println(x);
                });

        // variante : renvoyer une liste
        System.out.println("Variante avec une liste : " + Arrays.stream(citation.split(" ")).filter(x -> x.contains("oi")).toList());
        System.out.println("Variante avec une liste et pas oi: " + Arrays.stream(citation.split(" ")).filter(x -> !x.contains("oi")).toList());

        // 5- search letters in string
        System.out.println("5- search letters in string : 'o' or 'i' ");
        Arrays.stream(citation.split(" "))
                .distinct()
                .forEach(x -> {
                    if (x.contains("o") || x.contains("i")) System.out.println(x);
                });

        // variante : renvoyer une liste
        System.out.println("Variante avec une liste : " + Arrays.stream(citation.split(" ")).filter(x -> x.contains("oi")).toList());

        // 6- fast comparison -> écart le moins important entre différents nombres
        System.out.println("6 - fast comparison (no double loop) : différence la moins élevés entre plusieurs nombres");
        Integer[] arrToCompare = {4, 5, 8, 9};
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < arrToCompare.length; i++) {
            result.add(Math.abs(arrToCompare[i] - arrToCompare[i - 1]));
        }
        System.out.println("Smaller= " + Collections.min(result));

        // 7- Equals with no null pointer exception
        System.out.println("7 - Equals with no null pointer exception");
        System.out.print("Test Ok : ");
        Arrays.stream(citation.split(" "))
                .filter(x -> Objects.equals(x, "adroit"))
                .peek(System.out::println)
                .toList();

        /*System.out.print("Test NOk : ");
        Arrays.stream(citation.split(" "))
                .filter(x -> Objects.equals(x, "droit"))
                .peek(System.out::println)
                .toList();*/

        // 8- Modify "Toto TATA Titi TUTU Tota TOTA" into "Toto TATA, Titi TUTU, Tota TOTA,"
        String foo = "Toto TATA Titi TUTU Tota TOTA";
        StringBuilder bar = new StringBuilder();
        for (int i = 1; i < foo.split(" ").length; i++) {
            if (i == 1) {
                bar.append(String.format("%s %s,", foo.split(" ")[i - 1], foo.split(" ")[i]));
            } else {
                bar.append(String.format(" %s %s,", foo.split(" ")[i - 1], foo.split(" ")[i]));
            }
        }
        System.out.println("Modify Toto TATA Titi TUTU Tota TOTA into : " +bar);

        // 9- In a list : return true if there is int > 8
        System.out.println("In a list : return true if there is int > 8 " + Arrays.stream(arrToCompare).anyMatch(num -> num > 8));

        // 10- Generate random int with no duplicate
        Random random = new Random();
        Set<Integer> setInt = new HashSet<>(9);
        while(setInt.size()<9){
        setInt.add(random.ints(0,10).findFirst().getAsInt());
        }

    }
}
