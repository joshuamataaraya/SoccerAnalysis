package controller;

import java.util.Observable;

public abstract class Controller extends Observable {
	private Object input;
	public abstract Object algoritm(Object dto) throws Exception;
}
