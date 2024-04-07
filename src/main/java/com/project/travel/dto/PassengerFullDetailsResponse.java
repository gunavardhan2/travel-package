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
public class PassengerFullDetailsResponse {
    private PassengerDetails passengerDetails;
    List<PassengerActivityDetails> passengerActivityDetails;

}
