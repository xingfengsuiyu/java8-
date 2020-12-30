package 第六章.六点二规约和汇总;

import common.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author zc
 * @create 2020-12-30 20:59
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

		Long howManyDishes = menu.stream().collect(counting());
		System.out.println(howManyDishes);

		Long howManyDishes1 = menu.stream().count();
		System.out.println(howManyDishes1);


		Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCaloriesDhis = menu.stream().collect(maxBy(dishComparator));
		System.out.println(mostCaloriesDhis.get());

		System.out.println(menu.stream().max(dishComparator).get());
		System.out.println(menu.stream().max(Comparator.comparingInt(dish -> dish.getCalories())).get());

		System.out.println(menu.stream().collect(summarizingLong(Dish::getCalories)));
		System.out.println(menu.stream().collect(summarizingInt(Dish::getCalories)));
		System.out.println(menu.stream().collect(summingInt(Dish::getCalories)));

		System.out.println(menu.stream().collect(averagingInt(Dish::getCalories)));

		// 6.2.3 链接字符串 joinion 内部使用stringBuilder
		String shortMenu = menu.stream().map(Dish::getName).collect(joining());
		System.out.println(shortMenu);

		//String  shortMenu1 = menu.stream().collect(Collectors.joining());
		//System.out.println(shortMenu1);
		String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));
		System.out.println(shortMenu2);

		int totalCal = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		System.out.println(totalCal);


		Optional maxCal = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		//Optional dd = menu.stream().collect(reducing((d1, d2) -> d1.getName() + "" + d2.getName()+ ""));
		System.out.println(maxCal.get());

		// reduce方法来实现toListCollector所做的工作：
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		List<Integer> numbers = stream.reduce(
				new ArrayList<Integer>(),
				(List<Integer> l, Integer e) -> {
					l.add(e);
					return l;
				}, (List<Integer> l1, List<Integer> l2) -> {
					l1.addAll(l2);
					return l1;
				});

		int totalCalories = menu.stream().collect(reducing(0,
				Dish::getCalories,
				Integer::sum));

		String shortMenu1 = menu.stream().map(Dish::getName)
				.collect( reducing ( (s1, s2) -> s1 + s2 ) ).get();

		//menu.stream().collect(reducing((d1, d2) -> d1.getName() + d2.getName() ) );

		String shortMenu3 = menu.stream()
				.collect( reducing( "",Dish::getName, (s1, s2) -> s1 + s2 ) );
	}
}
