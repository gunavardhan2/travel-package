package com.project.travel.model;

import jakarta.persistence.*;
        import lombok.Data;

@Data
@Entity
@Table(name="activity")
public class Activity {
    @Id
    @Column(name="activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activityId;

    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int peopleEnrolled;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;
}