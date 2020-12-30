package 第五章.五点七构建流;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zc
 * @create 2020-12-27 15:33
 **/
public class Main {

  public static void main(String[] args) {
    // 由值创建流
    Stream<String> stream = Stream.of("Java8", "Lambads", "In", "Action");
    stream.map(String::toUpperCase).forEach(System.out::println);

    // 创建空流
    Stream<String> empty = Stream.empty();

    // 由数组创建流
    int[] numbers = {1, 2, 3, 4, 5, 6, 7};
    IntStream stream1 = Arrays.stream(numbers);
    System.out.println(stream1.sum());

    // 由文件创建流
    try {
      try (Stream<String> stream2 = Files
          .lines(Paths.get("第二部分函数式数据处理\\src\\main\\java\\五点七构建流\\data.txt"),
              Charset.defaultCharset())) {
        Long count = stream2.flatMap(str -> Arrays.stream(str.split(""))).distinct().count();
        System.out.println(count);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 由函数生成流，创建无限流
    Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    // 斐波纳契元组序列
    /**
     斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分： 0, 1, 1,
     2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。
     斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列： (0, 1),
     (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
     你的任务是用iterate方法生成斐波纳契元组序列中的前20个元素。
     */
    Stream.iterate(new int[]{0, 1}, n ->
        new int[]{n[1], n[0] + n[1]}).limit(20)
        .forEach(t -> System.out
            .println(t[0] + "," + t[1]));

    Stream.generate(Math::random).limit(5).forEach(System.out::println);

    IntSupplier fib = new IntSupplier(){
      private int previous = 0;
      private int current = 1;
      public int getAsInt(){
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };
    IntStream.generate(fib).limit(10).forEach(System.out::println);
  }
}
