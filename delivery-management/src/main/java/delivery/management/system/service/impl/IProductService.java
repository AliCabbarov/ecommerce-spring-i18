package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.entity.Product;
import delivery.management.system.repository.ProductRepository;
import delivery.management.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ExceptionService exceptionService;

    @Override
    public Product getById(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.product.not.found", HttpStatus.NOT_FOUND)));
    }
}
