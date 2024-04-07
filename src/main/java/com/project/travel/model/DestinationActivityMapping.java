package com.project.travel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="destination_activity_mapping")
public class DestinationActivityMapping {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

}
