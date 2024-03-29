package delivery.management.system.controller;

import delivery.management.system.model.dto.response.UserResponseDto;
import delivery.management.system.service.CartService;
import delivery.management.system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("find-by-id")
    ResponseEntity<UserResponseDto> findById() {
        return customerService.findById();
    }

    @PostMapping("{productId}/cart")
    ResponseEntity<Void> addProductToCart(@PathVariable long productId, @RequestParam(required = false) int count) {
        return customerService.addProduct(productId, count);
    }
}
