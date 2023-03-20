package com.pms.osb.domain.osblog.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@ToString
@Table(name = "OBS_LOG")
public class ObsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obslog_id")
    private Long id;

    private String text;
    private Long count;
    private LocalDateTime searchStartDateTime; // 최초 조회
    private LocalDateTime updateStartDateTime; // 최근 조회

    @Builder
    public ObsLog(String text, Long count) {
        this.text = text;
        this.count = count;
        this.searchStartDateTime = LocalDateTime.now();
        this.updateStartDateTime = LocalDateTime.now();
    }
    public void plusCount() {
        this.updateStartDateTime = LocalDateTime.now();
        count++;
    }
}
