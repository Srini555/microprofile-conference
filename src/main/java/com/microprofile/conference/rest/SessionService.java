package com.microprofile.conference.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class SessionService {

    @Inject
    SpeakerService client;

    @Inject
    @ConfigProperty(name = "CONFERENCE_NAME", defaultValue = "Oracle Code")
    String conference;

    List<Session> sessions;

    @PostConstruct
    public void init() {
        sessions = Arrays.asList(
                new Session(1, "Turning Your Java EE App Cloud-Native", "Intermediatte", conference, Arrays.asList(1)),
                new Session(2, "Jakarta EE Meets NoSQL in the Cloud Age", "Intermediatte", conference, Arrays.asList(3,5)),
                new Session(3, "Containers for an Automated Pipeline: Risk or Opportunity", conference, "Intermediatte", Arrays.asList(5))
        );
    }

    public List<Session> getSessions() throws Exception {
        for (Session session : sessions) {
            List<Speaker> speakers = new ArrayList<>();
            for (Integer id : session.getSpeakerIds()) {
                Speaker speaker = client.getSpeaker(id);
                if (speaker != null) {
                    speakers.add(speaker);
                }
            }
            session.setSpeakers(speakers);
        }
        return sessions;
    }



}
