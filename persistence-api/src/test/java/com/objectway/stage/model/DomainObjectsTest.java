package com.objectway.stage.model;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DomainObjectsTest {
	
	@Before
	public void before() {
		DomainObjectsCatalog catalog = Mockito.mock(DomainObjectsCatalog.class);
		DomainObjects.get().setCatalog(catalog);
		
		List<Comune> comuni = new ArrayList<>();
		List<Provincia> province = new ArrayList<>();
		
		province.add(new Provincia("P1", "Provincia 1"));
		
		comuni.add(new Comune("C1", "Comune 1", province.get(0)));
		comuni.add(new Comune("C2", "Comune 2", province.get(0)));
		comuni.add(new Comune("C3", "Comune 3", province.get(0)));
		
		when(catalog.getComuni()).thenReturn(comuni);
		when(catalog.getProvince()).thenReturn(province);
	}
	

	@Test
	public void testGet() {
		assertThat(DomainObjects.get(), notNullValue());
	}

	@Test
	public void testGetComuni() {
		assertThat(DomainObjects.get(), notNullValue());
		assertThat(DomainObjects.get().getComuni(), notNullValue());
		assertThat(DomainObjects.get().getComuni(), hasSize(3));
	}

	@Test
	public void testGetProvince() {
		assertThat(DomainObjects.get(), notNullValue());
		assertThat(DomainObjects.get().getProvince(), notNullValue());
		assertThat(DomainObjects.get().getProvince(), hasSize(1));
	}

}
