package playground.MyLists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinElement {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();

        arr.add("Hello");
        arr.add("Hello");
        arr.add("dsadad");
        arr.add("4jmk");
        arr.add("r4mll3");
        arr.add("p23m0");

        System.out.println(findAllDuplicates(arr));




    }

    public static String findAllDuplicates(List<String> arr) {
        Map<String, Integer> map = new HashMap<>();

        arr.forEach(el -> {
            map.merge(el, 1, (oldValue, newValue) -> oldValue + newValue);
        });

        return map.entrySet().stream().map(entry -> {
            if (entry.getValue() > 1) return "Key is " + entry.getKey() + " is repeated " + entry.getValue() + " times \n";
            else return null;
        }).filter(Objects::nonNull)
                .collect(Collectors.joining());
    }

    public static String reverse(char[] string, String newString) {
        StringBuilder newStringBuilder = new StringBuilder(newString);

        for (int i = string.length - 1; i >= 0; i--) {
            newStringBuilder.append(string[i]);
        }
        return newStringBuilder.toString();
    }

    public static void findDublicate(int index, int[] arr) {
        if (arr.length < index + 1) System.out.println("No dublicates has been found");
        else if (arr[index] != index + 1) System.out.println(arr[index]);
        else findDublicate(index + 1, arr);
    }
}
