package com.objectway.stage.model;

import java.util.List;

public class DomainObjects {
	private final static DomainObjects INSTANCE = new DomainObjects();
	private DomainObjectsCatalog catalog;

	private DomainObjects() {
	}
	
	public final static DomainObjects get() {
		return INSTANCE;
	}
	
	public List<Comune> getComuni() {
		return catalog.getComuni();
	}
	
	public List<Provincia> getProvince() {
		return catalog.getProvince();
	}

	public void setCatalog(DomainObjectsCatalog catalog) {
		this.catalog = catalog;
	}
	
}
