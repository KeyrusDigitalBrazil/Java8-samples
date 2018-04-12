package br.com.keyrus.samples.java8.lamdba;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Source:
 * https://github.com/takipi/loops-jmh-playground
 *
 */
public class LoopBenchmarkMain {

    volatile int           size     = 100000;
    volatile List<Integer> integers = null;

    public static void main(String[] args) {
        LoopBenchmarkMain benchmark = new LoopBenchmarkMain();
        benchmark.setup();

        Instant start = Instant.now();
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        Instant stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
        start = Instant.now();
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
        stop = Instant.now();
        System.out.println("Time spent: " + ChronoUnit.MILLIS.between(start, stop) + " ms");
    }

    public void setup() {
        integers = new ArrayList<Integer>(size);
        populate(integers);
    }

    public void populate(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000000));
        }
    }

    public int iteratorMaxInteger() {
        int max = Integer.MIN_VALUE;
        for (Iterator<Integer> it = integers.iterator(); it.hasNext();) {
            max = Integer.max(max, it.next());
        }
        return max;
    }

    public int forEachLoopMaxInteger() {
        int max = Integer.MIN_VALUE;
        for (Integer n : integers) {
            max = Integer.max(max, n);
        }
        return max;
    }

    public int forEachLambdaMaxInteger() {
        final Wrapper wrapper = new Wrapper();
        wrapper.inner = Integer.MIN_VALUE;

        integers.forEach(i -> helper(i, wrapper));
        return wrapper.inner.intValue();
    }

    public static class Wrapper {
        public Integer inner;
    }

    private int helper(int i, Wrapper wrapper) {
        wrapper.inner = Math.max(i, wrapper.inner);
        return wrapper.inner;
    }

    public int forMaxInteger() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            max = Integer.max(max, integers.get(i));
        }
        return max;
    }

    public int parallelStreamMaxInteger() {
        Optional<Integer> max = integers.parallelStream().reduce(Integer::max);
        return max.get();
    }

    public int streamMaxInteger() {
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        return max.get();
    }

    public int lambdaMaxInteger() {
        return integers.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
    }
}
