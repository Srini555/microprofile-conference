package com.microprofile.conference.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class SpeakerClient {

    @Inject
    @ConfigProperty(name = "SPEAKER_SERVICE_HOST", defaultValue = "localhost")
    String speakerHost;

    @Inject
    @ConfigProperty(name = "SPEAKER_SERVICE_PORT", defaultValue = "8080")
    String speakerPort;

    @Inject
    @RestClient
    SpeakerRestClient speakerRestClient;

    @Fallback(fallbackMethod = "getSpeakerFallback")
    public Speaker getSpeaker(Integer id) {
        Response response = speakerRestClient.getById(id);
        return response.readEntity(Speaker.class);
    }

    public Speaker getSpeakerFallback(Integer id) {
        return null;
    }

}
