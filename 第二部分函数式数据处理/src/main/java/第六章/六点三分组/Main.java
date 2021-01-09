package 第六章.六点三分组;

import common.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author zc
 * @create 2021-01-09 16:39
 **/
public class Main {
	public enum CaloricLevel {DIET, NORMAL, FAT}

	public enum FirstName {PNAME, OTHER}

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

		Map<Dish.Type, List<Dish>> dishesByType =
				menu.stream().collect(groupingBy(Dish::getType));

		System.out.println(dishesByType);

		// 对菜单的热量进行分组
		Map<CaloricLevel, List<Dish>> caloricLevelListMap = menu.stream().collect(groupingBy(
				dish -> {
					if (dish.getCalories() <= 400) {
						return CaloricLevel.DIET;
					} else if (dish.getCalories() <= 700) {
						return CaloricLevel.NORMAL;
					} else {
						return CaloricLevel.FAT;
					}
				}
		));
		System.out.println(caloricLevelListMap);

		// 多级分组
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishByTypeAndCaloricLevel = menu.stream().collect(
				groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400) {
						return CaloricLevel.DIET;
					} else if (dish.getCalories() <= 700) {
						return CaloricLevel.NORMAL;
					} else {
						return CaloricLevel.FAT;
					}
				}))
		);
		System.out.println(dishByTypeAndCaloricLevel);

		// 三层分组
		Map<Dish.Type, Map<CaloricLevel, Map<FirstName, List<Dish>>>> three = menu.stream().collect(
				groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400) {
						return CaloricLevel.DIET;
					} else if (dish.getCalories() <= 700) {
						return CaloricLevel.NORMAL;
					} else {
						return CaloricLevel.FAT;
					}
				}, groupingBy(item -> {
					if (item.getName().indexOf("p") >= 0) {
						return FirstName.PNAME;
					} else {
						return FirstName.OTHER;
					}
				})))
		);
		System.out.println(three);

		// 按子组收集数据
		Map<Dish.Type, Long> typesCount = menu.stream().collect(
				groupingBy(Dish::getType, counting()));
		System.out.println(typesCount);

		Map<Dish.Type, Optional<Dish>> mostCaloricByType =
				menu.stream()
						.collect(groupingBy(Dish::getType,
								maxBy(Comparator.comparingInt(Dish::getCalories))));
		System.out.println(mostCaloricByType);

		Map<Dish.Type, Dish> mostType = menu.stream().collect(groupingBy(Dish::getType,
				collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println(mostType);

		Map<Dish.Type, Integer> totalCaloriesByType =
				menu.stream().collect(groupingBy(Dish::getType,
						summingInt(Dish::getCalories)));
		System.out.println(totalCaloriesByType);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
				menu.stream().collect(
						groupingBy(Dish::getType, mapping(
								dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
								else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
								else return CaloricLevel.FAT; },
								toSet() )));
		System.out.println(caloricLevelsByType);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
				menu.stream().collect(
						groupingBy(Dish::getType, mapping(
								dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
								else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
								else return CaloricLevel.FAT; },
								toCollection(HashSet::new) )));
		System.out.println(caloricLevelsByType2);
	}
}
