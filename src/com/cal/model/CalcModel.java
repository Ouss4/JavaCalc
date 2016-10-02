package com.cal.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class CalcModel extends AbstractModel{
	
	private Double firstNumber;
	private Double secondNumber;
	private Double result;
	
	private String op;
	
	private static final Map<String, BiFunction<Double, Double, Double>> OPERATORS = new HashMap<>();
    static {
        OPERATORS.put("+", (o1, o2) -> o1 + o2);
        OPERATORS.put("-", (o1, o2) -> o1 - o2);
        OPERATORS.put("*", (o1, o2) -> o1 * o2);
        OPERATORS.put("/", (o1, o2) -> o1 / o2);
    }
    
	public CalcModel(){
		firstNumber = 0.;
		result = 0.;
		op = "";
	}
	
	@Override
	public void setOperator(String op) {
		this.op = op;
	}

	@Override
	public void getResult() {
		workOut();
		notifyObservers(result.toString());
	}

	@Override
	public void notifyNumber(String num) {
		notifyObservers(num);
	}
	
	private void workOut() {
		result = OPERATORS.get(op).apply(firstNumber, secondNumber);
	}

	@Override
	public void setFirstNumber(Double firstNumber) {
		this.firstNumber = firstNumber;
	}

	@Override
	public void setSecondNumber(Double secondNumber) {
		this.secondNumber = secondNumber;
	}

	@Override
	public void reset() {
		firstNumber = 0.;
		secondNumber = 0.;
		result = 0.;
		notifyObservers("0");
	}
}
