package com.mycompany.a3;

import java.util.Observer;

public interface IObservable {
	public void addObserver(Observer obs);
	public void notifyObservers();

}
