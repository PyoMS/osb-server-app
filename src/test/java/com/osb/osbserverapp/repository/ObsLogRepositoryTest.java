package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.code.SearchSort;
import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObsLogRepositoryTest {

    @Autowired
    ObsLogRepository obsLogRepository;

    ObsGetListReq obsGetListReq;
    @BeforeEach
    void beforeAll(){
        obsGetListReq = new ObsGetListReq("test", SearchSort.accuracy, 1, 10);
    }

    @Test
    void findByText() {
        ObsLog obsLog = ObsLog.builder()
                .text(obsGetListReq.getText())
                .count(1L)
                .build();
        ObsLog obsLog1 = obsLogRepository.save(obsLog);

        assertEquals(1, obsLog1.getCount());
        obsLog1.plusCount();

        assertNotEquals(1, obsLog1.getCount());
        assertEquals(2, obsLog1.getCount());
    }
}