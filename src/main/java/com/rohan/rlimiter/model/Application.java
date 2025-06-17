package com.rohan.rlimiter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "applications", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}), @UniqueConstraint(columnNames = {"apiKey"})})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    @Column(unique = true)
    private String apiKey;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<EndPoint> endPoints = new ArrayList<>();
}