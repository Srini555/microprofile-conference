package com.microprofile.conference.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@XmlRootElement
public class Session {

    Integer id;
    String title;
    String level;
    String conference;
    List<Speaker> speakers;
    List<Integer> speakerIds;

    public Session(Integer id, String title, String level, String conference, List<Integer> speakerIds) {
        super();
        this.id = id;
        this.title = title;
        this.level = level;
        this.conference = conference;
        this.speakerIds = speakerIds;
    }

    @XmlTransient
    public List<Integer> getSpeakerIds() {
        return speakerIds;
    }
}
