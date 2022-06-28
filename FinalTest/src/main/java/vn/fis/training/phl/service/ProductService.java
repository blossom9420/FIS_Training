package vn.fis.training.phl.service;

import vn.fis.training.phl.model.Product;

public interface ProductService {
    Product findById(Long productId);
    Product update(Product product);
}
