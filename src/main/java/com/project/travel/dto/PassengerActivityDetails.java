package com.project.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerActivityDetails {
    private Long id;
    private String name;
    private String description;
    private double cost;
    private Long destinationId;
    private String destinationName;
}
