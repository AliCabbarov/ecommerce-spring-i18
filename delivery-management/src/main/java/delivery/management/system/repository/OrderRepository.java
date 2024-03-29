package delivery.management.system.repository;

import delivery.management.system.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM _order o JOIN o.details d  WHERE d.createdAt >= :creatAt")
    List<Order> findOrdersByDetailsCreatedAtProfit(LocalDate creatAt);
}
