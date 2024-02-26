package dev.martsin.evtest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "charging_connector_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargingConnectorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false, length = 32, unique = true)
    @NotBlank(message = "The value of 'type' must not be blank")
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<ChargingConnector> chargingConnector = new HashSet<>();

    public ChargingConnectorType(String name) {
        this.name = name;
    }
}
