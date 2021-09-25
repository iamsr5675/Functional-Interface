package com.bl.functionalinterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberPlayListApp {

	public static void main(String[] args) {

		List<Integer> myNumberList = new ArrayList<Integer>();
		for (int index = 0; index < 5; index++)
			myNumberList.add(index);

		// Method 1 : Traversing using Iterator
		Iterator<Integer> iterator = myNumberList.iterator();
		while (iterator.hasNext()) {
			Integer eachInteger = iterator.next();
			System.out.println("Iterator Value: " + eachInteger);
		}

		// Method 2 : Traversing with Consumer interface
		class MyConsumer implements Consumer<Integer> {
			public void accept(Integer t) {
				System.out.println("Method2 : Consumer Impl Value: " + t);
			}
		}
		MyConsumer action = new MyConsumer();
		myNumberList.forEach(action);

		// Method 3 : Traversing with Anonymous Consumer interface
		myNumberList.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println("Method3 : forEach anonymous Impl Value: " + t);
			}
		});

		// Method 4: Explicit Lambda Function
		Consumer<Integer> myListAction = n -> {
			System.out.println("Method4 : forEach Explicit Lambda Impl Value: " + n);
		};
		myNumberList.forEach(myListAction);

		// Method 5: Implicit Lambda Function
		myNumberList.forEach(n -> {
			System.out.println("Method5 : forEach Implicit Lambda Impl Value: " + n);
		});

		// Method 6: Implicit Lambda Function To Print Double Value
		Function<Integer, Double> toDoubleFunction = Integer::doubleValue;
		myNumberList.forEach(n -> {
			System.out.println("Method6 : forEach Value: " + toDoubleFunction.apply(n));
		});

		// Method 7 : Implicit Lambda Function To Check Even and Print
		Predicate<Integer> isEvenFunction = n -> n > 0 && n % 2 == 0;
		myNumberList.forEach(n -> {
			boolean testResult = isEvenFunction.test(n);
			if (testResult == true)
				System.out.println("Method7 : " + n + " is Even : ");
		});

		// JAVA STREAM API
		// Method 8 : Processing the Stream
		myNumberList.stream().forEach(n -> {
			System.out.println("Method 8 : Stream forEach Value: " + n);
		});

		// Method 9 : Process The Streams, Apply Operations on the Stream and Store the
		// Result
		List<Double> streamList = myNumberList.stream().
				filter(isEvenFunction)
				.map(toDoubleFunction)
				.collect(Collectors.toList());
		System.out.println("Method 9 : Printing Even Double List" + streamList);

		// UC 2.5 : Peek First Element
		Integer first = myNumberList.stream()
				.filter(isEvenFunction)
				.peek(n -> System.out.println("Peek Even Number: " + n))
				.findFirst()
				.orElse(null);
		System.out.println("Method 10 :  First Even: "+first);

		// UC 2.6 : Minimum and Maximum even Number
		Integer min = myNumberList.stream()
				.filter(isEvenFunction)
				.min((n1, n2) -> n1 - n2)
				.orElse(null);
		System.out.println("Method 11 :  Minimum Number: " + min);

		Integer max = myNumberList.stream()
				.filter(isEvenFunction)
				.max(Comparator.comparing(Integer::intValue))
				.orElse(null);
		System.out.println("Method 12 :  Maximum Number: " + max);

		// UC 2.7 : Sum and Average in Number Stream
		Integer sum = myNumberList.stream()
				.reduce(0, Integer::sum);
		long count = myNumberList.stream()
				.count();
		System.out.println("Method 13 : Average of " + sum + "/" + count + " = " + sum / count);

		// UC 2.8 : Stream allMatch(), anyMatch()
		boolean allEven = myNumberList.stream()
				.allMatch(isEvenFunction);
		boolean oneEven = myNumberList.stream()
				.anyMatch(isEvenFunction);
		boolean noneMultipleOfSix = myNumberList.stream()
				.noneMatch(i -> i > 0 && i % 6 == 0);
		System.out.println("All Even : " + allEven);
		System.out.println("One Even : " + oneEven);
		System.out.println("Method 14 : None Multiple Of Six : " + noneMultipleOfSix);
		
		//UC 2.9 Sort Number in Ascending Order
		List<Integer> sortedList = myNumberList.stream()
				.sorted((n1,n2) -> n2.compareTo(n1))
				.collect(Collectors.toList());
		System.out.println("Method 15 : Sorted List : "+sortedList);
		
	}
}
