package com.pms.osb.domain.osblog.repository;

import com.pms.osb.domain.osblog.entity.ObsLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import java.util.List;

import static com.pms.osb.domain.osblog.entity.QObsLog.obsLog;


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
