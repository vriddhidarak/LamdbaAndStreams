package exercises;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Collectors1 {
    public static void main(String[] args) {
        Set<Integer> evenNumbers =
                IntStream.range(0, 10)
                        .map(number -> number / 2)
                        .boxed()
                        .collect(Collectors.toSet());
        System.out.println("evenNumbers = " + evenNumbers);


        Collection<String> strings = List.of("one", "two", "three");

        long count = strings.stream().count();
        long countWithACollector = strings.stream().collect(counting());

        System.out.println("count = " + count);
        System.out.println("countWithACollector = " + countWithACollector);


        String joined =
                IntStream.range(0, 10)
                        .boxed()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "[", "]"));

        System.out.println("joined = " + joined);


        Collection<String> strings1 =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        Map<Boolean, List<String>> map =
                strings1.stream()
                        .collect(Collectors.partitioningBy(s -> s.length() > 4));

        map.forEach((key, value) -> System.out.println(key + " :: " + value));


        Collection<String> strings2 =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        Map<Integer, List<String>> map1 =
                strings2.stream()
                        .collect(Collectors.groupingBy(String::length));
        Map<Integer, Long> histogram = strings2.stream()
                        .collect(groupingBy(String::length,counting()));
        System.out.println("histogram = " + histogram);
        map1.forEach((key, value) -> System.out.println(key + " :: " + value));




        Map<Integer, String> map4 =
                strings1.stream()
                        .collect(
                                Collectors.toMap(
                                        element -> element.length(),
                                        element -> element,
                                        (element1, element2) -> element1 + ", " + element2));

        map4.forEach((key, value) -> System.out.println(key + " :: " + value));


        Map.Entry<Integer, Long> maxValue =
                histogram.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .orElseThrow();

        System.out.println("maxValue = " + maxValue);

        record NumberOfLength(int length, long number) {

            static NumberOfLength fromEntry(Map.Entry<Integer, Long> entry) {
                return new NumberOfLength(entry.getKey(), entry.getValue());
            }

            static Comparator<NumberOfLength> comparingByLength() {
                return Comparator.comparing(NumberOfLength::length);
            }
        }

        NumberOfLength maxNumberOfLength =
                histogram.entrySet().stream()
                        .map(NumberOfLength::fromEntry)
                        .max(NumberOfLength.comparingByLength())
                        .orElseThrow();

        System.out.println("maxNumberOfLength = " + maxNumberOfLength);



    }
}
