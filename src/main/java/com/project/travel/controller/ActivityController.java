package com.project.travel.controller;

import com.project.travel.dto.ActivityDto;
import com.project.travel.model.Activity;
import com.project.travel.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/availableActivity")
    public ResponseEntity<List<ActivityDto>> getActivity() {
        return ResponseEntity.ok(activityService.findAvailableActivities());
    }

}
