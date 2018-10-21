package com.microprofile.conference.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/speakers")
@Produces("application/json")
@RegisterRestClient
public interface SpeakerRestClient {

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Integer id);
}