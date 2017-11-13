package com.objectway.stage.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.slf4j.LoggerFactory;

public enum PropertySetters {
	NAME {
		@Override
		public IPropertySetter get() {
			return new NamePropertySetter();
		}

		@Override
		public boolean apply(String propertyName) {
			return "name".equalsIgnoreCase(propertyName);
		}
	}, 
	LASTNAME {
		@Override
		public IPropertySetter get() {
			return new LastnamePropertySetter();
		}

		@Override
		public boolean apply(String propertyName) {
			return "lastname".equalsIgnoreCase(propertyName);
		}},
	AGE {
		@Override
		public IPropertySetter get() {
			return new AgePropertySetter();
		}

		@Override
		public boolean apply(String propertyName) {
			return "age".equalsIgnoreCase(propertyName);
		}},
	PHONES {
		@Override
		public IPropertySetter get() {
			return new PhonesPropertySetter();
		}

		@Override
		public boolean apply(String propertyName) {
			return "phones".equalsIgnoreCase(propertyName);
		}},
	BIRTHDATE {
			@Override
			public IPropertySetter get() {
				return new BirthdatePropertySetter();
			}

			@Override
			public boolean apply(String propertyName) {
				return "birthdate".equalsIgnoreCase(propertyName);
			}};
	
	public abstract IPropertySetter get();
	
	public abstract boolean apply(String propertyName);
	
	private final static class NamePropertySetter implements IPropertySetter {
		@Override
		public Customer setPropertyValue(Customer c, String propertyValue) {
			c.setName(propertyValue);
			return c;
		}
	}
	
	private final static class LastnamePropertySetter implements IPropertySetter {
		@Override
		public Customer setPropertyValue(Customer c, String propertyValue) {
			c.setLastName(propertyValue);
			return c;
		}
	}
	
	private final static class AgePropertySetter implements IPropertySetter {
		@Override
		public Customer setPropertyValue(Customer c, String propertyValue) {
			c.setAge(propertyValue != null && propertyValue.length() > 0 ? Integer.valueOf(propertyValue) : 0);
			return c;
		}
	}
	
	private final static class PhonesPropertySetter implements IPropertySetter {
		@Override
		public Customer setPropertyValue(Customer c, String propertyValue) {
			c.setPhones(Arrays.asList(propertyValue.split(",")));
			return c;
		}
	}
	
	private final static class BirthdatePropertySetter implements IPropertySetter {
		@Override
		public Customer setPropertyValue(Customer c, String propertyValue) {
			if(propertyValue != null && propertyValue.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					c.setBirthDate(sdf.parse(propertyValue));
				} catch (ParseException e) {
					LoggerFactory.getLogger(BirthdatePropertySetter.class).error("Errore nel recupero della data di nascita", e);
				}
			}
			return c;
		}
	}
}
