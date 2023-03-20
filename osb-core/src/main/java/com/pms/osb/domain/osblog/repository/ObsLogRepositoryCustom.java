package com.pms.osb.domain.osblog.repository;

import com.pms.osb.domain.osblog.entity.ObsLog;

import java.util.List;

public interface ObsLogRepositoryCustom {
    List<ObsLog> findTopTenList();
}
