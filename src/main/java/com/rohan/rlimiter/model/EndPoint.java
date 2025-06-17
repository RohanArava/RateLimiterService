package com.rohan.rlimiter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="endPoints", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class EndPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated
    private Long id;

    @NonNull
    private String path;

    @NonNull
    private String method;

    @NonNull
    private int rateLimit;

    @NonNull
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application.id")
    private Application application;
}
