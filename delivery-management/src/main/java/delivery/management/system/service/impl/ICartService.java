package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.CartItem;
import delivery.management.system.model.entity.Product;
import delivery.management.system.model.entity.User;
import delivery.management.system.repository.CartRepository;
import delivery.management.system.service.CartService;
import delivery.management.system.service.ProductService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ICartService implements CartService {

    private final ProductService productService;
    private final CartRepository cartRepository;
    private final ExceptionService exceptionService;

    @Override
    @Transactional
    public void addProductToCart(long productId, User user, int productCount) {
        Product product = productService.getById(productId);

        Cart cart = getCartByUser(user);
        cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findAny()
                .ifPresentOrElse(cartItem -> {

                    cartItem.setCount(cartItem.getCount() + productCount);

                    BigDecimal totalAmount = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(productCount));
                    cartItem.setTotalAmount(cartItem.getTotalAmount().add(totalAmount));
                    cart.setTotalAmount(cart.getTotalAmount().add(totalAmount));

                        }, () -> {

                    CartItem cartItem = buildCartItem(productCount, product);
                    BigDecimal totalAmount = cart.getTotalAmount().add(cartItem.getTotalAmount());
                    cart.setTotalAmount(totalAmount);
                    cart.setCount(cart.getCount() + 1);
                    cart.getCartItems().add(cartItem);
                        }
                );


    }

    private Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(() ->
                new ApplicationException(exceptionService.exceptionResponse("exception.cart.not.found", HttpStatus.NOT_FOUND)));
    }


    private CartItem buildCartItem(int productCount, Product product) {

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(productCount));

        return CartItem.builder()
                .count(productCount)
                .totalAmount(totalAmount)
                .product(product)
                .build();

    }
}
