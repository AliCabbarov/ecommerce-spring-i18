package delivery.management.system.helper;

import delivery.management.system.model.dto.response.DashboardResponse;
import delivery.management.system.model.dto.response.OrderDashboard;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceHelper {

    public DashboardResponse dashboardBuild(OrderDashboard daily, OrderDashboard monthly,OrderDashboard yearly, int customerCount, int driverCount) {
        return DashboardResponse.builder()
                .customerCount(customerCount)
                .driverCount(driverCount)
                .dailyProfit(daily.getSum())
                .monthlyProfit(monthly.getSum())
                .yearlyProfit(yearly.getSum())
                .dailyOrderCount(daily.getCount())
                .monthlyOrderCount(monthly.getCount())
                .yearlyOrderCount(yearly.getCount())
                .build();
    }
}
