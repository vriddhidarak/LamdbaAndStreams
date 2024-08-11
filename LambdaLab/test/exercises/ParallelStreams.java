package exercises;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreams {
    public List<Integer> sequentialExecution(){
        System.out.println("Sequential Execution");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        return numbers.stream().map(this::transform).toList();
    }
    public List<Integer> parallelExecution(){
        System.out.println("Parallel Execution");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        return numbers.parallelStream().map(this::transform).toList();
    }
    public Integer transform(Integer number){
        System.out.println("Transform: " + number + " - " + Thread.currentThread().getName());
        return number * 10;
    }

    public static void main(String[] args) {
        ParallelStreams parallelStreams = new ParallelStreams();
        parallelStreams.sequentialExecution();
        parallelStreams.parallelExecution();
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool().getParallelism());

    }
}
