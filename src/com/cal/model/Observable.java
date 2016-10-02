package com.cal.model;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers(String str);
}
