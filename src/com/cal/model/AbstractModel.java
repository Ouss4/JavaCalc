package com.cal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements Observable{
	
	protected List<Observer> observers = new ArrayList<>();
	
	public abstract void setOperator(String operator);
	public abstract void setFirstNumber(Double firstNumber);
	public abstract void setSecondNumber(Double secondNumber);
	public abstract void getResult();
	public abstract void notifyNumber(String num);
	public abstract  void reset();
	
	@Override
	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}

	@Override
	public void notifyObservers(String str) {
		for(Observer obs : observers){
			obs.update(str);
		}
		
	}

}
