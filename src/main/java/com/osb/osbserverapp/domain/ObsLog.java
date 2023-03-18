package com.osb.osbserverapp.domain;


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
}
