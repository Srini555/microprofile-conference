package com.microprofile.conference.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URL;

@ApplicationScoped
public class SpeakerService {

    @Inject
    @ConfigProperty(name = "SPEAKER_SERVICE_HOST", defaultValue = "localhost")
    String speakerHost;

    @Inject
    @ConfigProperty(name = "SPEAKER_SERVICE_PORT", defaultValue = "8080")
    String speakerPort;

    @Fallback(fallbackMethod = "getSpeakerFallback")
    public Speaker getSpeaker(Integer id) throws Exception {
        URL url = new URL("http://" + speakerHost + ":" + speakerPort);
        SpeakerClient speakerRestClient = RestClientBuilder.newBuilder()
                .baseUrl(url).build(SpeakerClient.class);
        Response response = speakerRestClient.getById(id);
        return response.readEntity(Speaker.class);
    }

    public Speaker getSpeakerFallback(Integer id) {
        return null;
    }

}
