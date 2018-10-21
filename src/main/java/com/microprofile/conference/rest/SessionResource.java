package com.microprofile.conference.rest;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/sessions")
@Schema(name = "/sessions")
public class SessionResource {

    @Inject
    SessionService service;

    @GET
    @Produces("application/json")
    @Counted(name = "getAll", absolute = true, monotonic = true, description = "Number the times requested")
    @Operation(summary = "Get all conference sessions")
    @APIResponse(responseCode = "200",
            description = "List of sessions",
            content = @Content(schema = @Schema(implementation = Session.class)))
    public List<Session> getAll() throws Exception {
        return service.getSessions();
    }

}