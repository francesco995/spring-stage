package com.objectway.stage.services;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.objectway.stage.model.Comune;
import com.objectway.stage.model.DomainObjects;
import com.objectway.stage.model.Provincia;

public class DomainService implements DomainProvider {
	public DomainService() {
	}

	@Override
	public Response comuni() {
		List<Comune> comuni = DomainObjects.get().getComuni();
		if(comuni == null || comuni.isEmpty())
			throw new WebApplicationException(Status.NOT_FOUND);
		return Response.ok(comuni).build();
	}

	@Override
	public Response comuneByCodice(String codice) {
		List<Comune> comuni = DomainObjects.get().getComuni();
		if(comuni != null && !comuni.isEmpty()) {
			Comune found = FluentIterable.from(comuni).firstMatch(new Predicate<Comune>() {
				@Override
				public boolean apply(Comune comune) {
					return codice.equals(comune.getCodice());
				}
			}).orNull();
			if(found != null)
				return Response.ok(found).build();
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@Override
	public Response comuneByDescrizioneLike(String like) {
		List<Comune> comuni = DomainObjects.get().getComuni();
		if(comuni != null && !comuni.isEmpty()) {
			List<Comune> found = FluentIterable.from(comuni).filter(new Predicate<Comune>() {
				@Override
				public boolean apply(Comune comune) {
					return comune.getDescrizione().toUpperCase().startsWith(like.trim().toUpperCase());
				}
			}).toList();
			if(found != null)
				return Response.ok(found).build();
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public Response province() {
		List<Provincia> province = DomainObjects.get().getProvince();
		if(province == null || province.isEmpty())
			throw new WebApplicationException(Status.NOT_FOUND);
		return Response.ok(province).build();
	}

}
