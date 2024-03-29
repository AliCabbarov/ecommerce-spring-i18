package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User user;
    @Builder.Default
    private boolean isBusy = false;
    @ManyToOne
    private DriverType driverType;
}
