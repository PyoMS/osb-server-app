package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.domain.ObsLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.osb.osbserverapp.domain.QObsLog.obsLog;

@RequiredArgsConstructor
public class ObsLogRepositoryImpl implements ObsLogRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ObsLog> findTopTenList() {
        return queryFactory.selectFrom(obsLog)
                .offset(0)
                .limit(10)
                .orderBy(obsLog.count.desc())
                .fetch();
    }
}
