package com.osb.osbserverapp.repository;

import com.osb.osbserverapp.domain.ObsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObsLogRepository extends JpaRepository<ObsLog, Long>, ObsLogRepositoryCustom {
    Optional<ObsLog> findByText(String text);

}
