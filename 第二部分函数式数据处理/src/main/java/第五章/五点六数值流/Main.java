package 第五章.五点六数值流;

import common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zc
 * @create 2020-12-27 12:25
 **/
public class Main {

  public static void main(String[] args) {
    List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));
    int calories = menu.stream()
        .map(Dish::getCalories)
        .reduce(0, Integer::sum);

    System.out.println(calories);

    int sum1 = menu.stream().mapToInt(Dish::getCalories).sum();
    System.out.println(sum1);

    IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
    Stream<Integer> stream = intStream.boxed();

    OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
    System.out.println(max.orElse(1));

    IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
    System.out.println(evenNumbers.count());
    IntStream evenNumbers1 = IntStream.range(1, 100).filter(i -> i % 2 == 0);
    System.out.println(evenNumbers1.count());

    // 三元勾股数组
    IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})).filter(c -> c[2] % 1 == 0)
        .forEach(t ->
            System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
  }
}
