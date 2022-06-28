package vn.fis.training.phl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.fis.training.phl.exception.ProductNotFoundException;
import vn.fis.training.phl.model.Product;
import vn.fis.training.phl.repository.ProductRepository;
import vn.fis.training.phl.service.ProductService;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepository = productRepo;
    }

    @Override
    public Product findById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            try {
                throw new ProductNotFoundException(String.format("id Product %s Not Found", productId));
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Product with id {} : {}", productId, product);
        return product;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
}
