package com.project.travel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {

    private Long id;
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Long destinationId;
    private int peopleEnrolled;
    private int availableSlots ;
}