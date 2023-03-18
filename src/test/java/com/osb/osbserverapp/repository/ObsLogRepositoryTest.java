package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.code.SearchSort;
import com.osb.osbserverapp.domain.ObsLog;
import com.osb.osbserverapp.dto.ObsGetListReq;
import com.osb.osbserverapp.service.ObsLogService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

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

    @Test
    void findTopTenListSortTest(){
        ObsLog obsLog = ObsLog.builder()
                .text("피자")
                .count(1L)
                .build();
        ObsLog obsLog2 = ObsLog.builder()
                .text("치킨")
                .count(3L)
                .build();

        ObsLog obsLog3 = ObsLog.builder()
                .text("햄버거")
                .count(5L)
                .build();
        for (int i = 0; i < 10; i++) {
            obsLog.plusCount();
            obsLog2.plusCount();
            obsLog3.plusCount();
        }
        obsLogRepository.save(obsLog);
        obsLogRepository.save(obsLog2);
        obsLogRepository.save(obsLog3);

        List<ObsLog> topTenList = obsLogRepository.findTopTenList();
        assertTrue(topTenList.get(0).getCount() > topTenList.get(1).getCount());
        assertTrue(topTenList.get(1).getCount() > topTenList.get(2).getCount());
        System.out.println("topTenList = " + topTenList);
    }

    @Test
    void findTopTenSizeTest(){
        ObsLog obsLog = ObsLog.builder().text("1").count(1L).build();
        ObsLog obsLog2 = ObsLog.builder().text("2").count(1L).build();
        ObsLog obsLog3 = ObsLog.builder().text("3").count(1L).build();
        ObsLog obsLog4 = ObsLog.builder().text("4").count(1L).build();
        ObsLog obsLog5 = ObsLog.builder().text("5").count(1L).build();

        ObsLog obsLog6 = ObsLog.builder().text("6").count(1L).build();
        ObsLog obsLog7 = ObsLog.builder().text("7").count(1L).build();
        ObsLog obsLog8 = ObsLog.builder().text("8").count(1L).build();
        ObsLog obsLog9 = ObsLog.builder().text("9").count(1L).build();
        ObsLog obsLog10 = ObsLog.builder().text("10").count(1L).build();

        ObsLog obsLog11 = ObsLog.builder().text("11").count(1L).build();
        ObsLog obsLog12 = ObsLog.builder().text("12").count(1L).build();
        ObsLog obsLog13 = ObsLog.builder().text("13").count(1L).build();
        ObsLog obsLog14 = ObsLog.builder().text("14").count(1L).build();
        ObsLog obsLog15 = ObsLog.builder().text("15").count(1L).build();

        obsLogRepository.save(obsLog);
        obsLogRepository.save(obsLog2);
        obsLogRepository.save(obsLog3);
        obsLogRepository.save(obsLog4);
        obsLogRepository.save(obsLog5);

        obsLogRepository.save(obsLog6);
        obsLogRepository.save(obsLog7);
        obsLogRepository.save(obsLog8);
        obsLogRepository.save(obsLog9);
        obsLogRepository.save(obsLog10);

        obsLogRepository.save(obsLog11);
        obsLogRepository.save(obsLog12);
        obsLogRepository.save(obsLog13);
        obsLogRepository.save(obsLog14);
        obsLogRepository.save(obsLog15);

        List<ObsLog> topTenList = obsLogRepository.findTopTenList();
        assertEquals(10, topTenList.size());

    }
}