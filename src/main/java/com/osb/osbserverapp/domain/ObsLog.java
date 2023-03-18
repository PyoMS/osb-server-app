package com.osb.osbserverapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Table(name = "OBS_LOG")
public class ObsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obslog_id")
    private Long id;

    private String text;
    private Long count;

    @Builder
    public ObsLog(String text, Long count) {
        this.text = text;
        this.count = count;
    }
    public void plusCount() {
        count++;
    }
}
