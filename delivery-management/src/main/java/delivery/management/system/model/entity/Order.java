package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "_order")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String place;
    private BigDecimal totalAmount;
    @OneToOne
    TableDetails details;
    @ManyToOne
    private Cart cart;

    @OneToMany
    private List<OrderItem> orderItems;



}
