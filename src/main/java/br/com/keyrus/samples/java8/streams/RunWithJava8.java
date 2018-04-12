package br.com.keyrus.samples.java8.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.keyrus.samples.KeyrusOffice;

/**
 * Examples with Java 8
 *
 */
public class RunWithJava8 {

    public static void main(String[] args) {

        caption("Java 8 !");
        caption("Init array:");

        List<KeyrusOffice> keyrusOffices = new ArrayList<>();
        keyrusOffices.add(new KeyrusOffice("Keyrus Headquarters", "Paris", "France", "http://www.keyrus.com"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Belgium", "Brussels", "Belgium", "http://www.keyrus.be"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Luxembourg", "Luxembourg", "Luxembourg", "http://www.keyrus.com"));
        keyrusOffices.add(new KeyrusOffice("Keyrus UK", "London", "United Kingdom", "http://www.keyrus.co.uk"));
        keyrusOffices
                .add(new KeyrusOffice("Keyrus International SA", "Geneva", "Switzerland", "http://www.keyrus.com"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Spain", "Madrid", "Spain", "http://www.keyrus.es"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Canada", "Montreal", "Canada", "http://www.keyrus.ca"));
        keyrusOffices.add(new KeyrusOffice("Keyrus US", "New York", "United States", "http://www.keyrus.us"));
        keyrusOffices.add(new KeyrusOffice("Keyrus São Paulo", "São Paulo", "Brazil", "http://www.keyrus.com.br"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Colombia", "Medellín", "Colombia", "http://www.keyrus.lat"));
        keyrusOffices.add(new KeyrusOffice("Keyrus Tunis", "Tunisia", "Tunis", "http://www.keyrus.tn"));

        System.out.println("List size:" + keyrusOffices.size());

        // stream lista ordenada
        keyrusOffices.stream().sorted((u1, u2) -> u1.getCity().compareTo(u2.getCity())).forEach(System.out::println);

        // lista filtrada
        caption("Brazilian offices:");

        List<KeyrusOffice> officesBR = keyrusOffices.stream().filter(u -> u.getCountry().equals("Brazil"))
                .collect(Collectors.toList());

        officesBR.forEach(System.out::println);

        // Map example

        Map<String, String> mapNameCountry = keyrusOffices.stream()
                .collect(Collectors.toMap(KeyrusOffice::getName, KeyrusOffice::getCountry));

        mapNameCountry.forEach((x, y) -> System.out.println("Name: " + x + ", Country: " + y));

    }

    private static void caption(String message) {
        System.out.println(message);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}

class OfficeCompare implements Comparator<KeyrusOffice> {

    @Override
    public int compare(KeyrusOffice o1, KeyrusOffice o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
