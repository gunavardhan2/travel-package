package com.project.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerListResponse {
    private String packageName;
    private Long passengerCapacity;
    private Long passengersEnrolled;
    private List<PassengerDto> passengerList;

}
