package com.bl.functionalinterface;

@FunctionalInterface
interface IMathFunction {
	int calculate(int a, int b);
	static void printResult(int a, int b, String function, IMathFunction fobj) { //We can add static and default methods in Functional Interface
		System.out.println("Result of " +function+ " is " + fobj.calculate(a, b));
	}
	}

public class MathOperationApp {
	public static void main(String[] args) {
		IMathFunction add = (a, b) -> a + b;  //IMathFunction add = Integer::sum;
		IMathFunction subtract = (a, b) -> a - b;
		IMathFunction multiply = (a, b) -> a * b;
		IMathFunction divide = (a, b) -> a / b;
		
		System.out.println("Addition: " +add.calculate(5, 2));  //Passing Lambda expression
		System.out.println("Subtraction: " +subtract.calculate(5, 2));
		System.out.println("Multiplication: " +multiply.calculate(5, 2));
		System.out.println("Division: " +divide.calculate(5, 2));
		
		IMathFunction.printResult(6, 3, "Addition", add); //Passing Lambda expression as a functional type
		IMathFunction.printResult(6, 3, "Subtraction", subtract);
		IMathFunction.printResult(6, 3, "Multiplication", multiply);
		IMathFunction.printResult(6, 3, "Division", divide);
	}
}
