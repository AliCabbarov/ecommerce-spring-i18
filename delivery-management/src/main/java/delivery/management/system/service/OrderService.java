package delivery.management.system.service;

import delivery.management.system.model.dto.response.OrderDashboard;
import java.time.LocalDate;

public interface OrderService {
    OrderDashboard profitByDate(LocalDate localDate);
}
