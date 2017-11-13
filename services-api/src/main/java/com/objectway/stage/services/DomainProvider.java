package com.objectway.stage.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/domain")
public interface DomainProvider {

	@Path("/comuni")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response comuni();
	
	@Path("/comuni/{codice}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response comuneByCodice(@PathParam("codice") String codice);
	
	@Path("/comuni/query")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response comuneByDescrizioneLike(@QueryParam("like") String like);
	
	@Path("/province")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Response province();
	
}
