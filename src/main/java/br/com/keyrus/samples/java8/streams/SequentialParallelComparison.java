package br.com.keyrus.samples.java8.streams;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

public class SequentialParallelComparison {

    public static void main(String[] args) {
        String[] strings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        System.out.println("-------\nRunning sequential\n-------");
        Instant start = Instant.now();

        run(Arrays.stream(strings).sequential());

        Instant stop = Instant.now();
        System.out.println("-------\nTime spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("-------\nRunning parallel\n-------");

        run(Arrays.stream(strings).parallel());

        stop = Instant.now();
        System.out.println("-------\nTime spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
    }

    public static void run(Stream<String> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s + " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}