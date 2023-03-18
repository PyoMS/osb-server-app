package com.osb.osbserverapp.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime searchStartDateTime; // 시간대별 조회 현황

    @Builder
    public ObsLog(String text, Long count) {
        this.text = text;
        this.count = count;
        this.searchStartDateTime = LocalDateTime.now();
    }
    public void plusCount() {
        count++;
    }
}
