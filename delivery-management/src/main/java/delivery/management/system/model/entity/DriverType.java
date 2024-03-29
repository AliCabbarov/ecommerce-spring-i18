package delivery.management.system.model.entity;

import delivery.management.system.model.enums.DriverStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class DriverType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private DriverStatus status;
}
