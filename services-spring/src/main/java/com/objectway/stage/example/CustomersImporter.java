package com.objectway.stage.example;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CustomersImporter {
	private List<Customer> customers = new ArrayList<>();
	
	public CustomersImporter(Properties source) {
		Enumeration<?> enumeration = source.keys();
		Map<Integer, Customer> temp = new HashMap<>();
		while(enumeration.hasMoreElements()) {
			String currentKey = (String) enumeration.nextElement();
			String[] currentKeySplitted = currentKey.split("\\.");
			
			Integer index = Integer.valueOf(currentKeySplitted[1]);
			if(!temp.containsKey(index)) {
				Customer c = new Customer();
				c.setIdRef(index);
				temp.put(index, c);
			}
			
			String propertyName = currentKeySplitted[2];
			String propertyValue = source.getProperty(currentKey);
				
			for(PropertySetters current : PropertySetters.values()) {
				if(current.apply(propertyName)) {
					current.get().setPropertyValue(temp.get(index), propertyValue);
				}
			}
		}
		customers.addAll(temp.values());
	}

	public List<Customer> getCustomers() {
		return customers;
	}
}
