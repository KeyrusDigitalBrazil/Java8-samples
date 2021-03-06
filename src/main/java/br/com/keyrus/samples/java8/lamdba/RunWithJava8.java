package br.com.keyrus.samples.java8.lamdba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

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

        for (KeyrusOffice office : keyrusOffices) {
            System.out.println(office);
        }

        keyrusOffices.sort(new OfficeCompare());

        System.out.println(" -- Ordered -- ");

        // for (KeyrusOffice office : keyrusOffices) {
        // System.out.println(office);
        // }

        // com consumer
        caption("com consumer");
        ConsumerTeste consumerKeyrusOffice = new ConsumerTeste();
        keyrusOffices.forEach(consumerKeyrusOffice);

        // com consumer 2
        caption("com consumerKeyrusOffice");
        Consumer<KeyrusOffice> consumerKeyrusOffice2 = (KeyrusOffice o) -> {
            System.out.println(o);
        };
        keyrusOffices.forEach(consumerKeyrusOffice2);

        // com consumer 3
        caption("com consumerKeyrusOffice");
        Consumer<KeyrusOffice> consumerKeyrusOffice3 = (o) -> System.out.println(o);
        keyrusOffices.forEach(consumerKeyrusOffice3);

        // com Lambda
        caption("com Lambda");
        keyrusOffices.forEach(u -> System.out.println(u));

        // com method reference
        caption("com method reference");
        keyrusOffices.forEach(System.out::println);

        System.out.println(" -- Ordered by city -- ");
        Comparator<KeyrusOffice> comparatorCity = (u1, u2) -> u1.getCity().compareTo(u2.getCity());
        Collections.sort(keyrusOffices, comparatorCity);
        keyrusOffices.forEach(System.out::println);

        // sem declarar comparator
        Collections.sort(keyrusOffices, (u1, u2) -> u1.getCity().compareTo(u2.getCity()));
        keyrusOffices.forEach(System.out::println);

        // default method sort
        keyrusOffices.sort((u1, u2) -> u1.getCity().compareTo(u2.getCity()));
        keyrusOffices.forEach(System.out::println);

    }

    private static void caption(String message) {
        System.out.println(message);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}

class ConsumerTeste implements Consumer<KeyrusOffice> {
    public void accept(KeyrusOffice office) {
        System.out.println(office);
    }
}

class OfficeCompare implements Comparator<KeyrusOffice> {

    @Override
    public int compare(KeyrusOffice o1, KeyrusOffice o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
