package delivery.management.system.service;

import delivery.management.system.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface CartService {
    void addProductToCart(long productId, User user, int count);
}
