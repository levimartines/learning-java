package lessons.parallelStream;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStream {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Runtime.getRuntime().availableProcessors());

        long number = 10_000_000;
        sumFor(number);
        sumStream(number);
        sumParallelStream(number);
        sumLongStreamRangedClosed(number);
    }

    private static void sumFor(long number) {
        long sum = 0;
        long initialTime = System.currentTimeMillis();
        for (int i = 1; i <= number; i ++) {
            sum += i;
        }
        long timeMillis = (System.currentTimeMillis() - initialTime);
        System.out.println("sumFor: " + sum + " " +  timeMillis + "ms");
    }

    private static void sumStream(long number) {
        long initialTime = System.currentTimeMillis();
        long sum = Stream.iterate(1L, num -> num + 1).limit(number).reduce(0L, Long::sum);
        long timeMillis = (System.currentTimeMillis() - initialTime);
        System.out.println("sumStream: " + sum + " " +  timeMillis + "ms");
    }

    private static void sumParallelStream(long number) {
        long initialTime = System.currentTimeMillis();
        long sum = Stream.iterate(1L, num -> num + 1).limit(number).parallel().reduce(0L, Long::sum);
        long timeMillis = (System.currentTimeMillis() - initialTime);
        System.out.println("sumParallelStream: " + sum + " " +  timeMillis + "ms");
    }

    private static void sumLongStreamRangedClosed(long number) {
        long initialTime = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, number).parallel().reduce(0L, Long::sum);
        long timeMillis = (System.currentTimeMillis() - initialTime);
        System.out.println("sumLongStreamRangedClosed: " + sum + " " +  timeMillis + "ms");
    }
}
