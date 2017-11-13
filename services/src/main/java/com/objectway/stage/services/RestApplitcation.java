package com.objectway.stage.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplitcation extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		singletons.add(new DomainService());
		return singletons;
	}
}
