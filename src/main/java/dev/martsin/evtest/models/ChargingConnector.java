package dev.martsin.evtest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "charging_connector")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ChargingConnector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private ChargingConnectorType type;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double maxKWPower;

    @ManyToOne
    @JoinColumn(name = "charging_station_id")
    @JsonBackReference
    private ChargingStation chargingStation;

    public ChargingConnector(ChargingConnectorType type, Double maxKWPower) {
        this.type = type;
        this.maxKWPower = maxKWPower;
    }
}
