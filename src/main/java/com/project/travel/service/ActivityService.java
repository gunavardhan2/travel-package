package com.project.travel.service;

import com.project.travel.dto.ActivityDto;
import com.project.travel.model.Activity;
import com.project.travel.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    public List<ActivityDto> findAvailableActivities() {
        var mapper = new ModelMapper();

        return activityRepository.findAvailableSlotActivities()
                .stream().map(Activity -> {
            ActivityDto activity = mapper.map(Activity, ActivityDto.class);
            activity.setAvailableSlots(Activity.getCapacity()-Activity.getPeopleEnrolled());
            return activity;}).toList();
    }
}
