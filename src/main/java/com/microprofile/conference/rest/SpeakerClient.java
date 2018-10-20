package com.microprofile.conference.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;

@ApplicationScoped
public class SpeakerClient {

    @Inject
    @ConfigProperty(name = "SPEAKER_SVC_SERVICE_HOST", defaultValue = "localhost")
    String speakerHost;

    @Inject
    @ConfigProperty(name = "SPEAKER_SVC_SERVICE_PORT", defaultValue = "8080")
    String speakerPort;

    @Fallback(fallbackMethod = "getSpeakerFallback")
    public Speaker getSpeaker(Integer id) throws Exception {
        return ClientBuilder.newClient().target(new URI("http://" + speakerHost + ":" + speakerPort + "/speakers/" + id)).request().get(Speaker.class);
    }

    public Speaker getSpeakerFallback(Integer id) {
        return null;
    }

}
