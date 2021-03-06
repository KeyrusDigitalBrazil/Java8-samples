package br.com.keyrus.samples.java7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.keyrus.samples.KeyrusOffice;

/**
 * Examples with Java 7
 *
 */
public class RunWithJava7 {

    public static void main(String[] args) {

        caption("Java 7 !");
        caption("Init array:");

        List<KeyrusOffice> keyrusOffices = new ArrayList<KeyrusOffice>();
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
        keyrusOffices.add(new KeyrusOffice("Keyrus Israel", "Tel Aviv", "Israel", "http://www.keyrus.com"));
        keyrusOffices.add(
                new KeyrusOffice("Keyrus Middle East Dubai", "Dubai", "United Arab Emirates", "http://www.keyrus.com"));
        keyrusOffices.add(new KeyrusOffice("Keyrus China", "Shanghai", "China", "http://www.keyrus.sh.cn"));

        System.out.println("List size:" + keyrusOffices.size());

        for (KeyrusOffice office : keyrusOffices) {
            System.out.println(office);
        }

        Collections.sort(keyrusOffices, new OfficeCompare());

        System.out.println(" -- Ordered -- ");

        for (KeyrusOffice office : keyrusOffices) {
            System.out.println(office);
        }

        System.out.println(" -- Ordered by city -- ");
        Comparator<KeyrusOffice> comparatorCity = new Comparator<KeyrusOffice>() {
            public int compare(KeyrusOffice u1, KeyrusOffice u2) {
                return u1.getCity().compareTo(u2.getCity());
            }
        };
        Collections.sort(keyrusOffices, comparatorCity);
        for (KeyrusOffice office : keyrusOffices) {
            System.out.println(office);
        }

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
