package com.objectway.stage;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.objectway.stage.example.CustomersImporter;
import com.objectway.stage.example.ExampleListContainer;
import com.objectway.stage.model.DomainObjects;
import com.objectway.stage.model.DomainObjectsCatalog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring-config/persistence-spring-beans.xml"})
public class PersistenceTest implements ApplicationContextAware {
	private ApplicationContext ac;

	@Test
	public void test() {
		MatcherAssert.assertThat(ac, Matchers.notNullValue());
		
		DomainObjectsCatalog catalog = this.ac.getBean("domainObjectsCatalog", DomainObjectsCatalog.class);
		MatcherAssert.assertThat(catalog, Matchers.notNullValue());
		MatcherAssert.assertThat(catalog.getComuni(), Matchers.notNullValue());
		MatcherAssert.assertThat(catalog.getProvince(), Matchers.notNullValue());
		
		MatcherAssert.assertThat(DomainObjects.get(), Matchers.notNullValue());
		DomainObjects domainObjects = this.ac.getBean(DomainObjects.class);
		MatcherAssert.assertThat(domainObjects, Matchers.notNullValue());
		MatcherAssert.assertThat(domainObjects, Matchers.sameInstance(DomainObjects.get()));
		
		MatcherAssert.assertThat(catalog, Matchers.not(Matchers.sameInstance(this.ac.getBean("domainObjectsCatalog2", DomainObjectsCatalog.class))));
	}
	
	@Test
	public void testList() {
		ExampleListContainer listContainer = (ExampleListContainer) this.ac.getBean("listContainer");
		MatcherAssert.assertThat(listContainer, Matchers.notNullValue());
		MatcherAssert.assertThat(listContainer.getMyList(), Matchers.notNullValue());
		MatcherAssert.assertThat(listContainer.getMyList(), Matchers.hasSize(5));
		MatcherAssert.assertThat(listContainer.getMyList().get(2), Matchers.is("Stringa3"));
	}
	
	@Test
	public void testCustomers() {
		Properties customersSource = (Properties) this.ac.getBean("customersSource");
		MatcherAssert.assertThat(customersSource, Matchers.notNullValue());
		MatcherAssert.assertThat(customersSource.size(), Matchers.is(30));
		MatcherAssert.assertThat(customersSource.getProperty("customers.3.name"), Matchers.is("Vito"));
		
		CustomersImporter importer = this.ac.getBean("importer", CustomersImporter.class);
		MatcherAssert.assertThat(importer, Matchers.notNullValue());
		MatcherAssert.assertThat(importer.getCustomers(), Matchers.notNullValue());
		MatcherAssert.assertThat(importer.getCustomers(), Matchers.hasSize(6));
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}

}
