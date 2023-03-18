package com.osb.osbserverapp.service;

import com.osb.osbserverapp.repository.ObsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObsLogService {
    private final ObsLogRepository obsLogRepository;

}
