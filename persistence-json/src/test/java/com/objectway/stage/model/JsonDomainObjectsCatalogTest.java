package com.objectway.stage.model;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JsonDomainObjectsCatalogTest {
	private JsonDomainObjectsCatalog catalog;
	
	@Before
	public void before() {
		catalog = new JsonDomainObjectsCatalog();
		catalog.init();
	}

	@Test
	public void testGetComuni() {
		assertThat(catalog, notNullValue());
		List<Comune> comuni = catalog.getComuni();
		assertThat(comuni, notNullValue());
		assertThat(comuni, hasSize(7978));
	}

	@Test
	public void testGetProvince() {
		assertThat(catalog, notNullValue());
		List<Provincia> province = catalog.getProvince();
		assertThat(province, notNullValue());
		assertThat(province, hasSize(107));
	}

}
