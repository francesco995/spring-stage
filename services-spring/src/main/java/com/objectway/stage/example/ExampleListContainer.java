package com.objectway.stage.example;

import java.io.Serializable;
import java.util.List;

public class ExampleListContainer implements Serializable {
	private List<String> myList;
	
	public ExampleListContainer() {
	}

	public List<String> getMyList() {
		return myList;
	}

	public void setMyList(List<String> myList) {
		this.myList = myList;
	}
}
