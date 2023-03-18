package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.domain.ObsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObsLogRepository extends JpaRepository<ObsLog, Long> {

}
