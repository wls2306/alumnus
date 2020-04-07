package com.bcu.alumnus.service;

import com.bcu.alumnus.repo.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

@Service
@Validated
public class ActivityService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private ActivityRepository activityRepository;

}
