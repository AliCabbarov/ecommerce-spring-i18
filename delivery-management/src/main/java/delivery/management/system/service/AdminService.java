package delivery.management.system.service;

import delivery.management.system.model.dto.response.DashboardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    ResponseEntity<DashboardResponse> dashboard();
}
