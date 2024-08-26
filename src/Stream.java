import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        // 1- map renvoie un flux constitué des résultats de l'application d'une fonction
        list.stream().map(x -> x+1).forEach(System.out::println);

        // 2- filter renvoie

    }

}
