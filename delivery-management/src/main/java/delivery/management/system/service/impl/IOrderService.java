package delivery.management.system.service.impl;

import delivery.management.system.model.dto.response.OrderDashboard;
import delivery.management.system.model.entity.Order;
import delivery.management.system.repository.OrderRepository;
import delivery.management.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDashboard profitByDate(LocalDate localDate) {

        List<Order> orders = orderRepository.findOrdersByDetailsCreatedAtProfit(localDate);

        BigDecimal sum = orders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return OrderDashboard.builder()
                .count(orders.size())
                .sum(sum)
                .build();
    }
}
