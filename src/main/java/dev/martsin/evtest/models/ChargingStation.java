package dev.martsin.evtest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "charging_station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    private String title;

    @Column(length = 128)
    private String description;

    @Column(length = 128)
    private String address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Geolocation location;

    private Boolean isPublic;

    @OneToMany(mappedBy = "chargingStation", cascade = CascadeType.ALL)
    @BatchSize(size = 8)
    @JsonManagedReference
    private Set<ChargingConnector> connectors = new HashSet<>();

}
