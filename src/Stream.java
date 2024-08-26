import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {

    public static void main(String[] args) {

        // 1-création d'un stream
        // TODO : indiquer les exemples

        // 2-Opérations intermédiaires
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<String> stringList = new ArrayList<>(Arrays.asList("one", "two", "three", "four"));
        // map renvoie un flux constitué des résultats de l'application d'une fonction
        integerList.stream().map(x -> x+1).forEach(System.out::println);

        // Remarque - mapToInt renvoie un stream de int (impossible de collecter le résultat dans une liste) Idem pour mapToLong, mapToDouble
        // IntSummaryStatistics stats = integerList.stream().mapToInt(x->x).summaryStatistics();

        // filter renvoie une sélection en fonction d'un prédicat
        integerList.stream().filter(x -> x > 1 && x<4).forEach(System.out::println);
        System.out.println("count == 3 = " +
                        stringList.stream()
                                .map(String::length)
                                .filter(length -> length == 3)
                                .count()
                );

        // sorter est utilisée pour trier un flux
        integerList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // flatMap retourne un objet à plat
        // exemple avec un set de String
        Developer o1 = new Developer();
        // [ name : String,
        //   book : [name : String, name : String, name : String]]

        o1.setName("nga");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> developerList = new ArrayList<>();
        developerList.add(o1);
        developerList.add(o2);

        System.out.println(
                developerList.stream()
                        //.map(x -> x.getBook())
                        .flatMap(x -> x.getBook().stream())                 //  Stream<String>
                        .filter(x -> !x.toLowerCase().contains("python"))   //  filter python book
                        .collect(Collectors.toSet())
        );

        // exemple avec un objet imbriqué
        List<Order> orders = Order.findAll();

        // sum the line items' total amount
        BigDecimal sumOfLineItems = orders.stream()
                .flatMap(order -> order.getLineItems().stream())    //  Stream<LineItem>
                .map(LineItem::getTotal)                       //  Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);          //  reduce to sum all

        // MapMulti pour valider des données corrompues
        List<String> strings = List.of("1", " ", "2", "3 ", "", "3");
        List<Integer> ints =
                strings.stream()
                        .filter(s -> s != null && !s.trim().isEmpty()) // Filtre pour ne garder que les chaînes non nulles et non vides
                        .<Integer>mapMulti((string, consumer) -> {
                            try {
                                consumer.accept(Integer.parseInt(string));
                            } catch (NumberFormatException ignored) {
                            }
                        })
                        .toList();
        System.out.println(ints);

        // retirer les doublons
        System.out.println(strings.stream().map(String::trim).filter(s -> !s.isEmpty()).distinct().toList());

        // trier un stream
        System.out.println(strings.stream().map(String::trim).filter(s -> !s.isEmpty()).distinct().sorted(Comparator.reverseOrder()).toList());

        // skip et limit
        List<Integer> ints2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> result =
                ints2.stream()
                        .skip(2)
                        .limit(5)
                        .toList();

        System.out.println("result = " + result);

        // Concaténer des listes
        List<Integer> list0 = List.of(1, 2, 3);
        List<Integer> list1 = List.of(4, 5, 6);
        List<Integer> list2 = List.of(7, 8, 9);

        // 1st pattern: concat
        List<Integer> concat =
                java.util.stream.Stream.concat(list0.stream(), list1.stream())
                        .toList();

        // 2nd pattern: flatMap
        List<Integer> flatMap =
                java.util.stream.Stream.of(list0.stream(), list1.stream(), list2.stream())
                        .flatMap(Function.identity())
                        .toList();

        System.out.println("concat  = " + concat);
        System.out.println("flatMap = " + flatMap);

        // 3- Opérations terminales
        // toList est utilisé pour renvoyer le résultat des opérations intermédiaires effetuées sur le flux en liste
        List<Integer> listPair = integerList.stream().filter(x -> x % 2 == 0).toList();

        // reduce est utilisée pour réduire les éléments d'un flux
        System.out.println(integerList.stream().reduce(0, Integer::sum));

        // collect est utilisé pour réunir des éléments dans une collection
        String joined =
                IntStream.range(0, 10)
                        .boxed()
                        .map(Object::toString)
                        .collect(Collectors.joining());
        System.out.println(joined);

        String joined2 =
                IntStream.range(0, 10)
                        .boxed()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
        System.out.println(joined2);

        Map<Boolean, List<String>> map =
                strings.stream()
                        .collect(Collectors.partitioningBy(s -> s.length() > 4));
        System.out.println(map);

        // grouping by est utilisé pour réunir en fonction d'un prédicat
        System.out.println(
                stringList.stream()
                        .collect(Collectors.groupingBy(String::length))
        );

        // counting + collector
        Collection<String> stringList1 =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        Map<Integer, Long> map1 =
                stringList1.stream()
                        .collect(
                                Collectors.groupingBy(
                                        String::length,
                                        Collectors.counting()));

        map1.forEach((key, value) -> System.out.println(key + " :: " + value));

        // + joining
        Map<Integer, String> map2 =
                stringList1.stream()
                        .collect(
                                Collectors.groupingBy(
                                        String::length,
                                        Collectors.joining(", ")));
        map2.forEach((key, value) -> System.out.println(key + " :: " + value));

        // + toMap
        Map<Integer, String> map3 =
                stringList1.stream()
                        .collect(
                                Collectors.toMap(
                                        element -> element.length(),
                                        element -> element,
                                        (element1, element2) -> element1 + ", " + element2));

        System.out.println("Map avec peek");
        map3.forEach((key, value) -> System.out.println(key + " :: " + value));

        // 4- debugging stream
        List<String> result2 =
                strings.stream()
                        .peek(s -> System.out.println("Starting with = " + s))
                        .filter(s -> s.startsWith("t"))
                        .peek(s -> System.out.println("Filtered = " + s))
                        .map(String::toUpperCase)
                        .peek(s -> System.out.println("Mapped = " + s))
                        .toList();
    }



}
