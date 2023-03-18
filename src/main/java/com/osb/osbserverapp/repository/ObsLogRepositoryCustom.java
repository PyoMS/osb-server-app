package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.domain.ObsLog;

import java.util.List;

public interface ObsLogRepositoryCustom {
    List<ObsLog> findTopTenList();
}
