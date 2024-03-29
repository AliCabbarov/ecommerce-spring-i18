package delivery.management.system.service;


import delivery.management.system.model.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<List<UserResponseDto>> findAll();

    ResponseEntity<UserResponseDto> findById(long id);

    ResponseEntity<UserResponseDto> findById();

    ResponseEntity<Void> delete(long id);

    ResponseEntity<Void> addProduct(long productId, int count);
}
